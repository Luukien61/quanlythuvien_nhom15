package com.mycompany.baitapnhom1.entity;


import lombok.*;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseEntity {
    private String personalId;
    private String userName;
    private String password;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user")
    private List<BorrowFormEntity> borrowList;
    @Enumerated(EnumType.STRING)
    private Role role;
}
