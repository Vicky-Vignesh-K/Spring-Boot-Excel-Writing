package com.simple.connection.SimpleProject.business;

import com.simple.connection.SimpleProject.dto.User;
import com.simple.connection.SimpleProject.entity.UserInfo;
import com.simple.connection.SimpleProject.repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserCrud implements IUserCrud{

    @Autowired
    IUserRepo repo;

    @Override
    public User saveUser(User user) {
        if(user != null){
            UserInfo userInfo = new UserInfo();
            userInfo.setFirstName(user.getFirstName());
            userInfo.setLastName(user.getLastName());
            userInfo.setContact(user.getContact());
            userInfo.setDegree(user.getDegree());
            userInfo.setPassedOutYear(user.getPassedOutYear());
            userInfo.setEmail(user.getEmail());
            userInfo.setAadhaarNo(user.getAadhaarNo());
            userInfo.setPanNo(user.getPanNo());
            repo.save(userInfo);
            return user;
        }return null;
    }

    public UserInfo findUser(Long userID){
        if(userID != null){
            Optional<UserInfo> op = repo.findById(userID);
            if(op.isPresent()){
                UserInfo info = (UserInfo) op.get();
                return info;
            }
        }
        return null;
    }

    @Override
    public Integer deleteUser(Long userId) {
        if(userId != null){
            repo.deleteById(userId);
            return 1;
        }return 0;

    }

    @Override
    public UserInfo updateUserById(UserInfo userInfo) {
        if(userInfo != null) {
           UserInfo info =  repo.save(userInfo);
           if(info != null){
               return userInfo;
           }
           return null;
        }
        return null;
    }
}
