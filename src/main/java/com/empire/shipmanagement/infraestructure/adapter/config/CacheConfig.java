package com.empire.shipmanagement.infraestructure.adapter.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    public static final String SHIP_INFO_CACHE = "SHIP_INFO_CACHE";

    @Value("${cache.ship.info.ttl}")
    private long cacheShipInfoTtl;
    @Value("${cache.ship.info.ttl.unit}")
    private String cacheShipInfoTtlUnit;
    @Value("${cache.ship.info.max.size}")
    private long cacheShipInfoMaxSize;

    @Bean
    public CacheManager cacheManager() {
        List<CaffeineCache> caches = new ArrayList<>();
        caches.add(buildCache(SHIP_INFO_CACHE, cacheShipInfoTtl,
                TimeUnit.valueOf(cacheShipInfoTtlUnit.toUpperCase()), cacheShipInfoMaxSize ));

        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(caches);
        return manager;
    }

    private CaffeineCache buildCache(String name, long ttl, TimeUnit ttlUnit, long size){
        return new CaffeineCache(name, Caffeine.newBuilder()
                .expireAfterWrite(ttl, ttlUnit)
                .maximumSize(size)
                .build());

    }

}
