package org.chobit.jspy.web;


import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.service.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/memory")
public class MemoryController {

    @Autowired
    private MemoryService memoryService;


    @PostMapping("/find-by-params")
    public List<Map> findByParams(@RequestBody QueryParam param) {
        return memoryService.findByParams(param);
    }


}
