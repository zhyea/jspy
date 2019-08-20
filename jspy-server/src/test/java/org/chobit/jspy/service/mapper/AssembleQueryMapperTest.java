package org.chobit.jspy.service.mapper;

import org.chobit.jspy.TestBase;
import org.chobit.jspy.model.page.Page;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class AssembleQueryMapperTest extends TestBase {

    @Autowired
    private AssembleQueryMapper assembleQueryMapper;


    @Test
    public void findInPage(){
        String tableName = "gc_stat";
        String appCode = "jSpyCons";

        Page page = new Page();
        page.setSort("id");
        page.setOrder(Page.Direct.desc);
        page.setLimit(20);
        page.setOffset(0);
        page.setSearch("MAJ");

        List<String> searchColumns = Arrays.asList("type", "name", "cause");
        List<LowerCaseKeyMap> rows = assembleQueryMapper.findInPage(tableName, appCode, page, searchColumns,
                "type", "name", "action", "cause", "duration", "usage_before", "usage_after", "event_time");

        System.out.println(rows.size());

    }


}
