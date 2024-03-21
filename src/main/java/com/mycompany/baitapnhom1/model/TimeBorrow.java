package com.mycompany.baitapnhom1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TimeBorrow {
    MONTH_1("1 tháng"),
    MONTH_2("2 tháng"),
    MONTH_3("3 tháng");
    private final String time;
}
