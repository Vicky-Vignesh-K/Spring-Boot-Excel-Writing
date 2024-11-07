package com.simple.connection.SimpleProject.business;

import com.opencsv.CSVWriter;
import com.simple.connection.SimpleProject.configuration.Status;
import com.simple.connection.SimpleProject.entity.UserInfo;
import com.simple.connection.SimpleProject.repo.IUserRepo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ExcelWritingBO {

    @Autowired
    private IUserRepo repo;

    public boolean writeDataIntoExcel() {
        boolean isDataWritten = false;
        try (FileOutputStream destination = new FileOutputStream("D:\\Excel Files\\ExcelCreationJava\\UserDetails.xlsx");
             XSSFWorkbook workBook = new XSSFWorkbook()) {
            List<UserInfo> userList = repo.findAll();
            if (!CollectionUtils.isEmpty(userList)) {

                XSSFSheet sheet = workBook.createSheet("User Details");

                Font boldFont = workBook.createFont();
                boldFont.setBold(true);

                CellStyle boldStyle = workBook.createCellStyle();
                boldStyle.setFont(boldFont);
                List<String> headers = getHeaders();
                AtomicInteger rowCount = new AtomicInteger(0);
                Row rowHeader = sheet.createRow(rowCount.getAndIncrement());
                AtomicInteger columnCountHeader = new AtomicInteger(0);
                headers.forEach(header -> {
                    Cell cell = rowHeader.createCell(columnCountHeader.getAndIncrement());
                    cell.setCellValue(header);
                    cell.setCellStyle(boldStyle);
                });
                userList.stream()
                        .filter(userInfo -> String.valueOf(Status.ACTIVE).equals(userInfo.getStatus().toUpperCase()))
                        .sorted(Comparator.comparing(UserInfo::getFirstName).thenComparing(UserInfo::getLastName))
                        .forEach(userInfo -> {
                            int columnCount = 0;
                            Row row = sheet.createRow(rowCount.getAndIncrement());
                            row.createCell(columnCount++).setCellValue(userInfo.getId());
                            row.createCell(columnCount++).setCellValue(userInfo.getFirstName());
                            row.createCell(columnCount++).setCellValue(userInfo.getLastName());
                            row.createCell(columnCount++).setCellValue(userInfo.getContact());
                            row.createCell(columnCount++).setCellValue(userInfo.getDegree());
                            row.createCell(columnCount++).setCellValue(userInfo.getPassedOutYear());
                            row.createCell(columnCount++).setCellValue(userInfo.getAadhaarNo());
                            row.createCell(columnCount++).setCellValue(userInfo.getPanNo());
                            row.createCell(columnCount).setCellValue(userInfo.getEmail());
                            row.createCell(columnCount).setCellValue(userInfo.getStatus());
                        });
                workBook.write(destination);
                System.out.println("Data Copied from DataBase");
                isDataWritten = true;
            } else
                isDataWritten = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDataWritten;
    }

    public boolean writeDataIntoCSVFile() {
        boolean isDataWritten = false;
        try (FileWriter fileWriter = new FileWriter("D:\\Excel Files\\ExcelCreationJava\\CSV\\UserDetails.csv");
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            List<UserInfo> userList = repo.findAll();
            if (!CollectionUtils.isEmpty(userList)) {
                List<String> headers = getHeaders();
                headers.forEach(header -> printWriter.print(header + ", "));
                printWriter.println();
                userList.stream()
                        .filter(userInfo -> String.valueOf(Status.ACTIVE).equals(userInfo.getStatus().toUpperCase()))
                        .sorted(Comparator.comparing(UserInfo::getFirstName).thenComparing(UserInfo::getLastName))
                        .forEach(userInfo -> {
                            printWriter.print(userInfo.getId() + ", ");
                            printWriter.print(userInfo.getFirstName() + ", ");
                            printWriter.print(userInfo.getLastName() + ", ");
                            printWriter.print(userInfo.getContact() + ", ");
                            printWriter.print(userInfo.getDegree() + ", ");
                            printWriter.print(userInfo.getPassedOutYear() + ", ");
                            printWriter.print(userInfo.getAadhaarNo() + ", ");
                            printWriter.print(userInfo.getPanNo() + ", ");
                            printWriter.print(userInfo.getEmail());
                            printWriter.print((userInfo.getStatus()));
                            printWriter.println();
                        });
                System.out.println("Data Written in CSV File");
                isDataWritten = true;
            } else
                isDataWritten = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDataWritten;
    }

    public boolean writeDataIntoCSVFileUsingOpenCSV() {
        boolean isDataWritten = false;
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter("D:\\Excel Files\\ExcelCreationJava\\OpenCSV\\UserDetails.csv"))) {
            List<UserInfo> userInfoList = repo.findAll();
            if (!CollectionUtils.isEmpty(userInfoList)) {
                String[] header = {"User Id", "First Name", "Last Name", "Contact", "Degree", "Passed Out Year", "Aadhaar No", "Pan No", "Email ID", "Status"};
                csvWriter.writeNext(header);
                userInfoList.stream()
                        .filter(userInfo -> String.valueOf(Status.ACTIVE).equals(userInfo.getStatus().toUpperCase()))
                        .sorted(Comparator.comparing(UserInfo::getFirstName).thenComparing(UserInfo::getLastName))
                        .forEach(userInfo -> {
                            String[] userData = {String.valueOf(userInfo.getId()), userInfo.getFirstName(), userInfo.getLastName(),
                                    String.valueOf(userInfo.getContact()), userInfo.getDegree(), String.valueOf(userInfo.getPassedOutYear()),
                                    userInfo.getAadhaarNo(), userInfo.getPanNo(), userInfo.getEmail(), userInfo.getStatus()};
                            csvWriter.writeNext(userData);
                        });
                System.out.println("Data Written in CSV File using OpenCsv");
                isDataWritten = true;
            } else
                isDataWritten = false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return isDataWritten;
    }

    public List<String> getHeaders() {
        List<String> headers = new ArrayList<>();
        headers.add("User ID");
        headers.add("First Name");
        headers.add("Last Name");
        headers.add("Contact");
        headers.add("Degree");
        headers.add("Passed Out Year");
        headers.add("Aadhaar No");
        headers.add("Pan No");
        headers.add("Email Id");
        headers.add("Status");
        return headers;
    }

}
