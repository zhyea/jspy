package org.chobit.jspy;


import org.chobit.jspy.core.metrics.Histogram;
import org.chobit.jspy.core.metrics.Reservoir;
import org.chobit.jspy.core.metrics.SlidingTimeWindowReservoir;

import java.util.concurrent.TimeUnit;

import static org.chobit.jspy.utils.JSON.toJson;

public class Test {


    public static void main(String[] args) throws InterruptedException {
        Reservoir reservoir = new SlidingTimeWindowReservoir(20, TimeUnit.SECONDS);
        Histogram histogram = new Histogram(reservoir);
        for (int i = 0; i < 10000; i++) {
            histogram.update((int) (Math.random() * 10000));
            TimeUnit.MILLISECONDS.sleep(100);
            if (0 == i % 10) {
                System.out.println(toJson(histogram.getSnapshot()));
            }
        }
    }


}
