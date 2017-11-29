package com.zhyea.jspy.commons.model;

import java.util.ArrayList;
import java.util.List;


public class ObjectNode {

    private Object value;

    private List<ObjectNode> childs = new ArrayList<>();

    public ObjectNode(Object root) {
        this.value = root;
    }

    public Object getValue() {
        return value;
    }


    public List<ObjectNode> getChilds() {
        return childs;
    }

    public void addChild(ObjectNode childNode) {
        childs.add(childNode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjectNode that = (ObjectNode) o;

        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        return childs != null ? childs.equals(that.childs) : that.childs == null;
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (childs != null ? childs.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "ObjectNode{" +
                "value=" + value +
                ", childs=" + childs +
                '}';
    }
}
