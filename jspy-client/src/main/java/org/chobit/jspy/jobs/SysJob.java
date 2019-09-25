package org.chobit.jspy.jobs;

import org.chobit.jspy.core.info.Sys;
import org.chobit.jspy.core.model.Item;

import java.util.List;

public final class SysJob extends AbstractOneOffJob {


    @Override
    String name() {
        return "sysInfo";
    }


    @Override
    void collect() {
        List<Item> items = Sys.values();
        messagePack().setSysInfo(items);
    }
}
