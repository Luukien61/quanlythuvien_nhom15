/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.baitapnhom1;

import com.mycompany.baitapnhom1.entity.UserEntity;
import com.mycompany.baitapnhom1.repository.UserRepository;
import com.mycompany.baitapnhom1.service.implement.UserService;
import com.mycompany.baitapnhom1.view.DangNhap;
import lombok.AllArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author kienl
 */
@SpringBootApplication
@AllArgsConstructor
public class Baitapnhom1 {


    public static void main(String[] args) {
        var app = new SpringApplication(Baitapnhom1.class);
        app.setHeadless(false);
        app.setBannerMode(Banner.Mode.OFF);
        var context = app.run(args);
        UserService userService = context.getBean(UserService.class);
        //UserRepository userRepository = context.getBean(UserRepository.class);
//        var result = userRepository.save(UserEntity.builder()
//                .userName("kien")
//                .password("123456")
//                .build());
        DangNhap view = new DangNhap(userService);
        view.setVisible(true);
    }
}
