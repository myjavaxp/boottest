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
        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet("我的Excel");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue(new Date());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            workbook.write(outputStream);
            byte[] bytes = outputStream.toByteArray();
            outputStream.flush();
            return bytes;
        } finally {
            workbook.close();
            outputStream.close();
        }
    }
}