package com.creativedesignproject.kumoh_board_backend.CrawlingBoard.common;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class RobustWebElement implements WebElement {
    private WebElement originalElement;
    private RobustWebDriver driver;
    private By by;
    private static final int MAX_RETRIES = 10;

    public RobustWebElement(WebElement element, By by, RobustWebDriver driver) {
        this.originalElement = element;
        this.by = by;
        this.driver = driver;
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        int retries = 0;
        while (retries < MAX_RETRIES) {
            try {
                return this.originalElement.getScreenshotAs(target);
            } catch (StaleElementReferenceException ex) {
                refreshElement();
            }
            retries++;
        }
        throw new StaleElementReferenceException(
                String.format("Element is still stale after %s retries.", MAX_RETRIES));
    }

    @Override
    public void click() {
        int retries = 0;
        while (retries < MAX_RETRIES) {
            try {
                this.originalElement.click();
                return;
            } catch (StaleElementReferenceException ex) {
                refreshElement();
            }
            retries++;
        }
        throw new StaleElementReferenceException(String.format("Element is still stale after %s retries.", MAX_RETRIES));
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        int retries = 0;
        while (retries < MAX_RETRIES) {
            try {
                this.originalElement.sendKeys(keysToSend);
                return;
            } catch (StaleElementReferenceException ex) {
                refreshElement();
            }
            retries++;
        }
        throw new StaleElementReferenceException(
                String.format("Element is still stale after %s retries.", MAX_RETRIES));
    }

    @Override
    public void submit() {
        int retries = 0;
        while (retries < MAX_RETRIES) {
            try {
                this.originalElement.submit();
                return;
            } catch (StaleElementReferenceException ex) {
                refreshElement();
            }
            retries++;
        }
        throw new StaleElementReferenceException(
                String.format("Element is still stale after %s retries.", MAX_RETRIES));
    }

    @Override
    public void clear() {
        int retries = 0;
        while (retries < MAX_RETRIES) {
            try {
                this.originalElement.clear();
                return;
            } catch (StaleElementReferenceException ex) {
                refreshElement();
            }
            retries++;
        }
        throw new StaleElementReferenceException(
                String.format("Element is still stale after %s retries.", MAX_RETRIES));
    }

    @Override
    public String getTagName() {
        int retries = 0;
        while (retries < MAX_RETRIES) {
            try {
                return this.originalElement.getTagName();
            } catch (StaleElementReferenceException ex) {
                refreshElement();
            }
            retries++;
        }
        throw new StaleElementReferenceException(
                String.format("Element is still stale after %s retries.", MAX_RETRIES));
    }

    @Override
    public String getAttribute(String name) {
        int retries = 0;
        while (retries < MAX_RETRIES) {
            try {
                return this.originalElement.getAttribute(name);
            } catch (StaleElementReferenceException ex) {
                refreshElement();
            }
            retries++;
        }
        throw new StaleElementReferenceException(
                String.format("Element is still stale after %s retries.", MAX_RETRIES));
    }

    @Override
    public boolean isSelected() {
        int retries = 0;
        while (retries < MAX_RETRIES) {
            try {
                return this.originalElement.isSelected();
            } catch (StaleElementReferenceException ex) {
                refreshElement();
            }
            retries++;
        }
        throw new StaleElementReferenceException(
                String.format("Element is still stale after %s retries.", MAX_RETRIES));
    }

    @Override
    public boolean isEnabled() {
        int retries = 0;
        while (retries < MAX_RETRIES) {
            try {
                return this.originalElement.isEnabled();
            } catch (StaleElementReferenceException ex) {
                refreshElement();
            }
            retries++;
        }
        throw new StaleElementReferenceException(
                String.format("Element is still stale after %s retries.", MAX_RETRIES));
    }

    @Override
    public String getText() {
        int retries = 0;
        while (retries < MAX_RETRIES) {
            try {
                return this.originalElement.getText();
            } catch (StaleElementReferenceException ex) {
                refreshElement();
            }
            retries++;
        }
        throw new StaleElementReferenceException(
                String.format("Element is still stale after %s retries.", MAX_RETRIES));
    }

    @Override
    public List<WebElement> findElements(By by) {
        int retries = 0;
        while (retries < MAX_RETRIES) {
            try {
                List<WebElement> elements = new ArrayList<>();
                for (WebElement element : this.originalElement.findElements(by)) {
                    elements.add(new RobustWebElement(element, by, this.driver));
                }
                return elements;
            } catch (StaleElementReferenceException ex) {
                refreshElement();
            }
            retries++;
        }
        throw new StaleElementReferenceException(
                String.format("Element is still stale after %s retries.", MAX_RETRIES));
    }

    @Override
    public WebElement findElement(By by) {
        int retries = 0;
        while (retries < MAX_RETRIES) {
            try {
                return new RobustWebElement(this.originalElement.findElement(by), by, this.driver);
            } catch (StaleElementReferenceException ex) {
                refreshElement();
            }
            retries++;
        }
        throw new StaleElementReferenceException(
                String.format("Element is still stale after %s retries.", MAX_RETRIES));
    }

    @Override
    public boolean isDisplayed() {
        int retries = 0;
        while (retries < MAX_RETRIES) {
            try {
                return this.originalElement.isDisplayed();
            } catch (StaleElementReferenceException ex) {
                refreshElement();
            }
            retries++;
        }
        throw new StaleElementReferenceException(
                String.format("Element is still stale after %s retries.", MAX_RETRIES));
    }

    @Override
    public Point getLocation() {
        int retries = 0;
        while (retries < MAX_RETRIES) {
            try {
                return this.originalElement.getLocation();
            } catch (StaleElementReferenceException ex) {
                refreshElement();
            }
            retries++;
        }
        throw new StaleElementReferenceException(
                String.format("Element is still stale after %s retries.", MAX_RETRIES));
    }

    @Override
    public Dimension getSize() {
        int retries = 0;
        while (retries < MAX_RETRIES) {
            try {
                return this.originalElement.getSize();
            } catch (StaleElementReferenceException ex) {
                refreshElement();
            }
            retries++;
        }
        throw new StaleElementReferenceException(
                String.format("Element is still stale after %s retries.", MAX_RETRIES));
    }

    @Override
    public Rectangle getRect() {
        int retries = 0;
        while (retries < MAX_RETRIES) {
            try {
                return this.originalElement.getRect();
            } catch (StaleElementReferenceException ex) {
                refreshElement();
            }
            retries++;
        }
        throw new StaleElementReferenceException(
                String.format("Element is still stale after %s retries.", MAX_RETRIES));
    }

    @Override
    public String getCssValue(String propertyName) {
        int retries = 0;
        while (retries < MAX_RETRIES) {
            try {
                return this.originalElement.getCssValue(propertyName);
            } catch (StaleElementReferenceException ex) {
                refreshElement();
            }
            retries++;
        }
        throw new StaleElementReferenceException(
                String.format("Element is still stale after %s retries.", MAX_RETRIES));
    }

    private void refreshElement() {
        this.originalElement = driver.findElement(by);
    }
}