package org.mukhortov.requests;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mukhortov.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelWork {

    public Map<Integer, List<String>> openFileAndRead(String fileLocation) {
        Workbook workbook = null;
        try {
            FileInputStream file = new FileInputStream(new File(fileLocation));
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            System.out.println("Файл не открывается " + e.getMessage());
        }

        Sheet sheet = workbook.getSheetAt(0);

        Map<Integer, List<String>> data = new HashMap<>();
        int i = 0;
        for (Row row : sheet) {
            data.put(i, new ArrayList<String>());
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
                        data.get(new Integer(i)).add(cell.getRichStringCellValue().getString());
                    break;
                    case NUMERIC: if (DateUtil.isCellDateFormatted(cell)) {
                        data.get(i).add(cell.getDateCellValue() + "");
                    } else {
                        data.get(i).add(cell.getNumericCellValue() + "");
                    }
                    break;
                    case BOOLEAN: data.get(i).add(cell.getBooleanCellValue() + "");
                    break;
                    case FORMULA: data.get(i).add(cell.getCellFormula() + "");
                    break;
                    default: data.get(new Integer(i)).add(" ");

            }
            }
            i++;
        }
//Поверяем данные
        /*System.out.println("Проверяем считанное");
        for (List list :
                data.values()) {
            for (Object s :
                    list) {

                System.out.println(s);
            }
        }*/
        return data;
    }

    public void dataWriting(Map<Integer, List<String>> data) {

        //создали книгу, видим фигу
        Workbook workbook = new XSSFWorkbook();
        System.out.println("нулевая строка: ");
        for (String s : data.get(0)) {
            System.out.print(s + "|");
        }
        int rowSize = data.get(0).size();
//лист
        Sheet sheet = workbook.createSheet("OSP Coordinates");
        sheet.setColumnWidth(0, rowSize);
        sheet.setColumnWidth(1, data.size());

        /*Row header = sheet.createRow(0);
//меняем стиль
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 14);
        font.setBold(true);
        headerStyle.setFont(font);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("OSP ID");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Address");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Lat");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(3);
        headerCell.setCellValue("Lon");
        headerCell.setCellStyle(headerStyle);*/

        //пишем содержимое
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        for (int i = 0; i < data.size(); i++) {
            List<String> tmpList = data.get(i);
            Row row = sheet.createRow(i);

            for (int x = 0; x < tmpList.size(); x++) {
                Cell cell = row.createCell(x);
                cell.setCellValue(tmpList.get(x));
                cell.setCellStyle(style);
            }
        }
        /*for (Map.Entry<Integer, List<String>> dataRow:
             data.entrySet()) {
            Row row = sheet.createRow(dataRow.getKey()+1);
            for (int x = 0; x < dataRow.getValue().size(); x++) {
                Cell cell = row.createCell(x);
                cell.setCellValue(dataRow.getValue().get(x));
                cell.setCellStyle(style);
            }
        }*/


//пишем в файл, надо в наш
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = "C:\\Users\\user\\Documents\\МОЛЛ\\" + "temp.xlsx";

        try {
            FileOutputStream outputStream = new FileOutputStream(fileLocation);
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            System.out.println("Запись не работает " + e.getMessage());
        }
    }
}

