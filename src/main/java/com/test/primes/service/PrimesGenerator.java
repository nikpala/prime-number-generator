package com.test.primes.service;

import java.util.List;
import java.util.Optional;

@FunctionalInterface
public interface PrimesGenerator {

    Optional<List<Integer>> fetchPrimes(Integer initial);
}
