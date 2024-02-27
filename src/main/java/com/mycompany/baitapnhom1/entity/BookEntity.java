package com.mycompany.baitapnhom1.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookEntity extends BaseEntity {

    private String bookId;
    private String bookName;
    private String author;
    private String publisher;
    private Date publishDate;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private BookCategory category;
    @ManyToMany()
    @JoinTable(
            name = "book_borrow",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "borrow_id")
    )
    private List<BorrowFormEntity> borrowForm;
}
