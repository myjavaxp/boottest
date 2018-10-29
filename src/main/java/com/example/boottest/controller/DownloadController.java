package com.example.boottest.controller;

import com.example.boottest.service.DownloadService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/download")
public class DownloadController {
    @Resource
    private DownloadService downloadService;

    @GetMapping("/excel")
    public ResponseEntity<byte[]> excel() throws IOException {
        byte[] downLoad = downloadService.download();
        String fileName = new String("我的Excel.xlsx".getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(downLoad, headers, HttpStatus.OK);
    }
}