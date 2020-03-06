package com.test.primes.util;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AlgorithmUtil {

    public static Optional<Set<Integer>> sieveOfEratosthenes(int n)
    {
        Boolean primeFlag[] = new Boolean[n+1];
        Arrays.fill(primeFlag, true);

        List<Boolean> primeFlagList = new LinkedList<>();
        Collections.addAll(primeFlagList, primeFlag);

        for(int p = 2; p*p <=n; p++)
        {
            if(primeFlagList.get(p))
            {
                for(int i = p*2; i <= n; i += p)
                    primeFlagList.set(i, false);
            }
        }

        IntStream intStream = IntStream.rangeClosed(2,n);
        Set<Integer> primes = intStream.parallel().filter( f -> primeFlagList.get(f))
                            .mapToObj(Integer::valueOf)
                            .collect(Collectors.toCollection(ConcurrentSkipListSet::new));
        return Optional.of(primes);
    }

    public static Optional<Set<Integer>> sieveOfSundaram(int n) {

        final int limit = (n - 2) / 2;
        Boolean marks[] = new Boolean[limit + 1];
        Arrays.fill(marks, false);

        for (int i = 1; i < limit; i++){
            int j = i;

            while(marks.length > i + j + (2 * i * j)){
               marks[i + j + (2 * i * j)] = true;
                j++;
            }
        }

        IntStream intStream = IntStream.rangeClosed(1,limit);
        Set<Integer> primes = intStream.parallel().filter( f -> !marks[f])
                        .mapToObj(num -> Integer.valueOf((2*num)+1))
                        .collect(Collectors.toCollection(ConcurrentSkipListSet::new));

        primes.add(2);
        return Optional.of(primes);
    }

    public static Optional<Set<Integer>> sieveOfAtkin(int limit){

        Boolean marks[] = new Boolean[limit+1];
        Arrays.fill(marks, false);

        Set<Integer> set;

        if(limit < 3){
            set = new TreeSet<>(Arrays.asList(2));
            return Optional.of(set);
        }

        set = new TreeSet<>(Arrays.asList(2, 3));

        for(int i = 1; i*i <=limit; i++){
            for(int j = 1; j*j<=limit; j++){

                int n = (4 * i * i) + (j * j);
                if (n <= limit && (n % 12 == 1 ||
                                n % 12 == 5)){
                    marks[n] ^= true;
                }

                n = (3 * i * i) + (j * j);
                if(n <=limit && (n % 12 == 7)){
                    marks[n] ^= true;
                }

                n = (3 * i * i) - (j * j);
                if( i > j && n <= limit && n % 12 == 11){
                    marks[n] ^= true;
                }

            }
        }

        for (int r = 5; r * r < limit; r++) {
            if (marks[r]) {
                for (int i = r * r; i < limit; i += r * r)
                    marks[i] = false;
            }
        }

        IntStream intStream = IntStream.rangeClosed(5,limit);
        Set<Integer> primes = intStream.parallel().filter(i -> marks[i]).mapToObj(Integer::valueOf).collect(
                        Collectors.toCollection(ConcurrentSkipListSet::new));

        primes.addAll(set);

        return Optional.of(primes);
    }

}
