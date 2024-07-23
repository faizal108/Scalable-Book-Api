package com.faizal108.bookService.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class ResponseModel {

    private HttpStatus status;
    private int statusCode;
    private Object data;

    public ResponseModel(HttpStatus status, int statusCode, Object data) {
        this.status = status;
        this.statusCode = statusCode;
        this.data = data;
    }
}
