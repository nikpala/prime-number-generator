package com.test.primes.controller;

import com.test.primes.builders.ResponseBuilderFactory;
import com.test.primes.enumerations.Algorithms;
import com.test.primes.api.PrimesApi;

import com.test.primes.config.CacheHelper;
import com.test.primes.service.ResponseEntityBuilder;
import com.test.primes.model.ErrorResponse;
import com.test.primes.model.PrimesResponse;
import com.test.primes.service.PrimesGenerator;
import com.test.primes.util.AlgorithmUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

@RestController
public class PrimesApiController implements PrimesApi {

    private final HttpServletRequest request;
    private CacheHelper primesCache;
    private ResponseBuilderFactory responseBuilderFactory;

    @org.springframework.beans.factory.annotation.Autowired
    public PrimesApiController(HttpServletRequest request, CacheHelper primesCache, ResponseBuilderFactory responseBuilderFactory){
        this.request = request;
        this.primesCache = primesCache;
        this.responseBuilderFactory = responseBuilderFactory;
    }

    @Override
    public ResponseEntity fetchPrimes(Integer initial, Optional<String> algorithm){

        String acceptType = request.getHeader(HttpHeaders.ACCEPT);
        MediaType contentType = determineResponseContentType(acceptType);

        String alg =  null;
        if(algorithm.isPresent()){
            alg = algorithm.get();
        }

        Algorithms primeAlg = Algorithms.getValue(alg);

        try{

            if(initial < 2){
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setError("Please enter a positive integer greater than 1.");

                ResponseEntityBuilder entityBuilder = responseBuilderFactory.getResponseEntityBuilder(contentType);
                return entityBuilder.buildResponse(errorResponse, HttpStatus.BAD_REQUEST, contentType);
            }

            PrimesResponse primesResponse = checkCacheForResponse(initial, primeAlg);

            if(primesResponse == null){
                primesResponse = executeAlgorithmForPrimes(initial, primeAlg);
            }

            ResponseEntityBuilder entityBuilder = responseBuilderFactory.getResponseEntityBuilder(contentType);
            return entityBuilder.buildResponse(primesResponse, HttpStatus.OK, contentType);
        } catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            StringBuffer sb = new StringBuffer()
                                    .append("Unable to generate primes for limit ")
                                    .append(initial)
                                    .append(" using algorithm ")
                                    .append(primeAlg.getAlg())
                                    .append(". Please try again with a different algorithm.");

            errorResponse.setError(sb.toString());

            ResponseEntityBuilder entityBuilder = responseBuilderFactory.getResponseEntityBuilder(contentType);
            return entityBuilder.buildResponse(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR, contentType);
        }
    }

    private PrimesResponse checkCacheForResponse(int initial, Algorithms primeAlg){

        PrimesResponse primesResponse = null;

        if (primesCache.getPrimesCache().containsKey(primeAlg)) {
            primesResponse = primesCache.getPrimesCache().get(primeAlg);

            if(primesResponse.getInitial() > initial){
                Set<Integer> primes = primesResponse.getPrimes().parallelStream().filter(i -> i<=initial).collect(Collectors.toCollection(
                                ConcurrentSkipListSet::new));
                primesResponse.setPrimes(primes);
                primesResponse.setInitial(initial);
            } else if(primesResponse.getInitial() < initial){
                primesResponse = null;
            }
        }

        return primesResponse;
    }

    private PrimesResponse executeAlgorithmForPrimes(int initial, Algorithms primeAlg) throws Exception{
        PrimesResponse primesResponse = new PrimesResponse();
        PrimesGenerator primesGenerator;

        switch(primeAlg){

            case SIEVE_OF_ERATOSHENES:

                primesGenerator = AlgorithmUtil::sieveOfEratosthenes;
                break;

            case SIEVE_OF_SUNDARAM:

                primesGenerator = AlgorithmUtil::sieveOfSundaram;
                break;

            case SIEVE_OF_ATKIN:
            default:
                primesGenerator = AlgorithmUtil::sieveOfAtkin;
                break;

        }

        Optional<Set<Integer>> primes = primesGenerator.fetchPrimes(initial);
        primesResponse.setInitial(initial);
        primesResponse.setPrimes(primes.get());
        primesCache.getPrimesCache().put(primeAlg, primesResponse);

        return primesResponse;
    }

    private MediaType determineResponseContentType(String acceptType) {

        MediaType contentType = null;

        if(acceptType == null || acceptType.contains(MediaType.ALL_VALUE) || acceptType.contains(MediaType.APPLICATION_JSON_VALUE)) {
            contentType = MediaType.APPLICATION_JSON;
        }else if(acceptType.contains(MediaType.APPLICATION_XML_VALUE)){
            contentType = MediaType.APPLICATION_XML;
        } else if(acceptType.contains(MediaType.TEXT_PLAIN_VALUE)){
            contentType = MediaType.TEXT_PLAIN;
        }

        return contentType;
    }
}
