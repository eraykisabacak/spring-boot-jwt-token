package com.example.jwttoken.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private boolean flag;
    private Integer code;
    private String message;
    private Object data;


}
