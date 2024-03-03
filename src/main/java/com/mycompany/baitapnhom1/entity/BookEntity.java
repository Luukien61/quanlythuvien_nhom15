package com.mycompany.baitapnhom1.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookEntity  {
    @Id
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
    @OneToMany(mappedBy = "book",fetch = FetchType.LAZY)
    private List<BorrowFormEntity> borrows;

//    public int getRestQuantity() {
//        if (this.borrows == null || this.borrows.isEmpty()) {
//            return this.totalQuantity;
//        }
//        int borrowed = 0;
//        for (BorrowFormEntity item : borrows) {
//            borrowed += item.getQuantity();
//        }
//        return totalQuantity - borrowed;
//    }
}
