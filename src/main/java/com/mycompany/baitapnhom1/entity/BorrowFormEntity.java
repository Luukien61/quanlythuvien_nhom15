package com.mycompany.baitapnhom1.entity;

import com.mycompany.baitapnhom1.model.ReturnState;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BorrowFormEntity {
    @Id
    private String borrowId;
    private Date borrowDate;
    private Date expiredDate;
    private int quantity;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id",referencedColumnName = "bookId")
    private BookEntity book;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",referencedColumnName = "personalId")
    private UserEntity user;
    @Enumerated(EnumType.STRING)
    private ReturnState state;

}
