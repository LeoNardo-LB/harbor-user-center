package com.maple.core.cache.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheBuilderSpec;
import com.maple.core.cache.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service("guavaCacheImpl")
@Slf4j
public class GuavaCacheImpl implements CacheService {

    private static final Cache<String, Object> CACHE_MAP = CacheBuilder.newBuilder()
                                                                   .concurrencyLevel(1)
                                                                   .expireAfterWrite(30, TimeUnit.MINUTES)
                                                                   .expireAfterAccess(1, TimeUnit.HOURS)
                                                                   .build();

    @Override
    public Boolean putObject(String key, Object object) {
        CACHE_MAP.put(key, object);
        return true;
    }

    @Override
    public <T> T getObject(String key, Class<T> tClass) {
        return (T) CACHE_MAP.getIfPresent(key);
    }

    @Override
    public Boolean existKey(String key) {
        Object o = CACHE_MAP.getIfPresent(key);
        return Objects.nonNull(o);
    }

    @Override
    public Boolean deleteKey(String key) {
        CACHE_MAP.invalidate(key);
        return true;
    }

}
