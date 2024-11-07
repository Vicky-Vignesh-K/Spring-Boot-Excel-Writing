package com.simple.connection.SimpleProject.service;

import com.simple.connection.SimpleProject.business.ExcelWritingBO;
import com.simple.connection.SimpleProject.configuration.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ExcelWritingService {

    @Autowired
    ExcelWritingBO excelWritingBO;

    @Autowired
    ResponseStructure responseStructure;

    public ResponseEntity<ResponseStructure> writeAllUserDetailsToExcelFile() {
        if (excelWritingBO.writeDataIntoExcel()) {
            responseStructure.setMessage("Excel File Written Successfully");
            responseStructure.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
        responseStructure.setMessage("Error in Writing Excel File");
        responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ResponseStructure> writeAllUserDetailsToCSVFile(){
        if(excelWritingBO.writeDataIntoCSVFile()){
            responseStructure.setMessage("CSV File Written Successfully");
            responseStructure.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
        responseStructure.setMessage("Error in Writing CSV File");
        responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ResponseStructure> writeAllUserDetailsToCSVFileUsingOpenCSV(){
        if(excelWritingBO.writeDataIntoCSVFileUsingOpenCSV()){
            responseStructure.setMessage("CSV File Written Successfully");
            responseStructure.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
        responseStructure.setMessage("Error in Writing CSV File");
        responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
    }

}
