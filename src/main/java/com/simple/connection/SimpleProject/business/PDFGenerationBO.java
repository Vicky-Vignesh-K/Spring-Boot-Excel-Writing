package com.simple.connection.SimpleProject.business;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.simple.connection.SimpleProject.configuration.Status;
import com.simple.connection.SimpleProject.entity.UserInfo;
import com.simple.connection.SimpleProject.repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class PDFGenerationBO {

    @Autowired
    private IUserRepo repo;

    public boolean generatePDFAndWriteData() {
        boolean isPdfWritten;
        List<UserInfo> userList = repo.findAll();
        try {
            if (!CollectionUtils.isEmpty(userList)) {
                PdfWriter pdfWriter = new PdfWriter("D:\\Excel Files\\PDFCreationJava\\UserDetails.pdf");
                PdfDocument pdfDocument = new PdfDocument(pdfWriter);
                Document document = new Document(pdfDocument);
                List<String> headStrings = getHeaders();
                document.add(new Paragraph("\n"));
                userList.stream()
                        .filter(userInfo -> String.valueOf(Status.ACTIVE).equals(userInfo.getStatus().toUpperCase()))
                        .sorted(Comparator.comparing(UserInfo::getFirstName).thenComparing(UserInfo::getLastName))
                        .forEach(userInfo -> {
                            int i = 0;
                            document.add(new Paragraph(headStrings.get(i++) + " : " + userInfo.getId() + "\n"
                                    + headStrings.get(i++) + " : " + userInfo.getFirstName() + "\n"
                                    + headStrings.get(i++) + " : " + userInfo.getLastName() + "\n"
                                    + headStrings.get(i++) + " : " + userInfo.getContact() + "\n"
                                    + headStrings.get(i++) + " : " + userInfo.getDegree() + "\n"
                                    + headStrings.get(i++) + " : " + userInfo.getPassedOutYear() + "\n"
                                    + headStrings.get(i++) + " : " + userInfo.getAadhaarNo() + "\n"
                                    + headStrings.get(i++) + " : " + userInfo.getPanNo() + "\n"
                                    + headStrings.get(i++) + " : " + userInfo.getEmail() + "\n"
                                    + headStrings.get(i++) + " : " + userInfo.getStatus()
                            ));
                            document.add(new Paragraph("\n"));
                        });
                document.close();
                isPdfWritten = true;
            } else
                isPdfWritten = false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return isPdfWritten;
    }

    public List<String> getHeaders() {
        List<String> headers = new ArrayList<>();
        headers.add("User");
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
