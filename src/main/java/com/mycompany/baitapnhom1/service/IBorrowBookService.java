package com.mycompany.baitapnhom1.service;

import com.mycompany.baitapnhom1.entity.BookEntity;
import com.mycompany.baitapnhom1.entity.BorrowFormEntity;
import com.mycompany.baitapnhom1.entity.UserEntity;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.function.Function;

public interface IBorrowBookService {
    void saveNew(BorrowFormEntity item);
    void saveNew(String bookId, String userId ,int quantity, int time);
    List<BorrowFormEntity> fetchAll();
    List<BorrowFormEntity> findAllByUser(String userId);
    List<BorrowFormEntity> findAllByBook(String bookId);
    BorrowFormEntity findByBorrowId(String id);
    void deleteItem(String id);
    List<BorrowFormEntity> findAllByUserIdAndBookId(String userId, String bookId);
    void returnBook(String borrowId);
    void displayData(DefaultTableModel model, List<BorrowFormEntity> items, Function<BookEntity,String> function);

}
