package com.test.exam.domain;

import com.test.exam.dto.Request;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Book {

    @Id
    private String id;
    private String name;
    private String lastname;
    private Integer age;
    private String phoneNumber;
    private Date startDate;
    private Date endDate;
    private String houseId;
    private String discountCode;

    public Book(Request request) {
        this.id = request.getId();
        this.name = request.getName();
        this.lastname = request.getLastname();
        this.age = request.getAge();
        this.phoneNumber = request.getPhoneNumber();
        this.startDate = request.getStartDate();
        this.endDate = request.getEndDate();
        this.houseId = request.getHouseId();
        this.discountCode = request.getDiscountCode();
    }

}
