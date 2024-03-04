package com.mycompany.baitapnhom1.service.implement;

import com.mycompany.baitapnhom1.api.GetUserController;
import com.mycompany.baitapnhom1.entity.Role;
import com.mycompany.baitapnhom1.entity.UserEntity;
import com.mycompany.baitapnhom1.repository.UserRepository;
import com.mycompany.baitapnhom1.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private UserRepository userRepository;

    @Override
    public void saveUser(UserEntity user) throws SQLException {
        try {
            var existUser = userRepository.findByPersonalId(user.getPersonalId());
            if (existUser.isPresent()) {
                throw new IllegalArgumentException("The user already exist");
            } else userRepository.save(user);
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
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
    public UserEntity findUserByPersonalId(String id) throws RuntimeException {
        try {
            return userRepository.findByPersonalId(id.trim().toUpperCase())
                    .orElseThrow(() -> new RuntimeException("The user doesn't exist"));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
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
    public void updateUser(UserEntity newUser, String id) throws SQLException {
        try {
            var existUser = userRepository.findByPersonalId(id);
            if (existUser.isPresent()) {
                var user = existUser.get();
                user.setUserName(newUser.getUserName());
                user.setPassword(newUser.getPassword());
                user.setPersonalId(newUser.getPersonalId());
                user.setRole(newUser.getRole());
                userRepository.save(user);
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
    public UserEntity findUserByUserIdAndPassword(String id, String password) throws SQLException {
        try {
            return userRepository.findByPersonalIdAndPassword(id.toUpperCase(), password).orElse(null);
        } catch (Exception e) {
            throw new SQLException("Sorry, an error occurs when finding the user");
        }
    }


    @Override
    public List<UserEntity> findAllUserByRole(Role role) {
        try {
            return userRepository.findAllByRole(role);
        } catch (Exception e) {
            throw new RuntimeException("Sorry, an error occurs when fetching users");
        }
    }

    @Override
    @Transactional
    public List<UserEntity> fetchAllUser(Role role) {
        List<UserEntity> users = GetUserController.getUser();
        users.forEach(user -> {
            try {
                saveUser(user);
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        });
        return findAllUserByRole(Role.USER);
    }
}
