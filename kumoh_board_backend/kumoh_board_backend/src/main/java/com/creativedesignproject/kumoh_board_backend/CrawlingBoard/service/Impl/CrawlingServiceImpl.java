package com.creativedesignproject.kumoh_board_backend.CrawlingBoard.service.Impl;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.common.RobustWebDriver;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.common.RobustWebElement;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.entity.CrawlingActivitiesEntity;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.entity.CrawlingContestsEntity;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.entity.LinkCareerContestsEntity;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.service.CrawlingService;
import com.creativedesignproject.kumoh_board_backend.mapper.CrawlingMapper;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.entity.LinkCareerActivitiesEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CrawlingServiceImpl implements CrawlingService {
    private final CrawlingMapper crawlingMapper;

    private final String wevityUrl = "https://www.wevity.com";
    private final String linkCareerUrl = "https://linkareer.com";

    @Scheduled(cron = "0 0 0 * * *")
    @Override
    public void crawlContestsFromWevity() throws IOException {
        int remainingDaysThreshold = 0; // 남은 기간이 0일 이하인 데이터 삭제
        crawlingMapper.deleteByRemainingDaysLessThanEqualContest(remainingDaysThreshold);

        int[] fieldPageNums = {20, 21, 22};

        for(int fieldPageNum : fieldPageNums) {
            log.info(fieldPageNum + "번째 분야 크롤링 시작");
            int pageNum = 1;

            while (true) {
                final String contestUrl = "/?c=find&s=1&gub=1&cidx=" + fieldPageNum + "&gp=" + pageNum;
                log.info("pageNum: {}", pageNum);
                log.info("contestUrl: {}", contestUrl);
                Document doc = Jsoup.connect(wevityUrl + contestUrl).get();
                Elements contests = doc.select(".main-section .ms-list .list li");
                if (contests.size() == 1) {
                    break;
                }
                log.info("contest size: {}", contests.size());

                for (int i = 1; i < contests.size(); i++) {
                    Element contest = contests.get(i);
                    String title = contest.select(".tit a").text(); // 제목
                    String field = contest.select(".sub-tit").text(); // 분야
                    String host = contest.select(".organ").text(); // 주최
                    String status = contest.select(".dday").text(); // 진행 상태
                    if(status.equals("마감")) continue;
                    Element dateElement = contest.selectFirst(".day"); // 마감일
                    dateElement.select(".dday").remove();
                    String date = dateElement.text();
                    int views = Integer.parseInt(contest.select(".read").text().replaceAll("[^0-9]", "")); // 리뷰 수를 int로 변환
                    String url = contest.select(".tit a").attr("abs:href"); // 상세 링크

                    LocalDate parsedDate = parseDate(date);
                    if (crawlingMapper.existsByUrlContest(url)) {
                        log.info("중복된 데이 뛰어넘음");
                        continue;
                    } 

                    Document detailDoc = Jsoup.connect(url).get();

                    String detailData = detailDoc.select(".article .comm-desc div").text();

                    CrawlingContestsEntity crawlingEntity = CrawlingContestsEntity.builder()
                            .title(title)
                            .field(field)
                            .host(host)
                            .status(status)
                            .date(parsedDate)
                            .url(url)
                            .views(views)
                            .detailData(detailData)
                            .build();
                    crawlingMapper.save(crawlingEntity);
                }
                pageNum++;
            }
        }
    }

    @Scheduled(cron = "0 0 0 * * *")
    @Override
    public void crawlActivitiesFromWevity() throws IOException {
        int remainingDaysThreshold = 0; // 남은 기간이 0일 이하인 데이터 삭제
        crawlingMapper.deleteByRemainingDaysLessThanEqualActivity(remainingDaysThreshold);
        
        int pageNum = 1;
        while (true) {
            if(pageNum > 50) {
                log.info("페이지 수 초과로 크롤링 종료");
                break;
            }
            final String contestUrl = "/?c=active&s=1&gp=" + pageNum;
            log.info("pageNum: {}", pageNum);
            log.info("contestUrl: {}", contestUrl);
            Document doc = Jsoup.connect(wevityUrl + contestUrl).get();
            Elements contests = doc.select(".ext-area .ext-list li");
            if (contests.size() == 0) {
                break;
            }
            log.info("contest size: {}", contests.size());

            for (int i = 1; i < contests.size(); i++) {
                Element contest = contests.get(i);
                String title = contest.select("li a").text(); // 제목
                String field = contest.select(".hide-cat").text(); // 분야
                String image = contest.select("li a img").attr("abs:src"); // 이미지
                Element dateElement = contest.selectFirst(".hide-dday"); // 마감일
                
                if (dateElement == null) {
                    log.warn("마감일 요소를 찾을 수 없습니다. 데이터를 건너뜁니다.");
                    continue;
                }
                String date = dateElement.text();

                if(date.startsWith("D+")) {
                    log.info("마감일이 D+로 시작하는 데이터 뛰어넘음");
                    continue;
                }

                LocalDate parsedDate = parseDate(date);
                String url = contest.select("li a").attr("abs:href"); // 상세 링크

                if (crawlingMapper.existsByUrlActivity(url)) {
                    log.info("중복된 데이터 뛰어넘음");
                    continue;
                }

                Document detailDoc = Jsoup.connect(url).get();

                String detailData = detailDoc.select(".contest-detail div").text();

                CrawlingActivitiesEntity crawlingEntity = CrawlingActivitiesEntity.builder()
                        .title(title)
                        .field(field)
                        .image(wevityUrl + image)
                        .date(parsedDate)
                        .url(url)
                        .detailData(detailData)
                        .build();
                crawlingMapper.saveActivity(crawlingEntity);
            }
            pageNum++;
        }
    }

    @Scheduled(cron = "0 0 0 * * *")
    @Override
    public void crawlContestsFromLinkCareer() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\samsung\\Desktop\\chromedriver-win64\\chromedriver.exe");
        WebDriver originalDriver = new ChromeDriver();
        RobustWebDriver driver = new RobustWebDriver(originalDriver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        int pageNum = 1;
        List<String> detailUrls = new ArrayList<>();
        Set<String> existingUrls = new HashSet<>(crawlingMapper.findAllLinkCareerContestUrls());

        while(true) {
            final String contestUrl = "/list/contest?filterBy_categoryIDs=35&filterBy_categoryIDs=33&filterType=CATEGORY&orderBy_direction=DESC&orderBy_field=CREATED_AT&page=" + pageNum;
            log.info("pageNum: {}", pageNum);
            log.info("contestUrl: {}", contestUrl);

            driver.get(linkCareerUrl + contestUrl);
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list-body .activity-list-card-item-wrapper"))); // 요소가 로드될 때까지 대기
            } catch (TimeoutException e) {
                pageNum++;
                break;
            }
            List<WebElement> contests = driver.findElements(By.cssSelector(".list-body .activity-list-card-item-wrapper"));
            
            log.info("contests크기{}", contests.size());

            if (contests.isEmpty()) {
                log.info("더 이상 크롤링할 공모전이 없습니다. 크롤링을 종료합니다.");
                break;
            }

            for (int i = 0; i < contests.size(); i++) {
                RobustWebElement contest = (RobustWebElement) contests.get(i);
                log.info("contest : {}", contest);
                String title = contest.findElement(By.cssSelector(".activity-title")).getText(); // 제목
                String url = contest.findElement(By.cssSelector(".card-content a")).getAttribute("href"); // 상세 링크
                log.info("url: {}", url);
                String image = contest.findElement(By.cssSelector(".image-link img")).getAttribute("src"); // 이미지
                String host = contest.findElement(By.cssSelector(".organization-name")).getText(); // 주최
                List<WebElement> dataList = contest.findElements(By.cssSelector(".card-content div"));
                log.info("dataList크기{}", dataList.size());
                
                String date = ((RobustWebElement) dataList.get(3)).getText();
                log.info("date: {}", date);
                if (date.startsWith("D+") || existingUrls.contains(url)) {
                    log.info("마감일이 D+로 시작하는 데이터 뛰어넘음");
                    continue;
                }

                detailUrls.add(url);

                LocalDate parsedDate = parseDate(date);
                LinkCareerContestsEntity crawlingEntity = LinkCareerContestsEntity.builder()
                        .title(title)
                        .url(url)
                        .image(image)
                        .host(host)
                        .date(parsedDate)
                        .build();

                crawlingMapper.saveLinkCareerContest(crawlingEntity);
                existingUrls.add(url);
            }
            pageNum++;
        }
        log.info("목록 페이지 크롤링이 완료되었습니다. 상세 페이지 크롤링을 시작합니다.");

        List<LinkCareerContestsEntity> entitiesToUpdate = new ArrayList<>();

        log.info("existingUrls 크기: {}", existingUrls.size());

        for (String detailUrl : existingUrls) {
            driver.get(detailUrl);
            String detailData = "";
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".responsive-element")));
                List<WebElement> elements = driver.findElements(By.cssSelector(".responsive-element"));

                for (WebElement element : elements) {
                    detailData += element.getText() + "\n";
                }
                log.info("detailData: {}", detailData);
            } catch (TimeoutException e) {
                log.warn("상세 데이터를 찾을 수 없습니다. 상세 데이터가 없는 것으로 간주합니다.");
            }

            LinkCareerContestsEntity existingEntity = crawlingMapper.findByUrlLinkCareerContest(detailUrl);
            log.info("existingEntity: {}", existingEntity);
            existingEntity.setDetailData(detailData);
            entitiesToUpdate.add(existingEntity);
        }
        // Batch save
        log.info("entitiesToUpdate 크기: {}", entitiesToUpdate.size());
        log.info("entitiesToUpdate: {}", entitiesToUpdate.get(0).getDetailData());
        log.info("여기서 멈춘건가?");
        crawlingMapper.saveAllLinkCareerContests(entitiesToUpdate);

        driver.close();
        driver.quit();
    }

    @Scheduled(cron = "0 0 0 * * *")
    @Override
    public void crawlActivitiesFromLinkCareer() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\samsung\\Desktop\\chromedriver-win64\\chromedriver.exe");
        WebDriver originalDriver = new ChromeDriver();
        RobustWebDriver driver = new RobustWebDriver(originalDriver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        int pageNum = 1;
        List<String> detailUrls = new ArrayList<>();
        Set<String> existingUrls = new HashSet<>(crawlingMapper.findAllLinkCareerActivitiesUrls());

        while(true) {
            final String activitiesUrl = "/list/activity?filterBy_interestIDs=13&filterType=INTEREST&orderBy_direction=DESC&orderBy_field=CREATED_AT&page=" + pageNum;
            log.info("pageNum: {}", pageNum);
            log.info("contestUrl: {}", activitiesUrl);

            driver.get(linkCareerUrl + activitiesUrl);
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list-body .activity-list-card-item-wrapper"))); // 요소가 로드될 때까지 대기
            } catch (TimeoutException e) {
                pageNum++;
                break;
            }
            List<WebElement> contests = driver.findElements(By.cssSelector(".list-body .activity-list-card-item-wrapper"));
            
            log.info("contests크기{}", contests.size());

            if (contests.isEmpty()) {
                log.info("더 이상 크롤링할 대외활동이 없습니다. 크롤링을 종료합니다.");
                break;
            }

            for (int i = 0; i < contests.size(); i++) {
                RobustWebElement contest = (RobustWebElement) contests.get(i);
                log.info("contest : {}", contest);
                String title = contest.findElement(By.cssSelector(".activity-title")).getText(); // 제목
                String url = contest.findElement(By.cssSelector(".card-content a")).getAttribute("href"); // 상세 링크
                log.info("url: {}", url);
                String image = contest.findElement(By.cssSelector(".image-link img")).getAttribute("src"); // 이미지
                String host = contest.findElement(By.cssSelector(".organization-name")).getText(); // 주최
                List<WebElement> dataList = contest.findElements(By.cssSelector(".card-content div"));
                log.info("dataList크기{}", dataList.size());
                
                String date = ((RobustWebElement) dataList.get(3)).getText();
                log.info("date: {}", date);
                if (date.startsWith("D+") || date.equals("오늘마감") || existingUrls.contains(url)) {
                    log.info("마감일이 D+로 시작하거나 오늘 마감인 데이터 뛰어넘음");
                    continue;
                }

                detailUrls.add(url);

                LocalDate parsedDate = parseDate(date);
                LinkCareerActivitiesEntity crawlingEntity = LinkCareerActivitiesEntity.builder()
                        .title(title)
                        .url(url)
                        .image(image)
                        .host(host)
                        .date(parsedDate)
                        .build();

                crawlingMapper.saveLinkCareerActivity(crawlingEntity);
                existingUrls.add(url);
            }
            pageNum++;
        }
        log.info("목록 페이지 크롤링이 완료되었습니다. 상세 페이지 크롤링을 시작합니다.");

        List<LinkCareerActivitiesEntity> entitiesToUpdate = new ArrayList<>();

        log.info("existingUrls 크기: {}", existingUrls.size());

        for (String detailUrl : existingUrls) {
            driver.get(detailUrl);
            String detailData = "";
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".responsive-element")));
                List<WebElement> elements = driver.findElements(By.cssSelector(".responsive-element"));

                for (WebElement element : elements) {
                    detailData += element.getText() + "\n";
                }
                log.info("detailData: {}", detailData);
            } catch (TimeoutException e) {
                log.warn("상세 데이터를 찾을 수 없습니다. 상세 데이터가 없는 것으로 간주합니다.");
            }

            LinkCareerActivitiesEntity existingEntity = crawlingMapper.findByUrlLinkCareerActivity(detailUrl);
            log.info("existingEntity: {}", existingEntity);
            existingEntity.setDetailData(detailData);
            entitiesToUpdate.add(existingEntity);
        }
        // Batch save
        log.info("entitiesToUpdate 크기: {}", entitiesToUpdate.size());
        log.info("여기서 멈춘건가?");
        crawlingMapper.saveAllLinkCareerActivities(entitiesToUpdate);

        driver.close();
        driver.quit();
    }

    private LocalDate parseDate(String date) {
        if (date.startsWith("D-")) {
            int days = Integer.parseInt(date.substring(2));
            return LocalDate.now().plusDays(days);
        } else if (date.startsWith("D+")) {
            int days = Integer.parseInt(date.substring(2));
            return LocalDate.now().minusDays(days);
        } else if (date.equals("오늘마감")) {
            return LocalDate.now(); // "오늘 마감"을 오늘 날짜로 처리
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(date, formatter);
        }
    }
}
