package com.faizal108.userService.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/*
* This Class is Use for Returning the response as payload contain following response.
* HttpStatus (HttpStatus)
* StatusCode (Int)
* Data (Object)
* */
@Data
@NoArgsConstructor
public class ResponseModel implements Serializable {

    private HttpStatus status;
    private int statusCode;
    private Object data;

    public ResponseModel(HttpStatus status, int statusCode, Object data) {
        this.status = status;
        this.statusCode = statusCode;
        this.data = data;
    }
}
