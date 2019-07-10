package org.chobit.jspy;

public class WatcherConfig {

    private int expectNumMethods = 32;

    private int histogramPeriod = 60 * 5;

    public int getExpectNumMethods() {
        return expectNumMethods;
    }

    public void setExpectNumMethods(int expectNumMethods) {
        this.expectNumMethods = expectNumMethods;
    }

    public int getHistogramPeriod() {
        return histogramPeriod;
    }

    public void setHistogramPeriod(int histogramPeriod) {
        this.histogramPeriod = histogramPeriod;
    }
}
