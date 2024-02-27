package com.mycompany.baitapnhom1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BorrowFormEntity extends BaseEntity {
    private String borrowId;
    private Date borrowDate;
    private Date expiredDate;
    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "borrowForm")
    private List<BookEntity> book;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user",referencedColumnName = "id")
    private UserEntity user;

}
