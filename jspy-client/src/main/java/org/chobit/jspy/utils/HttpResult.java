package org.chobit.jspy.utils;


public class HttpResult {

    private int status;

    private String content;

    private Throwable throwable;

    public HttpResult() {
    }

    public boolean isSuccessful() {
        return null == throwable && status == 200;
    }


    public boolean isFailed() {
        return !isSuccessful();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
