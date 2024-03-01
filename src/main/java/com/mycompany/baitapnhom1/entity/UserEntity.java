package com.mycompany.baitapnhom1.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.util.ArrayList;
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
    private List<BorrowFormEntity> borrowList= new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Role role;

//    public void setRole(String role) {
//        this.role = Role.valueOf(role.toUpperCase());
//    }
}
