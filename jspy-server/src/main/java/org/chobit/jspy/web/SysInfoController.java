package org.chobit.jspy.web;


import org.chobit.jspy.service.SysInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sys")
public class SysInfoController {

    @Autowired
    private SysInfoService service;



}
