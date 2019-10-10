package org.chobit.jspy.service;


import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "test")
@Service
public class MyService {

    @Cacheable(key = "'getStr'")
    public String getStr() {
        System.out.println("------------");
        return "abc";
    }

}
