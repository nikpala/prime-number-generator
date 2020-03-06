package com.test.primes.controller;

import com.test.primes.builders.DefaultResponseBuilder;
import com.test.primes.builders.ResponseBuilderFactory;
import com.test.primes.config.CacheHelper;
import com.test.primes.enumerations.Algorithms;
import com.test.primes.model.ErrorResponse;
import com.test.primes.model.PrimesResponse;
import org.ehcache.Cache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(PrimesApiController.class)
public class PrimesApiControllerTest {

    @InjectMocks
    PrimesApiController primesApiController;

    @Mock
    HttpServletRequest request;

    @Mock
    CacheHelper cacheHelper;

    @Mock
    ResponseBuilderFactory responseBuilderFactory;

    @Mock
    private Cache<Algorithms, PrimesResponse> primesCache;

    @Test
    public void testValidation(){
        when(request.getHeader(HttpHeaders.ACCEPT)).thenReturn(MediaType.APPLICATION_JSON_VALUE);
        when(responseBuilderFactory.getResponseEntityBuilder(MediaType.APPLICATION_JSON)).thenReturn(new DefaultResponseBuilder());

        ResponseEntity res = primesApiController.fetchPrimes(1, Optional.empty());

        assertTrue(res.getBody() instanceof ErrorResponse);
        ErrorResponse err = (ErrorResponse) res.getBody();
        assertEquals(err.getError(), "Please enter a positive integer greater than 1.");
    }

    @Test
    public void testDefaultAlgorithm(){
        when(request.getHeader(HttpHeaders.ACCEPT)).thenReturn(MediaType.APPLICATION_JSON_VALUE);
        when(responseBuilderFactory.getResponseEntityBuilder(MediaType.APPLICATION_JSON)).thenReturn(new DefaultResponseBuilder());
        when(cacheHelper.getPrimesCache()).thenReturn(primesCache);
        when(primesCache.containsKey(Algorithms.SIEVE_OF_ATKIN)).thenReturn(false);
        ResponseEntity res = primesApiController.fetchPrimes(11, Optional.empty());
        assertTrue(res.getBody() instanceof PrimesResponse);
        PrimesResponse primes = (PrimesResponse) res.getBody();
        assertEquals(new TreeSet<>(Arrays.asList(2, 3, 5, 7, 11)), primes.getPrimes());
    }

    @Test
    public void testDefaultMediaTypeSelection(){
        when(request.getHeader(HttpHeaders.ACCEPT)).thenReturn(MediaType.ALL_VALUE);
        when(responseBuilderFactory.getResponseEntityBuilder(MediaType.APPLICATION_JSON)).thenReturn(new DefaultResponseBuilder());
        when(cacheHelper.getPrimesCache()).thenReturn(primesCache);
        when(primesCache.containsKey(Algorithms.SIEVE_OF_ATKIN)).thenReturn(false);
        primesApiController.fetchPrimes(11, Optional.empty());
        verify(responseBuilderFactory).getResponseEntityBuilder(eq(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testPlainTextMediaTypeSelection(){
        when(request.getHeader(HttpHeaders.ACCEPT)).thenReturn(MediaType.TEXT_PLAIN_VALUE);
        when(responseBuilderFactory.getResponseEntityBuilder(MediaType.TEXT_PLAIN)).thenReturn(new DefaultResponseBuilder());
        when(cacheHelper.getPrimesCache()).thenReturn(primesCache);
        when(primesCache.containsKey(Algorithms.SIEVE_OF_ATKIN)).thenReturn(false);
        primesApiController.fetchPrimes(11, Optional.empty());
        verify(responseBuilderFactory).getResponseEntityBuilder(eq(MediaType.TEXT_PLAIN));
    }

    @Test
    public void testXMLMediaTypeSelection(){
        when(request.getHeader(HttpHeaders.ACCEPT)).thenReturn(MediaType.APPLICATION_XML_VALUE);
        when(responseBuilderFactory.getResponseEntityBuilder(MediaType.APPLICATION_XML)).thenReturn(new DefaultResponseBuilder());
        when(cacheHelper.getPrimesCache()).thenReturn(primesCache);
        when(primesCache.containsKey(Algorithms.SIEVE_OF_ATKIN)).thenReturn(false);
        primesApiController.fetchPrimes(11, Optional.empty());
        verify(responseBuilderFactory).getResponseEntityBuilder(eq(MediaType.APPLICATION_XML));
    }
}
