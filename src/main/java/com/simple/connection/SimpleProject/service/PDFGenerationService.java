package com.simple.connection.SimpleProject.service;

import com.simple.connection.SimpleProject.business.PDFGenerationBO;
import com.simple.connection.SimpleProject.configuration.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PDFGenerationService {

    @Autowired
    private PDFGenerationBO pdfGenerationBO;

    @Autowired
    private ResponseStructure responseStructure;

    public ResponseEntity<ResponseStructure> generatePdf(){
            if (pdfGenerationBO.generatePDFAndWriteData()) {
                responseStructure.setMessage("PDF File Written Successfully");
                responseStructure.setStatusCode(HttpStatus.OK.value());
                return new ResponseEntity<>(responseStructure, HttpStatus.OK);
            }
            responseStructure.setMessage("Error in Writing PDF File");
            responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);

    }

}
