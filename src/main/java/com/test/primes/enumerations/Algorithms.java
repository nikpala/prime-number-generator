package com.test.primes.enumerations;

import com.sun.javafx.scene.traversal.Algorithm;

import java.util.Arrays;
import java.util.List;

public enum Algorithms {
    SIEVE_OF_ERATOSHENES("Eratosthenes"),
    SIEVE_OF_SUNDARAM("Sundaram"),
    SIEVE_OF_ATKIN("Atkin");

    private String alg;

    Algorithms(String alg){
        this.alg = alg;
    }

    public String getAlg(){
        return alg;
    }

    public static Algorithms getValue(String alg){
        List<Algorithms> algArray = Arrays.asList(Algorithms.values());

        for(Algorithms algorithms : algArray){
            algorithms.
        }

        return algs;
    }

}
