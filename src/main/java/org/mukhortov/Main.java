package org.mukhortov;

import org.mukhortov.requests.ExcelWork;
import org.mukhortov.requests.PiwiRequest;

import java.util.Scanner;

public class Main {
    Scanner scan = new Scanner(System.in);
    private String file;
    private int timeForControlInSec;


    public static void main(String[] args) {
        Main main = new Main();
        String file = main.getFile();

        ExcelWork excelWork = new ExcelWork();
        excelWork.openFileAndRead(file);



    }

    private String getFile() {
        System.out.println("Введите полное имя файла для выгрузки анализа данных:");
        try {
            file = scan.nextLine();
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Введено непонятно что");
        }
        System.out.println("Введен путь: " + file);

        return file;
    }
}