package org.mukhortov.requests;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mukhortov.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelWork {

    public void openFileAndRead(String fileLocation) {
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
        System.out.println("Проверяем считанное");
        for (List list :
                data.values()) {
            for (Object s :
                    list) {

                System.out.println(s);
            }
        }
    }
}

