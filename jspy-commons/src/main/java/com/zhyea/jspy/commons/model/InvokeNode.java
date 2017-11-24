package com.zhyea.jspy.commons.model;

import java.util.List;

/**
 * 方法链路节点
 */
public class InvokeNode {

    /**
     * 方法ID，默认使用方法名的MD5值
     */
    private String id;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 子节点，即该方法调用的方法
     */
    private List<InvokeNode> childNodes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<InvokeNode> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<InvokeNode> childNodes) {
        this.childNodes = childNodes;
    }
}
