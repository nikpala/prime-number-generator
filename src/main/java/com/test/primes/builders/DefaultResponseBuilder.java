package com.test.primes.builders;

import com.test.primes.service.ResponseEntityBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class DefaultResponseBuilder implements ResponseEntityBuilder {

    @Override
    public ResponseEntity buildResponse(Object body, HttpStatus status, MediaType contentType) {
        return ResponseEntity.status(status).contentType(contentType).body(body);
    }

}
