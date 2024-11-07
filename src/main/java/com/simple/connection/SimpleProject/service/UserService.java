package com.simple.connection.SimpleProject.service;

import com.simple.connection.SimpleProject.business.UserCrud;
import com.simple.connection.SimpleProject.configuration.ResponseStructure;
import com.simple.connection.SimpleProject.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserCrud crud;
    @Autowired
    ResponseStructure structure;
    public ResponseEntity<ResponseStructure> saveUser(UserInfo userInfo){
       UserInfo info =  crud.saveUserDetails(userInfo);
       if(info != null){
           structure.setMessage("User saved");
           structure.setData(userInfo);
           structure.setStatusCode(HttpStatus.OK.value());
           return new ResponseEntity<>(structure,HttpStatus.OK);
       }else{
           structure.setMessage("Error in saving User");
           structure.setData(userInfo);
           structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
           return new ResponseEntity<>(structure,HttpStatus.BAD_REQUEST);
       }
    }
}
