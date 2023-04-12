package com.ethnicthv.webprojectcnpmrestful.controler;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@SuppressWarnings("ALL")
@RestController
public class ImageControler {

    @RequestMapping("/image/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        ClassPathResource imgFile = new ClassPathResource("images/" + filename);
        try {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(new InputStreamResource(imgFile.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
