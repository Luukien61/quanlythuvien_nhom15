/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.baitapnhom1.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class UserList {
    List<User> ls = new ArrayList<>();

    public UserList() {
        ls.add(new User("admin","cntt@123"));
        ls.add(new User("quydat","cntt@123"));
        ls.add(new User("duchoang","cntt@123"));
        ls.add(new User("diemquynh","cntt@123"));
        ls.add(new User("tuananh","cntt@123"));
        ls.add(new User("vanmanh","cntt@123"));
    }
    
    public boolean checkLogin(String username, String password){
        for(User u : ls){
            if(u.getUserName().equals(username) 
                    && u.getPassword().equals(password))
            {
                return true;
            }   
        }
        return false;
    }
    
}
