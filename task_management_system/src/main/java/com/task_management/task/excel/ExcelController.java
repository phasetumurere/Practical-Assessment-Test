package com.task_management.task.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.io.IOException;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportToExcel() throws IOException {
        // Create a new workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        // Add data to the sheet (replace this with your actual data)
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Column 1");
        headerRow.createCell(1).setCellValue("Column 2");

        Row dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue("Value 1");
        dataRow.createCell(1).setCellValue("Value 2");

        // Write the workbook content to a byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        // Set the response headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "data.xlsx");

        // Return the Excel file as a response
        return ResponseEntity.ok()
                .headers(headers)
                .body(outputStream.toByteArray());
    }
}