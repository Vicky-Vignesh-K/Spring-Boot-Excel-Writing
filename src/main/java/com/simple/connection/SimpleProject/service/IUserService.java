package com.simple.connection.SimpleProject.service;

import com.simple.connection.SimpleProject.configuration.ResponseStructure;
import com.simple.connection.SimpleProject.dto.User;
import com.simple.connection.SimpleProject.entity.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface IUserService {

    public ResponseEntity<ResponseStructure> saveUser(User user);

    public ResponseEntity<ResponseStructure> findUser(Long id);

    public ResponseEntity<ResponseStructure> deleteUser(Long userId);

    public ResponseEntity<ResponseStructure> updateUser(UserInfo userInfo, Long userId);
}
