package com.maple.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class CacheUtils {

    /**
     * 生成cache的key
     * @param scene 业务场景
     * @param strs  需要拼接的key
     * @return
     */
    public static String genKey(String scene, String... strs) {
        String joined = String.join("_", strs);
        return new StringBuilder().append("[").append(scene).append(": ")
                       .append(joined)
                       .append("]").toString();
    }

}
