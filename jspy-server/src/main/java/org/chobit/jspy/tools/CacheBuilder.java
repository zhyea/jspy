package org.chobit.jspy.tools;


import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

public abstract class CacheBuilder {


    public static <K, V> LoadingCache<K, V> build(CacheLoader<? super K, V> loader) {
        return Caffeine.newBuilder()
                .maximumSize(20000)
                .expireAfterAccess(30, TimeUnit.DAYS)
                .refreshAfterWrite(5, TimeUnit.MINUTES)
                .build(loader);
    }

    private CacheBuilder() {
    }

}
