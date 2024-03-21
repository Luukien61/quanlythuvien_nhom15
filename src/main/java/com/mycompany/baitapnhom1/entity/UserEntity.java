package com.mycompany.baitapnhom1.entity;


import com.mycompany.baitapnhom1.model.Role;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity  {
    @Id
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
