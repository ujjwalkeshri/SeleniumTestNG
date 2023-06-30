package com.testngSelenium.utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ReadLoginDetails {

    public static List<LoginData> loginDataList = new LinkedList<>();

    public void readData() throws IOException, URISyntaxException {
        URL url = ReadLoginDetails.class.getClassLoader().getResource("data/LoginDetails.xlsx");
        File file = Paths.get(url.toURI()).toFile();

        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook(fis);

        XSSFSheet sheet = wb.getSheetAt(0);

        for (Row row : sheet) {
            LoginData loginData = new LoginData();

            if (row.getRowNum() == 0) {
                continue;
            }

            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (cell.getColumnIndex() == 0) {
                    loginData.setUsername(cell.getStringCellValue());
                } else if (cell.getColumnIndex() == 1) {
                    loginData.setPassword(cell.getStringCellValue());
                }
            }
            loginDataList.add(loginData);
        }
        fis.close();
    }

   /* public static void main(String[] args) throws IOException, URISyntaxException {
        new ReadLoginDetails().readData();
    }*/
}
