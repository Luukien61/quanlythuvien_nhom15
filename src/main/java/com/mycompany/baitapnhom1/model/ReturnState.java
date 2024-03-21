package com.mycompany.baitapnhom1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReturnState {
    RETURNED("Đã trả"),
    NOT_YET("Chưa trả"),
    EXPIRED("Quá hạn");
    private final String state;

    @Override
    public String toString() {
        return this.getState();
    }
}
