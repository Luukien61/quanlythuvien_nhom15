package com.mycompany.baitapnhom1.service.implement;

import com.mycompany.baitapnhom1.controller.GetUserController;
import com.mycompany.baitapnhom1.entity.Role;
import com.mycompany.baitapnhom1.entity.UserEntity;
import com.mycompany.baitapnhom1.repository.UserRepository;
import com.mycompany.baitapnhom1.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public UserEntity findUserByName(String userName) {
        try {
            return userRepository.findByUserName(userName.trim())
                    .orElseThrow(() -> new RuntimeException("There no result matching"));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public UserEntity findUserByPersonalId(String id) throws RuntimeException {
        try {
            if (id == null) {
                throw new RuntimeException("Please fill the require filed");
            }
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

    @Transactional
    public void deleteUserById(String id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public void updateUser(String newPersonalId, String userName, Role role, String password, String id) throws RuntimeException {
        try {
            var existUser = userRepository.findByPersonalId(id);
            if (existUser.isPresent()) {
                userRepository.updateByPersonalId(newPersonalId, password, userName, role.name(), id);
            } else {
                throw new RuntimeException("The user doesn't exist ");
            }
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("The user personal ID is identical with another user's ID");
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
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

    public UserEntity findByIdOrUserName(String key) {
        if (key == null) {
            throw new RuntimeException("Please fill the require field");
        }
        String regex = "^[CAD]T.*";
        Pattern idPattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = idPattern.matcher(key);
        if (matcher.matches()) {
            return findUserByPersonalId(key);
        } else return findUserByName(key);
    }
}
