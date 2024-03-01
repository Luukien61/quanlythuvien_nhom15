package com.mycompany.baitapnhom1.service;

import com.mycompany.baitapnhom1.entity.Role;
import com.mycompany.baitapnhom1.entity.UserEntity;

import java.sql.SQLException;
import java.util.List;

public interface IUserService {
    boolean saveUser(UserEntity user) throws SQLException;
    UserEntity findUserByName(String userName) throws SQLException;
    public List<UserEntity> fetchAllUser();
    UserEntity finduserByPersonalId(String id) throws SQLException;
    boolean deleteUser(UserEntity user);
    boolean updateUser(UserEntity newUser) throws SQLException;
    List<UserEntity> findAllUser();
    List<UserEntity> findAllUserByRole(Role role);
    UserEntity findUserByUserNameAndPassword(String userName, String password) throws SQLException;
}
