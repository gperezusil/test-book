package com.test.exam.service.impl;

import com.test.exam.client.ServiceClient;
import com.test.exam.client.dto.ClientRequest;
import com.test.exam.client.dto.ClientResponse;
import com.test.exam.domain.Book;
import com.test.exam.domain.repo.IBookRepository;
import com.test.exam.dto.Request;
import com.test.exam.dto.Response;
import com.test.exam.service.IBookService;
import com.test.exam.util.exceptions.customs.ConflictException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j(topic = "BookService")
public class BookService implements IBookService {

    private final ServiceClient serviceClient;
    private final IBookRepository bookRepository;

    @Autowired
    public BookService(ServiceClient serviceClient, IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.serviceClient = serviceClient;
    }

    @Override
    public Response bookHouse(Request request) {
        log.info("Ingresando al metodo bookHouse");
        if(request.getDiscountCode()!=null){
            ClientRequest requestDiscount = ClientRequest.builder()
                    .userId(request.getId())
                    .houseId(request.getHouseId())
                    .discountCode(request.getDiscountCode())
                    .build();
            ClientResponse response = serviceClient.bookHouse(requestDiscount);
            if(response == null ||Boolean.FALSE.equals(response.getStatus())){
                log.error("Invalid discount");
                throw new ConflictException("Invalid discount");
            }
        }
        bookRepository.save(new Book(request));
        log.info("Book Accepted");
        return Response.builder()
                .code(200)
                .message("Book Accepted")
                .build();
    }
}
