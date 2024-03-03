package com.mycompany.baitapnhom1.service;

import com.mycompany.baitapnhom1.entity.BookEntity;
import com.mycompany.baitapnhom1.entity.BorrowFormEntity;
import com.mycompany.baitapnhom1.entity.UserEntity;

import java.util.List;

public interface IBorrowBookService {
    void saveNew(BorrowFormEntity item);
    void saveNew(String bookId, String userId ,int quantity, int time);
    List<BorrowFormEntity> fetchAll();
    List<BorrowFormEntity> findAllByUser(UserEntity user);
    List<BorrowFormEntity> findAllByBook(BookEntity book);
    BorrowFormEntity findByBorrowId(String id);

}
