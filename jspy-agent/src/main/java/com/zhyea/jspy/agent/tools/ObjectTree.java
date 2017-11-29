package com.zhyea.jspy.agent.tools;

import com.zhyea.jspy.commons.model.ObjectNode;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;

/**
 * 对象树操作类
 */
public class ObjectTree {


    /**
     * 构建对象树
     *
     * @param obj 要构建对象树的源对象
     * @return 构建的对象树的根节点
     * @throws IllegalAccessException
     */
    public static ObjectNode build(Object obj) throws IllegalAccessException {
        ObjectNode root = new ObjectNode(obj);
        build(root, obj.getClass());
        return root;
    }


    /**
     * 执行构建对象树的具体工作
     *
     * @param root      对象树的根节点
     * @param rootClass 根节点的值的Class
     * @throws IllegalAccessException
     */
    private static void build(ObjectNode root, Class<?> rootClass) throws IllegalAccessException {

        Object rootObj;

        if (null == root || null == (rootObj = root.getValue())) {
            return;
        }

        if (rootClass.isArray()) {
            Class<?> clazz = rootClass.getComponentType();
            int length = Array.getLength(rootObj);
            for (int i = 0; i < length; i++) {
                Object ele = Array.get(rootObj, i);
                ObjectNode node = new ObjectNode(ele);
                root.addChild(node);
                build(node, clazz);
            }
        } else {
            Field[] fields = getAllFields(rootClass);
            for (Field f : fields) {
                Object o = f.get(rootObj);
                if (null == o) {
                    continue;
                }
                ObjectNode node = new ObjectNode(o);
                root.addChild(node);
                Class clazz = f.getType().isPrimitive() ? f.getType() : o.getClass();
                build(node, clazz);
            }
        }
    }


    /**
     * 获取指定类的所有Field，包括公有的和私有的
     *
     * @param clazz 指定类
     * @return 指定类的所有字段
     */
    private static Field[] getAllFields(Class<?> clazz) {
        if (clazz.isPrimitive() || clazz.equals(String.class)) {
            return new Field[]{};
        }
        Field[] fields = clazz.getDeclaredFields();
        AccessibleObject.setAccessible(fields, true);
        return fields;
    }

    /**
     * 在控制台输出对象树
     *
     * @param root 对象树根节点
     * @param deep 对象树深度，根节点深度为0
     */
    public static void dump(ObjectNode root, int deep) {
        for (int i = 0; i < deep; i++) {
            System.out.print("\t ");
        }
        if (deep > 0) {
            System.out.print("|- ");
        }

        System.out.println(root.getValue());

        List<ObjectNode> nodes = root.getChilds();
        for (ObjectNode n : nodes) {
            dump(n, deep + 1);
        }
    }


    public static boolean isWrapClass(Class clz) {
        try {
            return ((Class) clz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

    private ObjectTree() {
        throw new RejectedExecutionException("private constructor, cannot be called.");
    }
}
