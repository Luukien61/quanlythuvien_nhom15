package com.mycompany.baitapnhom1.repository;

import com.mycompany.baitapnhom1.entity.Role;
import com.mycompany.baitapnhom1.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByPersonalId(String id);
    Optional<UserEntity> findByUserName(String userName);
    Optional<UserEntity> findByUserNameIgnoreCase(String userName);
    List<UserEntity> findAll();
    @Modifying
    @Query(value = "update user_entity set personal_id= :newPersonalId, password= :password, user_name= :userName,role= :role " +
            "where personal_id = :personalId",nativeQuery = true)
    void updateByPersonalId(
            @Param("newPersonalId") String newPersonalId,
            @Param("password") String password,
            @Param("userName") String userName,
            @Param("role") String role,
            @Param("personalId") String personalId);
    List<UserEntity> findAllByRole(Role role);
    Optional<UserEntity> findByUserNameAndPassword(String userName, String password);
    Optional<UserEntity> findByPersonalIdAndPassword(String id, String password);
}
