package org.chobit.jspy.interceptor;

public class WatcherAttribute {


    private String name;

    private String methodIdentity;

    public WatcherAttribute(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethodIdentity() {
        return methodIdentity;
    }

    public void setMethodIdentity(String methodIdentity) {
        this.methodIdentity = methodIdentity;
    }
}
