package com.mycompany.baitapnhom1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BorrowFormEntity {
    @Id
    private String borrowId;
    private Date borrowDate;
    private Date expiredDate;
    private int quantity;
    @ManyToOne(fetch = FetchType.EAGER)
    private BookEntity book;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user",referencedColumnName = "id")
    private UserEntity user;
    @Enumerated(EnumType.STRING)
    private ReturnState state;

}
