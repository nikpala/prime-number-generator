package com.test.primes.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public interface ResponseEntityBuilder {

    ResponseEntity buildResponse(Object body, HttpStatus status, MediaType contentType);

}
