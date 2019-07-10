package org.chobit.jspy.core.support;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ObjectNode {

    private Object value;

    private boolean isPrimitive;

    private List<ObjectNode> children = new ArrayList<>();

    public ObjectNode(Object root, boolean isPrimitive) {
        this.value = root;
        this.isPrimitive = isPrimitive;
    }

    public Object getValue() {
        return value;
    }

    public boolean isPrimitive() {
        return isPrimitive;
    }

    public List<ObjectNode> getChildren() {
        return children;
    }

    public void addChild(ObjectNode childNode) {
        children.add(childNode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjectNode that = (ObjectNode) o;

        if (isPrimitive != that.isPrimitive) return false;
        if (!Objects.equals(value, that.value)) return false;
        return Objects.equals(children, that.children);
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (isPrimitive ? 1 : 0);
        result = 31 * result + (children != null ? children.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ObjectNode{" +
                "value=" + value +
                ", isPrimitive=" + isPrimitive +
                ", children=" + children +
                '}';
    }
}
