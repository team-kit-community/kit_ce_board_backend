package com.creativedesignproject.kumoh_board_backend.board.service.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.creativedesignproject.kumoh_board_backend.board.service.FileService;

@Service
public class FileServiceImpl implements FileService{
    @Value("${file.path}")
    private String filePath;

    @Value("${file.url}")
    private String fileUrl;

    @Override
    public String upload(MultipartFile file) {
        if (file.isEmpty())
            return null;

        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String saveFileName = uuid + extension;
        String savePath = filePath + saveFileName;

        try {
            // logger.info("savePath", savePath);
            file.transferTo(new File(savePath));
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        String url = fileUrl + saveFileName;
        return url;
    }

    @Override
    public Resource getImage(String fileName) {
        Resource resource = null;

        try {
            if (!Files.exists(Paths.get(filePath + fileName))) {
                throw new RuntimeException("File not found: " + fileName);
            }
            resource = new UrlResource("file:" + filePath + fileName);
            if (!resource.exists() || !resource.isReadable()) {
                throw new RuntimeException("Could not read file: " + fileName);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        return resource;
    }
}
