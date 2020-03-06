package com.test.primes.config;

import com.test.primes.enumerations.Algorithms;
import com.test.primes.model.PrimesResponse;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.stereotype.Component;

@Component
public class CacheHelper {

    private CacheManager cacheManager;
    private Cache<Algorithms, PrimesResponse> primesCache;

    public Cache<Algorithms, PrimesResponse> getPrimesCache() {
        return primesCache;
    }

    public CacheHelper() {
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        cacheManager.init();

        primesCache = cacheManager.createCache("primes", CacheConfigurationBuilder
                        .newCacheConfigurationBuilder(Algorithms.class, PrimesResponse.class, ResourcePoolsBuilder.heap(4)));
    }
}
