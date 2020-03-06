package com.test.primes.service;

import java.util.Optional;
import java.util.Set;

@FunctionalInterface
public interface PrimesGenerator {

    Optional<Set<Integer>> fetchPrimes(Integer initial) throws Exception;
}
