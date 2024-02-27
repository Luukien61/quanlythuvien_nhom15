package com.mycompany.baitapnhom1.service.implement;

import com.mycompany.baitapnhom1.entity.UserEntity;
import com.mycompany.baitapnhom1.repository.UserRepository;
import com.mycompany.baitapnhom1.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private UserRepository userRepository;

    @Override
    public boolean saveUser(UserEntity user) throws SQLException {
        try {
            var existUser = userRepository.findByPersonalId(user.getPersonalId());
            if (existUser.isPresent()) {
                return false;
            }
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            throw new SQLException("Sorry, an error occurs when save user");
        }
    }

    @Override
    public UserEntity findUserByName(String userName) throws SQLException {
        try {
            return userRepository.findByUserName(userName).orElse(null);
        } catch (Exception e) {
            throw new SQLException("Sorry, an error occurs when finding the user");
        }
    }

    @Override
    public UserEntity finduserById(String id) throws SQLException {
        try {
            return userRepository.findByPersonalId(id).orElse(null);
        } catch (Exception e) {
            throw new SQLException("Sorry, an error occurs when finding the user");
        }
    }

    @Override
    public boolean deleteUser(UserEntity user) {
        try {
            userRepository.delete(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateUser(UserEntity newUser) throws SQLException {
        try {
            var existUser = userRepository.findByPersonalId(newUser.getPersonalId());
            if (existUser.isPresent()) {
                var user = existUser.get();
                user.setUserName(newUser.getUserName());
                user.setPassword(newUser.getPassword());
                user.setPersonalId(newUser.getPersonalId());
                user.setRole(newUser.getRole());
                userRepository.save(user);
                return true;
            } else {
                throw new SQLException("The User doesn't exist ");
            }
        } catch (SQLException e) {
            throw new SQLException("Sorry, an error occurs when finding the user");
        }
    }

    @Override
    public List<UserEntity> findAllUser() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Sorry, an error occurs when finding the user");
        }
    }

    @Override
    public UserEntity findUserByUserNameAndPassword(String userName, String password) throws SQLException {
        try{
            return userRepository.findByUserNameAndPassword(userName,password).orElse(null);
        }catch (Exception e){
            throw new SQLException("Sorry, an error occurs when finding the user");
        }
    }
}
