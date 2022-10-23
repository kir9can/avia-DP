package com.example.avia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData  {

    private String message;
    private HttpStatus httpStatus;
    private transient Object data;
}
