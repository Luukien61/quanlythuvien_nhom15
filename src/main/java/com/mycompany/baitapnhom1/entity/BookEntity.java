package com.mycompany.baitapnhom1.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookEntity extends BaseEntity {
    @Column(unique = true)
    private String bookId;
    @Column(unique = true)
    private String bookName;
    private String author;
    private String publisher;
    private Date publishDate;
    private int totalQuantity;
    private int restQuantity;
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
