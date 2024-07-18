package com.test.exam;

import com.test.exam.client.ServiceClient;
import com.test.exam.client.dto.ClientRequest;
import com.test.exam.client.dto.ClientResponse;
import com.test.exam.domain.Book;
import com.test.exam.domain.repo.IBookRepository;
import com.test.exam.dto.Request;
import com.test.exam.dto.Response;
import com.test.exam.service.impl.BookService;
import com.test.exam.util.exceptions.customs.ConflictException;
import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookServiceTest {

    @Mock
    private ServiceClient serviceClient;

    @Mock
    private IBookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testBookHouseValidInput() {
        Request request = new Request("35532", "John", "Doe", 25, "12345678901",
                new Date(), new Date(System.currentTimeMillis() + 86400000), "house123", null);

        Response response = bookService.bookHouse(request);

        assertEquals(200, response.getCode());
        assertEquals("Book Accepted", response.getMessage());

    }

    @Test
    void testBookHouseMissingName() {
        Request request = new Request("35532", null, "Doe", 25, "12345678901",
                new Date(), new Date(System.currentTimeMillis() + 86400000), "house123", null);

        Set<ConstraintViolation<Request>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testBookHouseInvalidAge() {
        Request request = new Request("35532", "John", "Doe", 17, "12345678901",
                new Date(), new Date(System.currentTimeMillis() + 86400000), "house123", null);

        Set<ConstraintViolation<Request>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testBookHouseInvalidPhoneNumber() {
        Request request = new Request("35532", "John", "Doe", 25, "12345",
                new Date(), new Date(System.currentTimeMillis() + 86400000), "house123", null);

        Set<ConstraintViolation<Request>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testBookHousePastStartDate() {
        Request request = new Request("35532", "John", "Doe", 25, "12345678901",
                new Date(System.currentTimeMillis() - 86400000), new Date(System.currentTimeMillis() + 86400000), "house123", null);

        Set<ConstraintViolation<Request>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testBookHousePastEndDate() {
        Request request = new Request("35532", "John", "Doe", 25, "12345678901",
                new Date(), new Date(System.currentTimeMillis() - 86400000), "house123", null);

        Set<ConstraintViolation<Request>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testBookHouseInvalidHouseId() {
        Request request = new Request("35532", "John", "Doe", 25, "12345678901",
                new Date(), new Date(System.currentTimeMillis() + 86400000), null, null);

        Set<ConstraintViolation<Request>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testBookHouseWithValidDiscountCode() {
        Request request = new Request("35532", "John", "Doe", 25, "12345678901",
                new Date(), new Date(System.currentTimeMillis() + 86400000), "house123", "DISCOUNT123");
        ClientResponse clientResponse = new ClientResponse("house123", "DISCOUNT123", 1, "user123", true);

        when(serviceClient.bookHouse(any(ClientRequest.class))).thenReturn(clientResponse);

        Response response = bookService.bookHouse(request);

        verify(bookRepository, times(1)).save(any(Book.class));
        assertEquals(200, response.getCode());
        assertEquals("Book Accepted", response.getMessage());
    }

    @Test
    void testBookHouseWithInvalidDiscountCode() {
        Request request = new Request("35532", "John", "Doe", 25, "12345678901",
                new Date(), new Date(System.currentTimeMillis() + 86400000), "house123", "INVALID_DISCOUNT");
        ClientResponse clientResponse = new ClientResponse("house123", "DISCOUNT123", 1, "user123", false);

        when(serviceClient.bookHouse(any(ClientRequest.class))).thenReturn(clientResponse);

        assertThrows(ConflictException.class, () -> bookService.bookHouse(request));

        verify(bookRepository, never()).save(any(Book.class));
    }
}
