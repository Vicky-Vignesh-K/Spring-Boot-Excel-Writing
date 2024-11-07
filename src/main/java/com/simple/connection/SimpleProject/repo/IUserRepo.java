package com.simple.connection.SimpleProject.repo;

import com.simple.connection.SimpleProject.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<UserInfo,Long>
{
}
