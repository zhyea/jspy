package org.chobit.jspy.service;

import org.chobit.jspy.beans.Memory;
import org.chobit.jspy.core.constants.MemoryType;
import org.chobit.jspy.mapper.MemoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.management.MemoryUsage;
import java.util.Date;
import java.util.List;

@Service
public class MemoryService {


    @Autowired
    private MemoryMapper mapper;


    public int insert(Memory memory) {
        return mapper.insert(memory);
    }

    public int insert(MemoryUsage usage, MemoryType type) {
        Memory m = new Memory();

        return mapper.insert(memory);
    }


    public List<Memory> findByEventTime(Date eventTime) {
        return mapper.findByEventTime(eventTime);
    }


}
