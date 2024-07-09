package com.faizal108.userService.utils;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {

    public static ResponseModel create(Object data, HttpStatus status) {
        return new ResponseModel(status,status.value(),data);
    }
}
