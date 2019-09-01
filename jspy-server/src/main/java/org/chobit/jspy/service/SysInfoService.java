package org.chobit.jspy.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.chobit.jspy.constants.InfoType;
import org.chobit.jspy.core.model.Item;
import org.chobit.jspy.service.mapper.SysInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.chobit.jspy.constants.InfoType.RUNTIME;
import static org.chobit.jspy.constants.InfoType.SYS;
import static org.chobit.jspy.core.utils.Strings.isBlank;
import static org.chobit.jspy.utils.JSON.fromJson;
import static org.chobit.jspy.utils.JSON.toJson;

@Service
public class SysInfoService {

    @Autowired
    private SysInfoMapper mapper;


    public void insertSysInfo(String appCode, String ip, List<Item> items) {
        insert(appCode, ip, items, SYS);
    }


    public List<Item> getLatestSysInfo(String appCode) {
        return getLatest(appCode, SYS);
    }


    public void insertRuntimeInfo(String appCode, String ip, List<Item> items) {
        insert(appCode, ip, items, RUNTIME);
    }


    public List<Item> getLatestRuntimeInfo(String appCode) {
        return getLatest(appCode, RUNTIME);
    }


    private void insert(String appCode, String ip, List<Item> items, InfoType type) {
        String json = toJson(items);
        mapper.insert(appCode, ip, type.id, json, new Date());
    }


    private List<Item> getLatest(String appCode, InfoType type) {
        String json = mapper.getLatest(appCode, type.id);
        if (isBlank(json)) {
            return new LinkedList<>();
        }
        return fromJson(json, new TypeReference<List<Item>>() {
        });
    }


}
