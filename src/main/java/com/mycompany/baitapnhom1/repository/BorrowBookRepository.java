package com.mycompany.baitapnhom1.repository;

import com.mycompany.baitapnhom1.entity.BookEntity;
import com.mycompany.baitapnhom1.entity.BorrowFormEntity;
import com.mycompany.baitapnhom1.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BorrowBookRepository extends JpaRepository<BorrowFormEntity,String> {
    Optional<BorrowFormEntity> findByBorrowId(String id);
    Optional<List<BorrowFormEntity>> findAllByBook(BookEntity book);
    Optional<List<BorrowFormEntity>> findAllByUser(UserEntity user);
    @Query(value = "SELECT * FROM borrow_form_entity order by user",nativeQuery = true)
    List<BorrowFormEntity> findAll();
}
