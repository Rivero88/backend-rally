package com.rally.backend_rally.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rally.backend_rally.config.JwtUtil;
import com.rally.backend_rally.entities.LoginRequest;

//@RestController
//@RequestMapping("/images")
//public class ImageController {
//    @Autowired
//    private ImageService imageService;
//
//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
//        String imageUrl = imageService.save(file);
//        return ResponseEntity.ok(imageUrl);
//    }
//}
