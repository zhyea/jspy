package org.chobit.jspy.service;

import org.chobit.jspy.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MemoryServiceTest extends TestBase {

    @Autowired
    private MemoryService memoryService;

    @Test
    public void findHeapPoolNames() {
        String appCode = "jSpyCons";
        List<String> list = memoryService.findHeapPoolNames(appCode);
        List<String> list0 = memoryService.findHeapPoolNames(appCode);
        List<String> list1 = memoryService.findHeapPoolNames(appCode);
        Assert.assertTrue(list.size() > 0);
    }


}
