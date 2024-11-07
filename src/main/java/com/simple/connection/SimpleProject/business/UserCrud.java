package com.simple.connection.SimpleProject.business;

import com.simple.connection.SimpleProject.configuration.Status;
import com.simple.connection.SimpleProject.entity.UserInfo;
import com.simple.connection.SimpleProject.repo.IUserRepo;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCrud {
    @Autowired
    IUserRepo repo;

    public UserInfo saveUserDetails(UserInfo userInfo) {
        if (userInfo != null) {
            userInfo.setId(null);
            if (StringUtils.isBlank(userInfo.getStatus()))
                userInfo.setStatus(String.valueOf(Status.ACTIVE));
            UserInfo saved = repo.save(userInfo);
            return saved;
        } else {
            return null;
        }

    }
}
