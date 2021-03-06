package org.chobit.jspy.service;


import org.chobit.jspy.core.annotation.JSpyWatcher;
import org.chobit.jspy.service.entity.App;
import org.chobit.jspy.service.mapper.AppMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.chobit.jspy.utils.ShortCode.gen;

@Service
@CacheConfig(cacheNames = "app")
public class AppService {


    @Autowired
    private AppMapper appMapper;

    @JSpyWatcher("insert")
    public int insert(App app) {
        String appCode = gen();
        app.setAppCode(appCode);
        appMapper.insert(app);
        return app.getId();
    }


    public boolean update(App app) {
        return appMapper.update(app.getAppName(), app.getId());
    }


    public List<App> findAll() {
        return appMapper.findAll();
    }


    @Cacheable(key = "'all-app-codes'")
    public List<String> findAllAppCodes() {
        return appMapper.findAllAppCodes();
    }


    public boolean logicDelete(int id) {
        return appMapper.logicDelete(id);
    }


    public App get(int id) {
        return appMapper.get(id);
    }


    public App getByName(String appName) {
        return appMapper.getByAppName(appName);
    }


    public App getByAppCode(String appCode) {
        return appMapper.getByAppCode(appCode);
    }


}
