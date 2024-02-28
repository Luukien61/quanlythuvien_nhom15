package com.mycompany.baitapnhom1.service;

import com.mycompany.baitapnhom1.entity.BookEntity;
import com.mycompany.baitapnhom1.model.ResultModel;

import java.util.List;

public interface IBookService {
    List<BookEntity> findAllBook();
    BookEntity findBookByName(String name);
    BookEntity findBookById(String id);
    List<BookEntity> findBooksByPublisher(String publisher);
    List<BookEntity> findBooksByTime(String month, String year);
    List<BookEntity> findBooksByCategory(String category);
    ResultModel saveNewBook(BookEntity newBook);
    List<BookEntity> saveListBooks(List<BookEntity> items);
    ResultModel updateBook(BookEntity newBook);
    void deleteBookByBookIdAndName(String bookId, String bookName);
}
