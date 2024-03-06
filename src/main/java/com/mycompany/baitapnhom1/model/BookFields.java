package com.mycompany.baitapnhom1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BookFields {
    ID("id"),
    BOOK_ID("Mã sách"),
    NAME("Tên sách"),
    AUTHOR("Tác giả"),
    CATEGORY("Thể Loại"),
    PUBLISHER("Nhà xuất bản"),
    PUBLISH_DATE("Năm xuất bản"),
    TOTAL_QUANTITY("Tổng số lượng"),
    AVAILABLE_QUANTITY("Có sẵn");
    private final String fieldName;

    @Override
    public String toString() {
        return this.getFieldName();
    }
}
