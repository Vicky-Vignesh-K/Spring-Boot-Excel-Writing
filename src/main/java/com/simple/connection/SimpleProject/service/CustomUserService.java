package com.simple.connection.SimpleProject.service;

import com.simple.connection.SimpleProject.business.IUserCrud;
import com.simple.connection.SimpleProject.configuration.ResponseStructure;
import com.simple.connection.SimpleProject.dto.User;
import com.simple.connection.SimpleProject.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomUserService implements IUserService{

    @Autowired
    IUserCrud iUserCrud;

    @Autowired
    ResponseStructure structure;

    @Override
    public ResponseEntity<ResponseStructure> saveUser(User user) {
        User userSaved = iUserCrud.saveUser(user);
        if(userSaved != null) {
            structure.setData(userSaved);
            structure.setMessage("User saved Successfully");
            structure.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<ResponseStructure>(structure, HttpStatus.OK);
        }else{
            structure.setData(userSaved);
            structure.setMessage("Error in saving user");
            structure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
            return new ResponseEntity<ResponseStructure>(structure,HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public ResponseEntity<ResponseStructure> findUser(Long id){
        if(id != null){
           UserInfo info =  iUserCrud.findUser(id);
           if(info != null) {
               structure.setData(info);
               structure.setMessage("User Found");
               structure.setStatusCode(HttpStatus.FOUND.value());
               return new ResponseEntity<ResponseStructure>(structure,HttpStatus.FOUND);
           }else{
               structure.setData(id);
               structure.setMessage("User Not Found for the id : "+id);
               structure.setStatusCode(HttpStatus.NOT_FOUND.value());
               return new ResponseEntity<ResponseStructure>(structure,HttpStatus.NOT_FOUND);
           }
        }else{
            structure.setData(id);
            structure.setMessage("User id is null");
            structure.setStatusCode(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<ResponseStructure>(structure,HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ResponseStructure> deleteUser(Long userId) {
        if(userId != null){
            UserInfo info =  iUserCrud.findUser(userId);
            if(info != null) {
                if(iUserCrud.deleteUser(userId)>0) {
                    structure.setData(info);
                    structure.setMessage("User Deleted");
                    structure.setStatusCode(HttpStatus.OK.value());
                    return new ResponseEntity<ResponseStructure>(structure, HttpStatus.OK);
                }else{
                    structure.setData(info);
                    structure.setMessage("Error in Deleting User");
                    structure.setStatusCode(HttpStatus.NOT_MODIFIED.value());
                    return new ResponseEntity<ResponseStructure>(structure, HttpStatus.NOT_MODIFIED);
                }
            }else{
                structure.setData(userId);
                structure.setMessage("User Not Found for the id : "+userId);
                structure.setStatusCode(HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<ResponseStructure>(structure,HttpStatus.NOT_FOUND);
            }
        }else{
            structure.setData(userId);
            structure.setMessage("User id is null");
            structure.setStatusCode(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<ResponseStructure>(structure,HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ResponseStructure> updateUser(UserInfo userInfo, Long userId) {
        if(userId != null && userInfo != null) {
            UserInfo info = iUserCrud.findUser(userId);
            if(info != null){
                userInfo.setId(userId);
                UserInfo updated =  iUserCrud.updateUserById(userInfo);
                if(updated != null){
                    structure.setData(userInfo);
                    structure.setMessage("User Details Updated");
                    structure.setStatusCode(HttpStatus.OK.value());
                    return new ResponseEntity<ResponseStructure>(structure,HttpStatus.OK);
                }
            }else{
                structure.setData(userInfo);
                structure.setMessage("User Not Found for the id : "+userId);
                structure.setStatusCode(HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<ResponseStructure>(structure,HttpStatus.NOT_FOUND);
            }
        }
        structure.setData(userInfo);
        structure.setMessage("User Id is null");
        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<ResponseStructure>(structure,HttpStatus.NOT_FOUND);
    }
}
