package com.ethnicthv.webprojectcnpmrestful.controler;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        ClassPathResource imgFile = new ClassPathResource("images/" + filename);
        try {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(new InputStreamResource(imgFile.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<List<String>> uploadImage(@RequestParam("images") MultipartFile[] images) {
        System.out.println("accecpt");
        try {
            List<String> res = new ArrayList<>();
            for (MultipartFile image : images) {
                byte[] bytes = image.getBytes();
                String name = UUID.randomUUID().toString();
                String path = new ClassPathResource("images/" + name).getPath();
                Path directoryPath = Path.of(path).getParent();
                if (!Files.exists(directoryPath)) {
                    Files.createDirectories(directoryPath);
                }
                System.out.println(directoryPath.toAbsolutePath());
                Files.write(Path.of(path), bytes, StandardOpenOption.CREATE);
                res.add(name);
            }
            System.out.println(res);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

}
