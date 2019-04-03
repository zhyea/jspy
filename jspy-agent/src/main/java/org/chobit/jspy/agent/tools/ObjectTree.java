package org.chobit.jspy.agent.tools;

import org.chobit.jspy.commons.model.ObjectNode;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 对象树操作类
 */
public class ObjectTree {

    /**
     * 记录对象信息的集合，主要用来去重
     */
    private Set<Object> set = new HashSet<>();

    /**
     * 构建对象树的根节点
     */
    private Object rootObj;

    /**
     * 构建对象树时是否访问直接量
     */
    private boolean visitPrimitive;

    public ObjectTree(Object root) {
        this(root, false);
    }

    public ObjectTree(Object root, boolean visitPrimitive) {
        this.rootObj = root;
        this.visitPrimitive = visitPrimitive;
    }

    /**
     * 构建对象树
     *
     * @return 构建的对象树的根节点
     * @throws IllegalAccessException
     */
    public ObjectNode build() throws IllegalAccessException {
        if (null == rootObj) {
            return new ObjectNode(rootObj, false);
        }
        Class<?> clazz = rootObj.getClass();
        ObjectNode root = new ObjectNode(rootObj, clazz.isPrimitive());
        build(root, clazz);
        return root;
    }


    /**
     * 执行构建对象树的具体工作
     *
     * @param root      对象树的根节点
     * @param rootClass 根节点的值的Class
     * @throws IllegalAccessException
     */
    private void build(ObjectNode root, Class<?> rootClass) throws IllegalAccessException {

        Object rootObj;

        if (null == root || null == (rootObj = root.getValue())) {
            return;
        }

        if (rootClass.isArray()) {
            Class<?> clazz = rootClass.getComponentType();
            if (clazz.isPrimitive() && !visitPrimitive) {//
                return;
            }
            int length = Array.getLength(rootObj);
            for (int i = 0; i < length; i++) {
                Object ele = Array.get(rootObj, i);
                if (!set.add(ele)) {//已记录过的对象不需重复添加
                    continue;
                }
                ObjectNode node = new ObjectNode(ele, false);
                root.addChild(node);
                build(node, clazz);
            }
        } else {
            Field[] fields = getAllFields(rootClass);
            for (Field f : fields) {
                boolean isStatic = Modifier.isStatic(f.getModifiers());
                if (isStatic)//不需计算类成员
                    continue;
                boolean isPrimitive = f.getType().isPrimitive();
                if (isPrimitive && !visitPrimitive) {//如是不需要访问直接量，则直接跳过迭代
                    continue;
                }
                Object o = f.get(rootObj);//获取成员对象
                if (!set.add(o)) {//已记录过的对象不需重复添加
                    continue;
                }
                ObjectNode node = new ObjectNode(o, isPrimitive);//创建新节点
                root.addChild(node);
                if (null == o)//如果成员对象为null，不需继续进行迭代
                    continue;
                if (!isPrimitive) {//直接类型不需继续迭代
                    build(node, o.getClass());
                }
            }
        }
    }


    /**
     * 获取指定类的所有Field，包括公有的和私有的
     *
     * @param clazz 指定类
     * @return 指定类的所有字段
     */
    private Field[] getAllFields(Class<?> clazz) {
        if (clazz.isPrimitive()) {
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
    public void dump(ObjectNode root, int deep) {
        for (int i = 0; i < deep; i++) {
            System.out.print("\t");
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


    private ObjectTree() {
        throw new UnsupportedOperationException("private constructor, cannot be called.");
    }
}
