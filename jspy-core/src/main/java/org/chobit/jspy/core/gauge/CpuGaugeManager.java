package org.chobit.jspy.core.gauge;

import org.chobit.jspy.core.info.OSInfoManager;

import static org.chobit.jspy.core.gauge.ThreadsGaugeManager.allThreadIds;
import static org.chobit.jspy.core.gauge.ThreadsGaugeManager.getThreadCpuTime;

abstract class CpuGaugeManager {

    private static long preTotalTime = 0;
    private static long preTime = System.nanoTime();
    private static int availableProcessors = OSInfoManager.availableProcessors();

    static double cpuLoad() {
        long total = 0;

        for (long id : allThreadIds()) {
            total += getThreadCpuTime(id);
        }

        long curr = System.nanoTime();
        long elapsed = 0 == preTotalTime ? 0 : total - preTotalTime;
        long passed = curr - preTime;

        preTime = curr;
        preTotalTime = total;

        elapsed = elapsed < 0 ? 0 : elapsed;

        if (0 >= availableProcessors) {
            return 0;
        }

        if (0 >= passed) {
            return 0;
        }

        return Math.round(10000.0 * elapsed / passed / availableProcessors) / 100.0;
    }


}
