/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.baitapnhom1.model;

import java.util.ArrayList;
import java.util.List;

public class ListLoaiSach {
    List<LoaiSach> lsSach = new ArrayList<>();
    
    public ListLoaiSach(){
        lsSach.add(new LoaiSach("1","An toàn thông tin"));
        lsSach.add(new LoaiSach("2","Công nghệ thông tin"));
        lsSach.add(new LoaiSach("3","Điện tử viễn thông"));
        lsSach.add(new LoaiSach("4","Khoa cơ bản"));
        lsSach.add(new LoaiSach("5","Lý luận chính trị"));
        lsSach.add(new LoaiSach("6","Tài liệu tham khảo khác"));
    }
    
    public int add(LoaiSach s){
        lsSach.add(s);
        return 1;
    }
    
    public List<LoaiSach> getALLLoaiSach(){
        return lsSach;
    }
}
