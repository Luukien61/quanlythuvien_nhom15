package com.mycompany.baitapnhom1.service.implement;

import com.mycompany.baitapnhom1.entity.BookCategory;
import com.mycompany.baitapnhom1.entity.BookEntity;
import com.mycompany.baitapnhom1.model.BookFields;
import com.mycompany.baitapnhom1.model.ResultModel;
import com.mycompany.baitapnhom1.repository.BookRepository;
import com.mycompany.baitapnhom1.service.IBookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService implements IBookService {
    private BookRepository bookRepository;

    @Override
    public List<BookEntity> findAllBook() {
        try{
            return bookRepository.findAll();
        }catch (Exception e){
            throw new RuntimeException("An error occurs when trying to fetch books");
        }
    }

    @Override
    public BookEntity findBookByName(String name) {
        try{
            return bookRepository.findByBookName(name.trim());
        }catch (Exception e){
            throw new RuntimeException("An error occurs when trying to fetch books");
        }
    }

    @Override
    public BookEntity findBookById(String id) {
        try{
            return bookRepository.findByBookId(id.trim());
        }catch (Exception e){
            throw new RuntimeException("An error occurs when trying to fetch books");

        }
    }

    @Override
    public List<BookEntity> findBooksByPublisher(String publisher) {
        try{
            return bookRepository.findAllByPublisher(publisher.trim());
        }catch (Exception e){
            throw new RuntimeException("An error occurs when trying to fetch books");

        }
    }

    @Override
    public List<BookEntity> findBooksByTime(String month, String year) {
        try{
            year=year.trim();
            LocalDate firstDate,secondDate;
            if(month==null){
                firstDate= LocalDate.of(Integer.parseInt(year),1,1);
                secondDate= LocalDate.of(Integer.parseInt(year),12,31);
                var date1 = Date.from(firstDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                var date2 = Date.from(secondDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                return bookRepository.findAllByPublishDate(date1,date2);
            }else {
                month=month.trim();
                firstDate= LocalDate.of(Integer.parseInt(year),Integer.parseInt(month),1);
                var date1 = Date.from(firstDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                return bookRepository.findAllByPublishDate(date1);
            }
        }catch (Exception e){
            throw new RuntimeException("An error occurs when trying to fetch books");
        }
    }

    @Override
    public List<BookEntity> findBooksByCategory(String category) {
        try{
            category=category.trim().toUpperCase();
            var bookCategory = BookCategory.valueOf(category);
            return bookRepository.findAllByCategory(bookCategory);
        }catch (Exception e){
            throw new RuntimeException("An error occurs when trying to fetch books");
        }
    }

    @Override
    public ResultModel saveNewBook(BookEntity newBook) {
        try{
            var existBook = bookRepository.findByBookName(newBook.getBookName().trim());
            if(existBook!=null){
                return ResultModel.builder()
                        .data(null)
                        .message("This book already exist.")
                        .build();
            }
            newBook= bookRepository.save(newBook);
            return ResultModel.builder()
                    .data(newBook)
                    .message("Added successfully")
                    .build();
        }catch (Exception e){
            throw new RuntimeException("An error occurs when trying to fetch books");
        }
    }

    @Override
    public ResultModel updateBook(BookEntity newBook) {
        try{
            var existBook = bookRepository.findById(newBook.getId()).orElse(null);
            if(existBook!=null){
                existBook.setBookName(newBook.getBookName());
                existBook.setBookId(newBook.getBookId());
                existBook.setAuthor(newBook.getAuthor());
                existBook.setCategory(newBook.getCategory());
                existBook.setPublisher(newBook.getPublisher());
                existBook.setTotalQuantity(newBook.getTotalQuantity());
                existBook.setRestQuantity(newBook.getRestQuantity());
                existBook.setPublishDate(newBook.getPublishDate());
                existBook=bookRepository.save(existBook);
                return ResultModel.builder()
                        .data(existBook)
                        .message("Update successfully")
                        .build();
            }
            else {
                return ResultModel.builder()
                        .message("This book do not exist")
                        .build();
            }
        }catch (Exception e){
            throw new RuntimeException("An error occurs when trying to fetch books");
        }
    }

    @Override
    public List<BookEntity> saveListBooks(List<BookEntity> items) {
        return bookRepository.saveAll(items);
    }

    @Override
    public void deleteBookByBookIdAndName(String bookId, String bookName) {
        try{
            var book = bookRepository.findByBookIdAndBookName(bookId.trim(),bookName.trim());
            bookRepository.delete(book);
        }catch (Exception e){
            throw new RuntimeException("An error occurs when trying to delete this book");
        }
    }

    @Override
    public BookEntity findBookByBookId(String bookId) {
        try{
            return bookRepository.findByBookId(bookId.trim());
        }catch (Exception e){
            throw new RuntimeException("An error occurs when trying to fetch books");

        }
    }

    @Override
    public List<BookEntity> findBookByAUthor(String author) {
        try{
            return bookRepository.findAllByAuthor(author.trim());
        }catch (Exception e){
            throw new RuntimeException("An error occurs when trying to fetch books");

        }
    }

    @Override
    public List<BookEntity> findBookByYear(int year) {
        try{
            var localDate = LocalDate.of(year,1,1);
            var date= Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            return bookRepository.findByPublishDate(date);
        }catch (Exception e){
            throw new RuntimeException("An error occurs when trying to fetch books");

        }
    }

    @Override
    public List<BookEntity> searchBookByField(BookFields field, String key){
        List<BookEntity> items = new ArrayList<>();
        switch (field){
            case BOOK_ID -> items.add(this.findBookByBookId(key));
            case NAME -> items.add(this.findBookByName(key));
            case CATEGORY -> items.addAll(this.findBooksByCategory(key));
            case AUTHOR -> items.addAll(this.findBookByAUthor(key));
            case PUBLISH_DATE -> items.addAll(this.findBookByYear(Integer.parseInt(key)));
            case PUBLISHER -> items.addAll(this.findBooksByPublisher(key));
        }
        return items;
    }
}
