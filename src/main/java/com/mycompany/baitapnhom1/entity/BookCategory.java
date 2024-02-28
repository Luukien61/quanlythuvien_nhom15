package com.mycompany.baitapnhom1.entity;

public enum BookCategory {
    SCIENCE,
    MATHEMATICA,
    TEXTBOOK;

    @Override
    public String toString() {
        return this.name();
    }
}
