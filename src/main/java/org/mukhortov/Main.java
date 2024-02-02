package org.mukhortov;

import org.mukhortov.requests.ExcelWork;
import org.mukhortov.requests.TwoGisRequest;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    Scanner scan = new Scanner(System.in);
    private String file;
    private int timeForControlInSec;




    public static void main(String[] args) {
        Main main = new Main();
        String file = main.getFile();

        ExcelWork excelWork = new ExcelWork();
        Map<Integer, List<String>> data = excelWork.openFileAndRead(file);

        TwoGisRequest tgRequest = new TwoGisRequest();
        data = tgRequest.transformData(data);

        excelWork.dataWriting(data);
    }

    private String getFile() {
        System.out.println("Введите полное имя файла для выгрузки и анализа данных:");
        try {
            file = scan.nextLine();
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Введено непонятно что");
        }
        System.out.println("Введен путь: " + file);

        return file;
    }
}