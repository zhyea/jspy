package org.chobit.jspy.web;

import org.chobit.jspy.service.AppService;
import org.chobit.jspy.service.beans.App;
import org.chobit.jspy.utils.Args;
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
    public boolean update(@RequestBody App app) {
        return appService.update(app);
    }


    @PostMapping("/")
    public int insert(@RequestBody App app) {
        Args.checkNotBlank(app.getAppName(), "应用名称不能为空");
        App other = appService.getByName(app.getAppName());
        Args.checkNull(other, "应用名称已存在");
        return appService.insert(app);
    }


}
