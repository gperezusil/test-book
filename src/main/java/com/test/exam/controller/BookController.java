package com.test.exam.controller;

import com.test.exam.dto.Request;
import com.test.exam.dto.Response;
import com.test.exam.service.IBookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bideafactory")
public class BookController {

    private final IBookService bookService;

    @Autowired
    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    public Response bookHouse(@Valid @RequestBody  Request request) {

       return bookService.bookHouse(request);

    }
}
