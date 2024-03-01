package com.mycompany.baitapnhom1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookCategory {
    SCIENCE(0),
    MATHEMATICA(1),
    TEXTBOOK(2);
    private final int index;
    @Override
    public String toString() {
        return this.name();
    }
}
