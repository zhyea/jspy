package org.chobit.jspy.core.metrics;


public class Histogram implements Sampling {

    private final Reservoir reservoir;

    public Histogram(Reservoir reservoir) {
        this.reservoir = reservoir;
    }


    public void update(long value) {
        reservoir.update(value);
    }

    @Override
    public Snapshot getSnapshot() {
        return reservoir.getSnapshot();
    }
}

