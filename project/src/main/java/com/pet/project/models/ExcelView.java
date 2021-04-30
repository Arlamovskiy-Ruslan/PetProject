package com.pet.project.models;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExcelView extends AbstractXlsView {

    @Override
    public void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        response.setHeader("Content-Disposition", "attachment; filename=\"userRecords-BD.xls\"");

        @SuppressWarnings("unchecked")
        List<UserRecord> user_rec= (List<UserRecord>) model.get("user_rec");

        Sheet sheet = workbook.createSheet("Users Detail");
        sheet.setDefaultColumnWidth(30);

        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        font.setColor(HSSFColor.BLACK.index);
        style.setFont(font);

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("First Name");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("Name");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("Last Name");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("Problem");
        header.getCell(3).setCellStyle(style);
        header.createCell(4).setCellValue("Mail");
        header.getCell(4).setCellStyle(style);
        header.createCell(5).setCellValue("Feedback");
        header.getCell(5).setCellStyle(style);

        int rowCount = 1;
        for(UserRecord userRecord : user_rec){
            Row userRow =  sheet.createRow(rowCount++);
            userRow.createCell(0).setCellValue(userRecord.getFirst_name());
            userRow.createCell(1).setCellValue(userRecord.getName());
            userRow.createCell(2).setCellValue(userRecord.getLast_name());
            userRow.createCell(3).setCellValue(userRecord.getProblem());
            userRow.createCell(4).setCellValue(userRecord.getMail());

        }

    }
}