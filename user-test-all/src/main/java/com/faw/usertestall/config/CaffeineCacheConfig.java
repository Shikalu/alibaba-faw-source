package com.faw.usertestall.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author 鹿胜宝
 * @date 2023/03/22
 */
@Configuration
@EnableCaching
public class CaffeineCacheConfig {

    @Bean("cacheManager")
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        ArrayList<CaffeineCache> caches = new ArrayList<CaffeineCache>();

        caches.add(new CaffeineCache("user-cache", Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterAccess(120, TimeUnit.SECONDS)
                .build()));

        cacheManager.setCaches(caches);
        return cacheManager;
    }

    //spring 自带的缓存
//    @Bean("cacheManager")
//    public CacheManager cacheManager() {
//        SimpleCacheManager cacheManager = new SimpleCacheManager();
//        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("user-cache")));
//        return cacheManager;
//    }
}
