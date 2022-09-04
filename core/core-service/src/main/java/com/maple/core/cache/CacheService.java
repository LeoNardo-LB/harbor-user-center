package com.maple.core.cache;

public interface CacheService {

    Boolean putObject(String key, Object object);

    <T> T getObject(String key, Class<T> tClass);

    Boolean existKey(String key);

    Boolean deleteKey(String key);

}
