package org.chobit.jspy.core.support;


import java.lang.instrument.Instrumentation;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 对象测量工具类
 */
public class ObjectMeasure {


    private Instrumentation instrumentation;


    public ObjectMeasure(Instrumentation instrumentation) {
        this.instrumentation = instrumentation;
    }

    /**
     * 计算对象的shallow size, 即对象自身占用的内存空间的大小
     *
     * @param obj 要测量的对象
     * @return 要测量的对象的shallow size.
     */
    public long shallowSize(Object obj) {
        if (null == obj) {
            return -1;
        }
        return instrumentation.getObjectSize(obj);
    }


    /**
     * 测量对象的retained size，也就是对象自身及因对象obj保持存活的所有其他对象的shallow size之和
     *
     * @param obj 要测量的对象
     * @return 要测量的对象的retained size
     * @throws IllegalAccessException
     */
    public long retainedSize(Object obj) throws IllegalAccessException {
        ObjectNode root = new ObjectTree(obj).build();
        AtomicLong size = new AtomicLong(0);
        accumulate(root, size);
        return size.get();
    }

    /**
     * 执行retained size测量的累计工作
     *
     * @param node 对象树上的节点
     * @param size 记录对象size的变量
     */
    private void accumulate(ObjectNode node, AtomicLong size) {
        Object obj;
        if (null == node || null == (obj = node.getValue())) {
            return;
        }
        size.getAndAdd(instrumentation.getObjectSize(obj));
        for (ObjectNode on : node.getChildren()) {
            accumulate(on, size);
        }
    }


}