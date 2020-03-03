package com.test.primes.controller;

import com.test.primes.api.PrimesApi;

import com.test.primes.enumerations.Algorithms;
import com.test.primes.model.Response;
import com.test.primes.service.PrimesGenerator;
import com.test.primes.util.AlgorithmUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
public class PrimesApiController implements PrimesApi {

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public PrimesApiController(HttpServletRequest request){
        this.request = request;
    }

    @Override
    public ResponseEntity fetchPrimes(Integer initial, Optional<String> algorithm) {

        PrimesGenerator eratosthenesPrimes;
        String alg = "default";
        if(algorithm.isPresent()){
            alg = algorithm.get();
        }

        switch(Algorithms.getValue(alg)){

            case Eratosthenes:
                eratosthenesPrimes = AlgorithmUtil::generateEratosthenesPrimes;
                break;

            default:
                eratosthenesPrimes = AlgorithmUtil::generateEratosthenesPrimes;
                break;
        }

        Optional<List<Integer>> primes = eratosthenesPrimes.fetchPrimes(initial);
        Response response = new Response();
        response.setInitial(initial);
        response.setPrimes(primes.get());

        return ResponseEntity.ok().body(response);
    }
}
