package com.mycompany.baitapnhom1.repository;

import com.mycompany.baitapnhom1.entity.BookEntity;
import com.mycompany.baitapnhom1.entity.BorrowFormEntity;
import com.mycompany.baitapnhom1.entity.ReturnState;
import com.mycompany.baitapnhom1.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BorrowBookRepository extends JpaRepository<BorrowFormEntity, String> {
    Optional<BorrowFormEntity> findByBorrowId(String id);

    @Query(value = "SELECT * FROM borrow_form_entity WHERE book_id= :bookId ORDER BY borrow_date", nativeQuery = true)
    List<BorrowFormEntity> findAllByBook(@Param("bookId") String bookId);

    @Query(value = "SELECT * FROM borrow_form_entity WHERE user_id= :userId ORDER BY borrow_date", nativeQuery = true)
    List<BorrowFormEntity> findAllByUser(@Param("userId") String userId);

    @Query(value = "SELECT * FROM borrow_form_entity order by user_id", nativeQuery = true)
    List<BorrowFormEntity> findAll();

    List<BorrowFormEntity> findAllByUserAndBook(UserEntity user, BookEntity book);

    @Query(value = "SELECT * FROM borrow_form_entity WHERE user_id = :userId AND book_id= :bookId ", nativeQuery = true)
    List<BorrowFormEntity> findAllByUserAndBook(
            @Param("userId") String userId,
            @Param("bookId") String bookId);

    @Modifying
    @Query(value = "DELETE FROM borrow_form_entity WHERE borrow_id= :id", nativeQuery = true)
    void deleteByBorrowId(@Param("id") String id);

    @Query(value = "SELECT COUNT(*) FROM borrow_form_entity WHERE state='EXPIRED'", nativeQuery = true)
    long findBorrowFormExpiredCount();

    List<BorrowFormEntity> findAllByState(ReturnState state);

    @Query(value = "select b from BorrowFormEntity b where b.expiredDate > :currentDate and b.state= :state")
    List<BorrowFormEntity> findExpiredBorrowedForm(
            @Param("currentDate") Date currentDate,
            @Param("state") ReturnState state);

    @Modifying
    @Query(value = "update BorrowFormEntity b set b.state= :newState where b.expiredDate < :currentDate and b.state= :state")
    void expireBorrowForm(
            @Param("newState") ReturnState newState,
            @Param("currentDate") Date currentDate,
            @Param("state") ReturnState state);
}
