package com.example.mongo_user.domain.utils;

public class CacheKey {
    private static String prefix = "demo";
    public static String testCache() {
        return prefix + "_test";
    }
}
