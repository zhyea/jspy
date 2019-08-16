package org.chobit.jspy.service;

import org.chobit.jspy.TestBase;
import org.chobit.jspy.service.entity.App;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AppServiceTest extends TestBase {

    @Autowired
    private AppService appService;


    @Test
    public void insert() {
        App app = new App();
        app.setAppName("test");
        appService.insert(app);

        System.out.println(app.getId());
    }

}
