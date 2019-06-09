package org.chobit.jspy.core.info;


public enum Net implements Info {

    LOCAL_HOST_IP("本机IP") {
        @Override
        public String value() {
            return NetInfoManager.getLocalHostIp();
        }
    },

    LOCAL_HOST_NAME("本机服务器名称") {
        @Override
        public String value() {
            return NetInfoManager.getLocalHostName();
        }
    },

    ;


    public final String alias;

    Net(String alias) {
        this.alias = alias;
    }

}
