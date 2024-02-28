/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.baitapnhom1;

import com.mycompany.baitapnhom1.entity.Role;
import com.mycompany.baitapnhom1.entity.UserEntity;
import com.mycompany.baitapnhom1.repository.UserRepository;
import com.mycompany.baitapnhom1.service.implement.BookService;
import com.mycompany.baitapnhom1.service.implement.UserService;
import com.mycompany.baitapnhom1.util.AppUtil;
import com.mycompany.baitapnhom1.view.DangNhap;
import lombok.AllArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

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
        BookService bookService = context.getBean(BookService.class);
        initData(context);
        DangNhap view = new DangNhap(userService,bookService);
        view.setVisible(true);
    }

    private static void initData(ConfigurableApplicationContext context) {
        UserRepository userRepository = context.getBean(UserRepository.class);
        BookService bookService = context.getBean(BookService.class);
//        var result = userRepository.save(UserEntity.builder()
//                .userName("kien")
//                .password("123456")
//                .role(Role.ADMIN)
//                .personalId("CT060319")
//                .build());
//        userRepository.save(result);
//        var item = AppUtil.initialBooks();
//        bookService.saveListBooks(item);
    }

}
