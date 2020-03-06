package com.test.primes.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface PrimesApi {
    @RequestMapping(value = "/primes/{initial}",
        produces = { "application/json", "application/xml", "text/plain" },
        method = RequestMethod.GET)
    ResponseEntity fetchPrimes(@PathVariable("initial") Integer initial, @RequestParam(value = "algorithm", required = false) Optional<String> algorithm);
}
