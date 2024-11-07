package com.simple.connection.SimpleProject.dto;


import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

@Component
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private Long contact;
    private String email;
    private String aadhaarNo;
    private String panNo;
    private String degree;
    private Integer passedOutYear;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAadhaarNo() {
        return aadhaarNo;
    }

    public void setAadhaarNo(String aadhaarNo) {
        this.aadhaarNo = aadhaarNo;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Integer getPassedOutYear() {
        return passedOutYear;
    }

    public void setPassedOutYear(Integer passedOutYear) {
        this.passedOutYear = passedOutYear;
    }
}
