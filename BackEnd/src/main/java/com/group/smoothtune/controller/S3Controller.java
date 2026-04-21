package com.group.smoothtune.controller;

import com.group.smoothtune.infrastructure.storage.S3Service;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

//Clase de prueba
@RestController
@RequestMapping("/file")
public class S3Controller {

//    private final S3Service s3Service;
//
//    public S3Controller(S3Service s3Service) {
//        this.s3Service = s3Service;
//    }
//
//    @PostMapping("/upload")
//    public ResponseEntity<String> upload(@RequestParam("file")MultipartFile file) throws IOException{
//        s3Service.uploadFile(file);
//        return ResponseEntity.ok("Archivo subido exitosamente !");
//
//    }
//
//    @GetMapping("/download/{filename}")
//    public ResponseEntity<byte[]> download(@PathVariable String filename){
//
//        byte[] data = s3Service.downloadFile(filename);
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
//                .body(data);
//    }
}
