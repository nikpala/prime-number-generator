package com.test.primes.util;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AlgorithmUtil {

    public static Optional<List<Integer>> generateEratosthenesPrimes(Integer in) {

        List<Integer> intitialCollection = IntStream.rangeClosed(2, in)
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));

        List<Integer> primes = new ArrayList<>();
        primes.add(2);
        Iterator<Integer> primeIter = primes.iterator();

        while (primeIter.hasNext()) {
            int prime = primeIter.next();
            primes = intitialCollection.stream().filter(element -> (element == prime || element % prime > 0)).collect(Collectors.toList());

            if (prime * prime > in) {
                break;
            }
        }

        return Optional.of(primes);
    }
}
