package com.mycompany.baitapnhom1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatisticFilter {
    ALL("Chung"),
    EXPIRED("Quá hạn"),
    CATEGORY("Thể loại"),
    QUANTITY("Số lượng");
    private final String value;

    @Override
    public String toString() {
        return this.value;
    }
}
