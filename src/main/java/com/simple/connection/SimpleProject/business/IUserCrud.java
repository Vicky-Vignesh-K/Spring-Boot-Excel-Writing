package com.simple.connection.SimpleProject.business;

import com.simple.connection.SimpleProject.dto.User;
import com.simple.connection.SimpleProject.entity.UserInfo;
import org.springframework.stereotype.Component;

@Component
public interface IUserCrud {
    public User saveUser(User user);

    public UserInfo findUser(Long userId);

    public Integer deleteUser(Long userId);

    public UserInfo updateUserById(UserInfo userInfo);
}
