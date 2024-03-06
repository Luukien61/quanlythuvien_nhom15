package com.mycompany.baitapnhom1.model;

import lombok.Getter;

@Getter
public class BookStatistic {
    private Long quantity,total,rest,borrowed;

    public BookStatistic(long quantity,long total, long rest) {
        this.quantity=quantity;
        this.total = total;
        this.rest = rest;
        this.borrowed=total-rest;
    }

    public static String[] getTitle(){
        return new String[]{"Số đầu sách","Tổng số lượng","Trong kho","Đã mượn"};
    }
}
