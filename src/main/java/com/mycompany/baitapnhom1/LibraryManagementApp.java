/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.baitapnhom1;

import com.mycompany.baitapnhom1.model.Role;
import com.mycompany.baitapnhom1.entity.UserEntity;
import com.mycompany.baitapnhom1.service.implement.BookService;
import com.mycompany.baitapnhom1.service.implement.BorrowBookService;
import com.mycompany.baitapnhom1.service.implement.UserService;
import com.mycompany.baitapnhom1.util.AppUtil;
import com.mycompany.baitapnhom1.view.LogInFrame;
import lombok.AllArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;

/**
 * @author kienl
 */
@SpringBootApplication
@AllArgsConstructor
public class LibraryManagementApp {


    public static void main(String[] args) {
        var app = new SpringApplication(LibraryManagementApp.class);
        app.setHeadless(false);
        app.setBannerMode(Banner.Mode.OFF);
        var context = app.run(args);
        UserService userService = context.getBean(UserService.class);
        BookService bookService = context.getBean(BookService.class);
        BorrowBookService borrowBookService = context.getBean(BorrowBookService.class);
        //initData(context);
        LogInFrame view = new LogInFrame(userService, bookService, borrowBookService);
        view.setVisible(true);
    }

    private static void initData(ConfigurableApplicationContext context) {
        UserService userService = context.getBean(UserService.class);
        BookService bookService = context.getBean(BookService.class);
        BorrowBookService borrowBookService = context.getBean(BorrowBookService.class);
        UserEntity admin = UserEntity.builder()
                .userName("kien")
                .password("123")
                .role(Role.ADMIN)
                .personalId("CT060319")
                .build();
        try {
            userService.saveUser(admin);
            userService.saveUser(UserEntity.builder()
                    .userName("Dat")
                    .password("123")
                    .personalId("CT060307")
                    .role(Role.MANAGER)
                    .build());
            userService.saveUser(UserEntity.builder()
                    .userName("Nga")
                    .password("123")
                    .personalId("CT060328")
                    .role(Role.MANAGER)
                    .build());
            userService.saveUser(UserEntity.builder()
                    .userName("Vuong")
                    .password("123")
                    .personalId("CT060333")
                    .role(Role.MANAGER)
                    .build());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        var item = AppUtil.initialBooks();
        bookService.saveListBooks(item);
        var borrowedItems = AppUtil.initialBorrowedBooks(item.getFirst(), admin);
        borrowBookService.saveNew(borrowedItems.getFirst());
    }

}
