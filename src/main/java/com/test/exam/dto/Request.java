package com.test.exam.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private String id;

    @NotBlank(message = "El nombre no puede estar en blanco")
    private String name;

    @NotBlank(message = "El apellido no puede estar en blanco")
    private String lastname;

    @NotNull(message = "La edad no puede ser nula")
    @Min(value = 18, message = "La edad mínima debe ser 18")
    @Max(value = 120, message = "La edad máxima debe ser 120")
    private Integer age;

    @Pattern(regexp = "\\d{11}", message = "El número de teléfono debe ser de 11 dígitos numéricos")
    private String phoneNumber;

    @NotNull(message = "La fecha de inicio no puede ser nula")
    @FutureOrPresent(message = "La fecha de inicio debe ser en el presente o en el futuro")
    private Date startDate;

    @NotNull(message = "La fecha de fin no puede ser nula")
    @Future(message = "La fecha de fin debe ser en el futuro")
    private Date endDate;

    @NotNull(message = "La houseId de fin no puede ser nula")
    private String houseId;

    private String discountCode;
}
