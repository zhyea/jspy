package org.chobit.jspy.core.exceptions;


public class JSpyException extends RuntimeException {


    public JSpyException() {
        super();
    }

    public JSpyException(String msg) {
        super(msg);
    }


    public JSpyException(Throwable t) {
        super(t);
    }


    public JSpyException(String msg, Throwable t) {
        super(msg, t);
    }

}
