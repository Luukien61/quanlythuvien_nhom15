package com.mycompany.baitapnhom1.service;

import com.mycompany.baitapnhom1.entity.BookEntity;
import com.mycompany.baitapnhom1.model.BookFields;
import com.mycompany.baitapnhom1.model.ResultModel;

import java.util.List;

public interface IBookService {
    List<BookEntity> findAllBook();
    BookEntity findBookByName(String name);
    BookEntity findBookByBookId(String bookId);

    List<BookEntity> findBookByAUthor(String author);
    List<BookEntity> findBooksByPublisher(String publisher);
    List<BookEntity> findBooksByTime(String month, String year);
    List<BookEntity> findBooksByCategory(String category);
    ResultModel saveNewBook(BookEntity newBook);
    List<BookEntity> saveListBooks(List<BookEntity> items);
    ResultModel updateBook(BookEntity newBook);
    void deleteBookByBookIdAndName(String bookId, String bookName);
    List<BookEntity> findBookByYear(int year);
    List<BookEntity> searchBookByField(BookFields field, String key);

    void updateQuantity(BookEntity book,int borrowed);
}
