package org.chobit.jspy.web;

import org.chobit.jspy.service.AppService;
import org.chobit.jspy.service.beans.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private AppService appService;


    @GetMapping("/all")
    public List<App> findAll() {
        return appService.findAll();
    }


    @GetMapping("/{id}")
    public App get(@PathVariable("id") int id) {
        return appService.get(id);
    }


    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int id) {
        return appService.logicDelete(id);
    }


    @PutMapping("/")
    public boolean update(int id, String appName) {
        return appService.update(id, appName);
    }


    @PostMapping("/")
    public int insert(String appName) {
        return appService.insert(appName);
    }


}
