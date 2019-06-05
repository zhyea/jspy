package org.chobit.jspy.service;


import org.chobit.jspy.service.beans.App;
import org.chobit.jspy.service.mapper.AppMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.chobit.jspy.utils.ShortCodeGenerator.genShortCode;

@Service
public class AppService {


    @Autowired
    private AppMapper appMapper;

    public int insert(App app) {
        String appCode = genShortCode();
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


    public boolean logicDelete(int id) {
        return appMapper.logicDelete(id);
    }


    public App get(int id) {
        return appMapper.get(id);
    }


    public App getByName(String appName){
        return appMapper.getByAppName(appName);
    }


    public App getByAppCode(String appCode) {
        return appMapper.getByAppCode(appCode);
    }


}
