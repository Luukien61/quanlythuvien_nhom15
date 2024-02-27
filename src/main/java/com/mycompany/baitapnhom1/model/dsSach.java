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
public class dsSach {
    List<Sach> lsSach = new ArrayList<>();
    
    public int add(Sach s){
        lsSach.add(s);
        return 1;
    }
    
    public int count(){
        return lsSach.size();
    }
    
    public List<Sach> getAllSach(){
        return lsSach;
    }
    
    public Sach findByID(String id){
        for(Sach s : lsSach){
            if(s.getMaSach().equals(id)){
                return s;
            }
        }
        return null;
    }
    
    public int del(String id){
        Sach s = findByID(id);
        if(s != null){
            lsSach.remove(s);
            return 1;
        }
        return -1;
    }
    
    public Sach getSachAtPoint(int position){
        return  lsSach.get(position);
    }
    
    public int edit(Sach sNew){
        for(Sach s : lsSach){
            if(s.getMaSach().equals(sNew.getMaSach())){
               s.setTenSach(sNew.getTenSach());
               s.setTenTacGia(sNew.getTenTacGia());
               s.setNhaXB(sNew.getNhaXB());
               s.setNamXB(sNew.getNamXB());
               s.setSoLuong(sNew.getSoLuong());
               s.setLoaiSach(sNew.getLoaiSach());
               return 1;
            }
        }
        return -1;
    }
}
