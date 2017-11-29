package com.zhyea.jspy.agent.tools;

import com.zhyea.jspy.agent.JSpyAgent;
import com.zhyea.jspy.commons.model.ObjectNode;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 对象测量工具类
 */
public class ObjectMeasure {


    /**
     * 测量对象size
     *
     * @param obj 要测量的对象
     * @return 对象的size
     * @throws IllegalAccessException
     */
    public static long measure(Object obj) throws IllegalAccessException {
        ObjectNode root = ObjectTree.build(obj);
        AtomicLong size = new AtomicLong(0);
        accumulate(root, size);
        return size.get();
    }


    /**
     * 执行具体的测量累计工作
     *
     * @param node 对象树上的节点
     * @param size 记录对象size的变量
     */
    private static void accumulate(ObjectNode node, AtomicLong size) {
        size.getAndAdd(JSpyAgent.getInstrumentation().getObjectSize(node.getValue()));
        for (ObjectNode on : node.getChilds()) {
            accumulate(on, size);
        }
    }


}
