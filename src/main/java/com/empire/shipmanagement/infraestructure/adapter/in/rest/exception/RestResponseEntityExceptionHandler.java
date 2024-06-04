package com.empire.shipmanagement.infraestructure.adapter.in.rest.exception;


import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex, final WebRequest request) {
        log.error("ConstraintViolationException: {} {}", ex.getMessage(), ex.getCause());
        final String bodyOfResponse = String.format("ERROR ConstraintViolationException: %s due to %s",
                ex.getMessage(), ex.getCause());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {
        log.error("DataIntegrityViolationException: {} {}", ex.getMessage(), ex.getCause());
        final String bodyOfResponse = String.format("ERROR DataIntegrityViolationException: %s due to %s",
                ex.getMessage(), ex.getCause());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("HttpMessageNotReadableException: {} {}", ex.getMessage(), ex.getCause());
        final String bodyOfResponse = String.format("ERROR HttpMessageNotReadableException: %s due to %s",
                ex.getMessage(), ex.getCause());
        return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        log.error("MethodArgumentNotValidException: {} {}", ex.getMessage(), ex.getCause());
        final String bodyOfResponse = String.format("ERROR MethodArgumentNotValidException: %s due to %s",
                ex.getMessage(), ex.getCause());
        return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
    }


    // 404

    @ExceptionHandler(value = {EntityNotFoundException.class, ResourceNotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request) {
        log.error("404 Status Code: {} {}", ex.getMessage(), ex.getCause());
        final String bodyOfResponse = String.format("ERROR 404: %s due to %s", ex.getMessage(), ex.getCause());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    // 409

    @ExceptionHandler({InvalidDataAccessApiUsageException.class, DataAccessException.class})
    protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
        log.error("409 Status Code: {} {}", ex.getMessage(), ex.getCause());
        final String bodyOfResponse = String.format("ERROR 409: %s due to %s", ex.getMessage(), ex.getCause());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    // 412

    // 500
    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
        log.error("500 Status Code: {} {}", ex.getMessage(), ex.getCause());
        final String bodyOfResponse = String.format("ERROR 500: %s due to %s", ex.getMessage(), ex.getCause());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }


}
