package com.mycompany.baitapnhom1.repository;

import com.mycompany.baitapnhom1.entity.BookCategory;
import com.mycompany.baitapnhom1.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findAll();
    @Query(value = "select * from book_entity where id= :id",nativeQuery = true)
    BookEntity findById(@Param("id") String id);
    List<BookEntity> findAllByAuthor(String author);
    List<BookEntity> findAllByCategory(BookCategory category);
    Optional<BookEntity> findByBookId(String bookId);
    List<BookEntity> findAllByPublisher(String publisher);
    List<BookEntity> findAllByPublishDate(Date date);
    BookEntity findByBookName(String name);
    List<BookEntity> findByPublishDate(Date date);
    BookEntity findByBookIdAndBookName(String id, String name);
    @Query(value = "select * from book_entity where publish_date between :firstdate and :seconddate",nativeQuery = true)
    List<BookEntity> findAllByPublishDate(@Param("firstdate") Date firstDate, @Param("seconddate") Date secondDate);
}
