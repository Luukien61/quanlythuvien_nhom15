package com.mycompany.baitapnhom1.repository;

import com.mycompany.baitapnhom1.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByPersonalId(String id);
    Optional<UserEntity> findByUserName(String userName);
    List<UserEntity> findAll();
    Optional<UserEntity> findByUserNameAndPassword(String userName, String password);
}
