package com.mycompany.baitapnhom1.service;

import com.mycompany.baitapnhom1.entity.Role;
import com.mycompany.baitapnhom1.entity.UserEntity;

import java.sql.SQLException;
import java.util.List;

public interface IUserService {
    void saveUser(UserEntity user) throws SQLException;
    UserEntity findUserByName(String userName) throws SQLException;
    public List<UserEntity> fetchAllUser(Role role);
    UserEntity findUserByPersonalId(String id) throws SQLException;
    boolean deleteUser(UserEntity user);
    public void deleteUserById(String id);
    void updateUser(UserEntity newUser, String id) throws SQLException;
    List<UserEntity> findAllUser();
    List<UserEntity> findAllUserByRole(Role role);
    UserEntity findUserByUserIdAndPassword(String id, String password) throws SQLException;

}
