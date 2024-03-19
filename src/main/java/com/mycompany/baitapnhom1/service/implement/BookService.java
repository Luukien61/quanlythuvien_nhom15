package com.mycompany.baitapnhom1.service.implement;

import com.mycompany.baitapnhom1.entity.BookCategory;
import com.mycompany.baitapnhom1.entity.BookEntity;
import com.mycompany.baitapnhom1.model.BookFields;
import com.mycompany.baitapnhom1.model.BookStatistic;
import com.mycompany.baitapnhom1.model.ResultModel;
import com.mycompany.baitapnhom1.repository.BookRepository;
import com.mycompany.baitapnhom1.service.IBookService;
import com.mycompany.baitapnhom1.util.AppUtil;
import com.mycompany.baitapnhom1.util.CustomCallBackFunction;
import com.mycompany.baitapnhom1.util.JOptionPaneUtil;
import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
        try {
            return bookRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("An error occurs when trying to fetch books");
        }
    }

    @Override
    public BookEntity findBookByName(String name) {
        try {
            return bookRepository.findByBookName(name);
        } catch (Exception e) {
            throw new RuntimeException("An error occurs when trying to fetch books");
        }
    }

    public List<BookEntity> findBookByNameContaining(String name) {
        try {
            return bookRepository.findAllByBookNameContaining(name);
        } catch (Exception e) {
            throw new RuntimeException("An error occurs when trying to fetch books");

        }
    }

    @Override
    public List<BookEntity> findBooksByPublisher(String publisher) {
        try {
            return bookRepository.findAllByPublisher(publisher.trim());
        } catch (Exception e) {
            throw new RuntimeException("An error occurs when trying to fetch books");

        }
    }

    @Override
    public List<BookEntity> findBooksByTime(String month, String year) {
        try {
            year = year.trim();
            LocalDate firstDate, secondDate;
            if (month == null) {
                firstDate = LocalDate.of(Integer.parseInt(year), 1, 1);
                secondDate = LocalDate.of(Integer.parseInt(year), 12, 31);
                var date1 = Date.from(firstDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                var date2 = Date.from(secondDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                return bookRepository.findAllByPublishDate(date1, date2);
            } else {
                month = month.trim();
                firstDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), 1);
                var date1 = Date.from(firstDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                return bookRepository.findAllByPublishDate(date1);
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurs when trying to fetch books");
        }
    }

    @Override
    public List<BookEntity> findBooksByCategory(String category) {
        try {
            category = "%" + category.trim().toUpperCase() + "%";
            //var bookCategory = BookCategory.valueOf(category);
            return bookRepository.findAllByCategoryContaining(category);
        } catch (Exception e) {
            throw new RuntimeException("An error occurs when trying to fetch books");
        }
    }

    @Override
    public ResultModel saveNewBook(BookEntity newBook) {
        try {
            var existBook = bookRepository.findByBookName(newBook.getBookName().trim());
            if (existBook != null) {
                return ResultModel.builder()
                        .data(null)
                        .message("This book already exist.")
                        .build();
            }
            newBook = bookRepository.save(newBook);
            return ResultModel.builder()
                    .data(newBook)
                    .message("Added successfully")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("An error occurs when trying to fetch books");
        }
    }

    @Override
    public ResultModel updateBook(BookEntity newBook, String bookId) {
        try {
            var existBook = bookRepository.findByBookId(bookId).orElse(null);
            if (existBook != null) {
                existBook.setBookName(newBook.getBookName());
                existBook.setBookId(newBook.getBookId());
                existBook.setAuthor(newBook.getAuthor());
                existBook.setCategory(newBook.getCategory());
                existBook.setPublisher(newBook.getPublisher());
                existBook.setTotalQuantity(newBook.getTotalQuantity());
                existBook.setRestQuantity(newBook.getRestQuantity());
                existBook.setPublishDate(newBook.getPublishDate());
                existBook = bookRepository.save(existBook);
                return ResultModel.builder()
                        .data(existBook)
                        .message("Update successfully")
                        .build();
            } else {
                return ResultModel.builder()
                        .message("This book do not exist")
                        .build();
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurs when trying to fetch books");
        }
    }

    @Override
    public List<BookEntity> saveListBooks(List<BookEntity> items) {
        return bookRepository.saveAll(items);
    }

    @Override
    public void deleteBookByBookIdAndName(String bookId, String bookName) {
        try {
            var book = bookRepository.findByBookIdAndBookName(bookId.trim(), bookName.trim());
            bookRepository.delete(book);
        } catch (Exception e) {
            throw new RuntimeException("An error occurs when trying to delete this book");
        }
    }

    @Override
    public BookEntity findBookByBookId(String bookId) {
        return bookRepository.findByBookId(bookId.trim())
                .orElseThrow(() -> new RuntimeException("The book doesn't exist"));
    }

    @Override
    public List<BookEntity> findBookByAUthor(String author) {
        try {
            return bookRepository.findAllByAuthor(author.trim());
        } catch (Exception e) {
            throw new RuntimeException("An error occurs when trying to fetch books");

        }
    }

    @Override
    public List<BookEntity> findBookByYear(int year) {
        try {
            var localDate = LocalDate.of(year, 1, 1);
            var date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            return bookRepository.findByPublishDate(date);
        } catch (Exception e) {
            throw new RuntimeException("An error occurs when trying to fetch books");

        }
    }

    @Override
    public List<BookEntity> searchBookByField(BookFields field, String key) {
        List<BookEntity> items = new ArrayList<>();
        switch (field) {
            case BOOK_ID -> items.add(this.findBookByBookId(key));
            case NAME -> items.addAll(this.findBookByNameContaining(key));
            case CATEGORY -> items.addAll(this.findBooksByCategory(key));
            case AUTHOR -> items.addAll(this.findBookByAUthor(key));
            case PUBLISH_DATE -> items.addAll(this.findBookByYear(Integer.parseInt(key)));
            case PUBLISHER -> items.addAll(this.findBooksByPublisher(key));
        }
        return items;
    }

    @Override
    public void updateQuantity(BookEntity book, int borrowQuntity) {
        try {
            book.setRestQuantity(book.getRestQuantity() - borrowQuntity);
            bookRepository.save(book);
        } catch (Exception e) {
            throw new RuntimeException("An error occurs when updating the book");
        }
    }

    public BookStatistic getQuantityStatistic() {
        try {
            return bookRepository.findBookStatistic();
        } catch (Exception e) {
            throw new RuntimeException("An error occurs when fetching data");
        }
    }

    public void displayBooks(DefaultTableModel model, @Nullable CustomCallBackFunction function, List<BookEntity> items) {
        model.setRowCount(0);
        int index = 1;
        for (BookEntity book : items) {
            var date = book.getPublishDate();
            var data = new Object[]{
                    String.valueOf(index),
                    book.getBookId(), book.getBookName(), book.getTotalQuantity(),
                    book.getRestQuantity(), book.getAuthor(), book.getCategory(),
                    book.getPublisher(), AppUtil.getPublishDateString(date)
            };
            model.addRow(data);
            index += 1;
        }
        if (function != null) function.call();
    }

    public ResultModel saveNewBook(String bookId, String bookName, String author, String publisher, int quantity, BookCategory bookCategory, Date date) {
        BookEntity book = BookEntity.builder()
                .bookId(bookId)
                .bookName(bookName)
                .author(author)
                .publisher(publisher)
                .totalQuantity(quantity)
                .restQuantity(quantity)
                .category(bookCategory)
                .publishDate(date)
                .build();
        return saveNewBook(book);
    }

    public List<BookEntity> findBooks(String key, BookFields field) {
        List<BookEntity> items = new ArrayList<>();
        if (field == BookFields.PUBLISH_DATE) {
            try {
                var year = Integer.parseInt(key);
                items.addAll(findBookByYear(year));
            } catch (Exception e) {
                throw new RuntimeException("Please type the year correctly");
            }
        } else {
            try {
                var books = searchBookByField(field, key);
                if (books.getFirst() != null) {
                    items.addAll(books);
                } else throw new RuntimeException("Không tìm thấy sách");
            } catch (RuntimeException e) {
               throw new RuntimeException("An error occurs when finding books");
            }
        }
        return items;
    }
}
