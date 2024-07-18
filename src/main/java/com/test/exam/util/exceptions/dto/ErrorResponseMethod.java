package com.test.exam.util.exceptions.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorResponseMethod {
    private Integer statusCode;
    private String error;
    private List<String> message;
}
