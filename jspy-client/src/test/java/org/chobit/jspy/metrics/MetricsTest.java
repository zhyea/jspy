package org.chobit.jspy.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class MetricsTest {


    private static final MetricRegistry metrics = new MetricRegistry();

    public static void main(String[] args) throws InterruptedException {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).build();

        Queue<String> queue = new LinkedBlockingDeque<>();


        reporter.start(3, TimeUnit.SECONDS);

        Gauge<Integer> gauge = new Gauge<Integer>() {
            @Override
            public Integer getValue() {
                return queue.size();
            }
        };

        metrics.register(MetricRegistry.name(MetricsTest.class, "pending-job", "size"), gauge);

        //模拟数据
        for (int i=0; i<20; i++){
            queue.add("a");
            Thread.sleep(1000);
        }
    }

}
