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

    public int insert(App app) {
        String appCode = genShortUrl();
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


    public App getByAppCode(String appCode) {
        return appMapper.getByAppCode(appCode);
    }


    private static final AtomicInteger SEQ = new AtomicInteger(1);
    private static final DecimalFormat FORMAT = new DecimalFormat("00");

    private synchronized String genShortUrl() {
        StringBuilder builder = new StringBuilder(System.currentTimeMillis() + "");
        if (SEQ.incrementAndGet() % 10 == 0) {
            SEQ.incrementAndGet();
        }
        builder.append(FORMAT.format(SEQ.get()));
        if (99 == SEQ.get()) {
            SEQ.set(1);
        }
        long v = Long.parseLong(builder.reverse().toString());
        return Base62.encode(v);
    }


}
