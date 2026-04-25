package com.group.smoothtune.adapter.in.rest;

import org.springframework.web.bind.annotation.*;

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
