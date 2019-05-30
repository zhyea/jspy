package org.chobit.jspy.service;


import org.chobit.jspy.service.beans.App;
import org.chobit.jspy.service.mapper.AppMapper;
import org.chobit.jspy.tools.Base62;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AppService {


    @Autowired
    private AppMapper appMapper;

    public int insert(String appName) {
        String appCode = genShortUrl();
        App app = new App();
        app.setAppCode(appCode);
        app.setAppName(appName);
        appMapper.insert(app);
        return app.getId();
    }


    public boolean update(int appId, String appName) {
        return appMapper.update(appName, appId);
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


    public App getByAppCode(String appCode) {
        return appMapper.getByAppCode(appCode);
    }


    private static final AtomicInteger SEQ = new AtomicInteger(1);
    private static final DecimalFormat FORMAT = new DecimalFormat("##");

    private synchronized String genShortUrl() {
        String s = System.currentTimeMillis() + FORMAT.format(SEQ.getAndIncrement());

        if (99 == SEQ.get()) {
            SEQ.set(1);
        }
        long v = Long.parseLong(s);
        return Base62.encode(v);
    }


}
