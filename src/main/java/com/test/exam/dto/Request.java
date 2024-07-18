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
    @Size(min = 9, max = 10, message = "El id debe ser de 8 dígitos numéricos")
    private String id;

    @NotBlank(message = "El nombre no puede estar en blanco")
    @Size(min = 2, max = 50, message = "El nombre debe ser de 2 a 50 dígitos")
    private String name;

    @NotBlank(message = "El apellido no puede estar en blanco")
    private String lastname;

    @NotNull(message = "La edad no puede ser nula")
    @Min(value = 18, message = "La edad mínima debe ser 18")
    @Max(value = 100, message = "La edad máxima debe ser 100")
    private Integer age;

    @Size(min = 9, max = 20, message = "El phoneNumber debe ser de 9 a 20 dígitos numéricos")
    private String phoneNumber;

    @NotNull(message = "La fecha de inicio no puede ser nula")
    @FutureOrPresent(message = "La fecha de inicio debe ser en el presente o en el futuro")
    private Date startDate;

    @NotNull(message = "La fecha de fin no puede ser nula")
    @Future(message = "La fecha de fin debe ser en el futuro")
    private Date endDate;

    @NotNull(message = "La houseId de fin no puede ser nula")
    @Size(min = 6, max = 15, message = "El houseId debe ser de 6 a 15 dígitos numéricos")
    private String houseId;

    @Size(min = 8, max = 8, message = "El discountCode debe ser de 8 dígitos numéricos")
    private String discountCode;
}
