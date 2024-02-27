package com.mycompany.baitapnhom1.service;

import com.mycompany.baitapnhom1.entity.UserEntity;

import java.sql.SQLException;
import java.util.List;

public interface IUserService {
    boolean saveUser(UserEntity user) throws SQLException;
    UserEntity findUserByName(String userName) throws SQLException;
    UserEntity finduserById(String id) throws SQLException;
    boolean deleteUser(UserEntity user);
    boolean updateUser(UserEntity newUser) throws SQLException;
    List<UserEntity> findAllUser();
    UserEntity findUserByUserNameAndPassword(String userName, String password) throws SQLException;
}
