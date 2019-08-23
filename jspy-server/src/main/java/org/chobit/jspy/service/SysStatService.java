package org.chobit.jspy.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.chobit.jspy.core.model.Item;
import org.chobit.jspy.service.mapper.SysStatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.chobit.jspy.utils.JSON.fromJson;
import static org.chobit.jspy.utils.JSON.toJson;

@Service
public class SysStatService {

    @Autowired
    private SysStatMapper mapper;


    public void insert(String appCode, String ip, List<Item> items) {
        String json = toJson(items);
        mapper.insert(appCode, ip, json);
    }


    public List<Item> getLatest(String appCode) {
        String json = mapper.getLatest(appCode);
        return fromJson(json, new TypeReference<List<Item>>() {
        });
    }


}
