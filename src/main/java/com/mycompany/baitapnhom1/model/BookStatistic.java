package com.mycompany.baitapnhom1.model;

import lombok.Getter;

@Getter
public class BookStatistic {
    private final Long quantity;
    private final Long total;
    private final Long rest;
    private final Long borrowed;

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
