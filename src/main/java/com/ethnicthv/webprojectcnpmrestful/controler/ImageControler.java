package com.ethnicthv.webprojectcnpmrestful.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("ALL")
@RestController
@CrossOrigin(origins = "https://localhost:3000")
@RequestMapping("/image")
public class ImageControler {
    private static final String UPLOAD_DIR = "images";
    private static final Logger logger = LoggerFactory.getLogger(ImageControler.class);
    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        Path imgFile = Path.of("images/" + filename);
        logger.info("Sending image {}", imgFile);
        try {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(new InputStreamResource(new FileInputStream(imgFile.toFile())));
        } catch (IOException e) {
            logger.warn("Sending image that not exist:", e);
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<List<String>> uploadImage(@RequestParam("images") MultipartFile[] images) {
        logger.info("Uploading image!");
        try {
            List<String> res = new ArrayList<>();
            for (MultipartFile image : images) {
                byte[] bytes = image.getBytes();
                String name = UUID.randomUUID().toString();
                logger.info("Uploading name {}", name);
                var path = Path.of("images/" + name);
                var file = path.getParent().toFile();
                if(!file.exists()) {
                    file.mkdirs();
                }
                System.out.println(path.toAbsolutePath());
                Files.write(path, bytes, StandardOpenOption.CREATE);
                res.add(name);

            }
            logger.info("Completed upload!");
            System.out.println(res);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (IOException e) {
            logger.warn("Failed upload:", e);
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

}
