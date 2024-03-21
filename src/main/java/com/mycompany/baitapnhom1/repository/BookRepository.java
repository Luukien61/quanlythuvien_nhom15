package com.mycompany.baitapnhom1.repository;

import com.mycompany.baitapnhom1.model.BookCategory;
import com.mycompany.baitapnhom1.entity.BookEntity;
import com.mycompany.baitapnhom1.model.BookStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, String> {
    List<BookEntity> findAll();
    List<BookEntity> findAllByAuthor(String author);
    List<BookEntity> findAllByCategory(BookCategory category);
    @Query(value = "SELECT * FROM book_entity where category like :category",nativeQuery = true)
    List<BookEntity> findAllByCategoryContaining(@Param("category") String category);
    Optional<BookEntity> findByBookId(String bookId);
    List<BookEntity> findAllByPublisher(String publisher);
    List<BookEntity> findAllByPublishDate(Date date);
    List<BookEntity> findAllByBookNameContaining(String name);
    BookEntity findByBookName(String name);
    List<BookEntity> findByPublishDate(Date date);
    BookEntity findByBookIdAndBookName(String id, String name);
    @Query(value = "select * from book_entity where publish_date between :firstdate and :seconddate",nativeQuery = true)
    List<BookEntity> findAllByPublishDate(@Param("firstdate") Date firstDate, @Param("seconddate") Date secondDate);

    @Query(value = "select new com.mycompany.baitapnhom1.model.BookStatistic(count(b.bookId),sum(b.totalQuantity),sum(b.restQuantity)) from BookEntity b")
    BookStatistic findBookStatistic();
}
