package org.chobit.jspy.core.info;


public enum Net implements Info {

    LOCAL_HOST_IP("Local Host IP") {
        @Override
        public String value() {
            return NetInfoManager.getLocalHostIp();
        }
    },

    LOCAL_HOST_NAME("Local Host Name") {
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
