package com.example.boottest.service.impl;

import com.example.boottest.service.DownloadService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

@Service("downloadService")
public class DownloadServiceImpl implements DownloadService {
    @Override
    public byte[] download() throws IOException {
        try (Workbook workbook = new SXSSFWorkbook();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("我的Excel");
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue(new Date());
            workbook.write(outputStream);
            byte[] bytes = outputStream.toByteArray();
            outputStream.flush();
            return bytes;
        }
    }
}