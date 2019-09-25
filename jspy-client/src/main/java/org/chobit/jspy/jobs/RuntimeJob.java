package org.chobit.jspy.jobs;

import org.chobit.jspy.core.info.Compilation;
import org.chobit.jspy.core.info.Runtime;
import org.chobit.jspy.core.model.Item;

import java.util.LinkedList;
import java.util.List;

public final class RuntimeJob extends AbstractOneOffJob {


    @Override
    String name() {
        return "runtimeInfo";
    }


    @Override
    void collect() {
        List<Item> items = new LinkedList<>();

        Item compilations = new Item("编译信息");
        for (Compilation c : Compilation.values()) {
            compilations.add(c.alias, c.value());
        }
        items.add(compilations);


        Item runtime = new Item("运行时信息");
        for (Runtime r : Runtime.values()) {
            runtime.add(r.alias, r.value());
        }
        items.add(runtime);

        messagePack().setRuntime(items);
    }
}
