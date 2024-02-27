/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.baitapnhom1.model;

import java.util.Date;

/**
 *
 * @author DELL
 */
public class Sach {
    private String maSach;
    private String tenSach;
    private String tenTacGia;
    private String nhaXB;
    private Date namXB;
    private int soLuong;
    private LoaiSach loaiSach;

    public Sach() {
    }

    public Sach(String maSach, String tenSach, String tenTacGia, String nhaXB, Date namXB, int soLuong, LoaiSach loaiSach) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tenTacGia = tenTacGia;
        this.nhaXB = nhaXB;
        this.namXB = namXB;
        this.soLuong = soLuong;
        this.loaiSach = loaiSach;
    }

   

    
    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }


    public String getNhaXB() {
        return nhaXB;
    }

    public void setNhaXB(String nhaXB) {
        this.nhaXB = nhaXB;
    }

    public Date getNamXB() {
        return namXB;
    }

    public void setNamXB(Date namXB) {
        this.namXB = namXB;
    }

    public LoaiSach getLoaiSach() {
        return loaiSach;
    }

    public void setLoaiSach(LoaiSach loaiSach) {
        this.loaiSach = loaiSach;
    }

   
   

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "Sach{" + "maSach=" + maSach + ", tenSach=" + tenSach + ", tenTacGia=" + tenTacGia + ", nhaXB=" + nhaXB + ", namXB=" + namXB + ", soLuong=" + soLuong + ", loaiSach=" + loaiSach + '}';
    }
    
    
}
