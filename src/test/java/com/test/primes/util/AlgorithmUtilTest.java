package com.test.primes.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
public class AlgorithmUtilTest {

    @Test
    public void testSieveOfEratosthenesForMinInput(){
        Optional<Set<Integer>> primes = AlgorithmUtil.sieveOfEratosthenes(2);
        assertTrue(primes.isPresent());
        assertEquals(primes.get().size(), 1);
        Set<Integer> set = new HashSet<>(Arrays.asList(2));
        assertEquals(primes.get(), set);
    }

    @Test
    public void testSieveOfEratosthenes(){
        Optional<Set<Integer>> primes = AlgorithmUtil.sieveOfEratosthenes(11);
        assertTrue(primes.isPresent());

        Set<Integer> set = new TreeSet<>(Arrays.asList(2, 3, 5, 7, 11));
        assertEquals(primes.get(), set);
    }

    @Test
    public void testSieveOfSundaramForMinInput(){
        Optional<Set<Integer>> primes = AlgorithmUtil.sieveOfSundaram(2);
        assertTrue(primes.isPresent());
        assertEquals(primes.get().size(), 1);
        Set<Integer> set = new HashSet<>(Arrays.asList(2));
        assertEquals(primes.get(), set);
    }

    @Test
    public void testSieveOfSundaram(){
        Optional<Set<Integer>> primes = AlgorithmUtil.sieveOfSundaram(30);
        assertTrue(primes.isPresent());

        Set<Integer> set = new TreeSet<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29));
        assertEquals(primes.get(), set);
    }

    @Test
    public void testSieveOfAtkinForMinInput() throws  Exception{
        Optional<Set<Integer>> primes = AlgorithmUtil.sieveOfAtkin(2);
        assertTrue(primes.isPresent());
        assertEquals(primes.get().size(), 1);
        Set<Integer> set = new HashSet<>(Arrays.asList(2));
        assertEquals(primes.get(), set);
    }

    @Test
    public void testSieveOfAtkin() throws Exception{
        Optional<Set<Integer>> primes = AlgorithmUtil.sieveOfAtkin(34);
        assertTrue(primes.isPresent());

        Set<Integer> set = new TreeSet<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31));
        assertEquals(set, primes.get());
    }

}
