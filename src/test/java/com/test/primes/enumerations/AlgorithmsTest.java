package com.test.primes.enumerations;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class AlgorithmsTest {

    @Test
    public void testGetValueForNull(){
        Algorithms alg = Algorithms.getValue(null);
        assertEquals(Algorithms.SIEVE_OF_ATKIN, alg);
    }

    @Test
    public void testGetValueForGibberish(){
        Algorithms alg = Algorithms.getValue("abcd");
        assertEquals(Algorithms.SIEVE_OF_ATKIN, alg);
    }

    @Test
    public void testGetValueForCaseInsensitive(){
        Algorithms alg = Algorithms.getValue("sUNdaram");
        assertEquals(Algorithms.SIEVE_OF_SUNDARAM, alg);
    }

    @Test
    public void testGetValue(){
        Algorithms alg = Algorithms.getValue("Sundaram");
        assertEquals(Algorithms.SIEVE_OF_SUNDARAM, alg);

        alg = Algorithms.getValue("Atkin");
        assertEquals(Algorithms.SIEVE_OF_ATKIN, alg);

        alg = Algorithms.getValue("Eratosthenes");
        assertEquals(Algorithms.SIEVE_OF_ERATOSHENES, alg);
    }

}
