package org.chobit.jspy.core.support;

import org.chobit.jspy.core.gauge.HeapMemoryUsage;

import javax.management.InstanceNotFoundException;
import java.util.LinkedList;
import java.util.List;

import static org.chobit.jspy.core.constants.StorageUnit.B;

public class GcNotificationListenerTest {


    public static void main(String[] args) throws InstanceNotFoundException {

        GcCollector collector = new GcCollector(1024, 60);

        new GcNotificationListener(collector).apply();

        List<byte[]> list = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < 1024 * 400; i++) {
            if (++count % 100 == 0) {
                System.out.println(B.toMB(HeapMemoryUsage.USED.value()) + "MB");
            }

            list.add(new byte[1024]);
            //TimeUnit.MILLISECONDS.sleep(1);
        }
        System.out.println(collector.size());
        System.out.println(collector.takeFirst());
    }
}
