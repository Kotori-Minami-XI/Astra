package com.Kotori.Parser;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

class Crew {
    private String department;
    private Double steps;
    private Integer count;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getSteps() {
        return steps;
    }

    public void setSteps(Double steps) {
        this.steps = steps;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Crew{" +
                "department='" + department + '\'' +
                ", steps=" + steps +
                ", count=" + count +
                '}';
    }
}

public class ExcelParser {
    HashMap<String, Crew> map = new HashMap<>();

    @Test
    public void parseExcel() throws IOException {
        readExcel();
        writeExcel();
    }

    public void readExcel() {
        try {
            // 读取excel
            String filename = "C:/Users/54356/Desktop/1.xls";
            File file = new File(filename);
            InputStream in = new FileInputStream(file);
            HSSFWorkbook wb = new HSSFWorkbook(in);


            for (int page = 0; page<=3; page++) {
                HSSFSheet sheet = wb.getSheetAt(page);
                int lastRowNum = sheet.getLastRowNum();

                for (int i = 1; i <= lastRowNum; i++) {
                    HSSFRow row = sheet.getRow(i);

                    String username = getStringValue(row.getCell(0));
                    String department = getStringValue(row.getCell(1));
                    Double steps = getDoubleValue(row.getCell(2));

                    if (map.containsKey(username)) {
                        Crew crew = (Crew) map.get(username);
                        crew.setCount(crew.getCount()+1);
                        crew.setSteps(crew.getSteps()+steps);
                        map.put(username,crew);
                    } else {
                        Crew crew = new Crew();
                        crew.setCount(1);
                        crew.setDepartment(department);
                        crew.setSteps(steps);
                        map.put(username,crew);
                    }
                }
            }

            // 打印
            for (Map.Entry<String, Crew> entry : map.entrySet()) {
                System.out.println(entry.getKey()+entry.getValue());
            }
            System.out.println(map.size());

            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeExcel() throws IOException {
        // Step 1: Create excel by Apache POI
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("员工数据");

        // Step 2: Set rows in the sheet
        HSSFRow headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("姓名");
        headerRow.createCell(1).setCellValue("部门");
        headerRow.createCell(2).setCellValue("上榜次数");
        headerRow.createCell(3).setCellValue("总步数");

        int rowNum = 1;
        for (Map.Entry<String, Crew> entry : map.entrySet()) {
            String username = entry.getKey();
            Crew crew = entry.getValue();
            String department = crew.getDepartment();
            Integer count = crew.getCount();
            Double steps = crew.getSteps();

            HSSFRow currentRow = sheet.createRow(rowNum);
            currentRow.createCell(0).setCellValue(username);
            currentRow.createCell(1).setCellValue(department);
            currentRow.createCell(2).setCellValue(count);
            currentRow.createCell(3).setCellValue(steps);
            rowNum++;
        }

        // Step 3: Response to the request
        String filename = new String("C:/Users/54356/Desktop/2.xls");
        FileOutputStream out = new FileOutputStream(filename);
        wb.write(out);
        out.close();
    }

    private String getStringValue(Cell cell){
        if (null == cell) {
            return null;
        }
        cell.setCellType(CellType.STRING);
        return cell.getRichStringCellValue().getString();
    }

    private Double getDoubleValue(Cell cell){
        if (null == cell) {
            return 0.0;
        }
        return cell.getNumericCellValue();
    }



}
