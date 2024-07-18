package com.test.exam.util.exceptions;

import com.test.exam.util.exceptions.customs.*;
import com.test.exam.util.exceptions.dto.ErrorResponse;
import com.test.exam.util.exceptions.dto.ErrorResponseMethod;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Slf4j(topic = "REQUEST_SERVICE")
@ControllerAdvice
public class GlobalExceptionHandler {



   @ExceptionHandler(ErrorInternalException.class)
    public ResponseEntity<Object> handleException(HttpServletRequest request, ErrorInternalException ex) {
        ErrorResponse errorResponse = createErrorResponse(ex,HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleInternalServerErrors(RuntimeException ex) {
        ErrorResponse errorResponse = createErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(NoHandlerFoundException ex) {
        ErrorResponse errorResponse = createErrorResponse(ex, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> resourceNotFoundException( ResourceNotFoundException ex) {
        ErrorResponse errorResponse = createErrorResponse( ex, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleAuthorizationException( AuthorizationException ex) {
        ErrorResponse errorResponse = createErrorResponse( ex,HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(ForbiddenException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleForbiddenException( ForbiddenException ex) {
        ErrorResponse errorResponse = createErrorResponse (ex,HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleBadRequestException( BadRequestException ex) {
        ErrorResponse errorResponse = createErrorResponse( ex, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> conflictException( ConflictException ex) {
        ErrorResponse errorResponse = createErrorResponse( ex, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseMethod> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errorMessages = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorMessages.add(fieldName + ": " + errorMessage);
        });


        ErrorResponseMethod errorResponse = createErrorResponseMethod(HttpStatus.BAD_REQUEST,errorMessages);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    private ErrorResponse createErrorResponse( Exception ex,HttpStatus status) {
        log.error("Error: {}",ex.getMessage());
        return new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage()
        );
    }

    private ErrorResponseMethod createErrorResponseMethod(HttpStatus status,List<String> error) {
        log.error("Error: {}",error.toString());
        return new ErrorResponseMethod(
                status.value(),
                status.getReasonPhrase(),
               error
        );
    }
}
