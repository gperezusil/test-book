package com.test.exam.util.exceptions.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorResponse {
   private Integer statusCode;
   private String error;
   private String message;

}
