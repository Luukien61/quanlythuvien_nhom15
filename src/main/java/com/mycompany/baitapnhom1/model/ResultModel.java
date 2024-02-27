package com.mycompany.baitapnhom1.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResultModel {
    private Object data;
    private String message;
}
