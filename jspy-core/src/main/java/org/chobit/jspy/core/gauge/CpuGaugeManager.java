package org.chobit.jspy.core.gauge;

import org.chobit.jspy.core.info.OSInfoManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static org.chobit.jspy.core.gauge.ThreadsGaugeManager.allThreadIds;
import static org.chobit.jspy.core.gauge.ThreadsGaugeManager.getThreadCpuTime;

abstract class CpuGaugeManager {

    private static final int AVAILABLE_PROCESSORS = OSInfoManager.availableProcessors();

    private static long preTime = 0;
    private static Map<Long, Long> hisThreads = new HashMap<>(128);


    static double cpuLoad() {

        long elapsed = 0;
        Set<Long> threadIds = new TreeSet<>(hisThreads.keySet());

        for (long id : allThreadIds()) {
            threadIds.remove(id);

            long tCurr = getThreadCpuTime(id);
            Long tHis = hisThreads.put(id, tCurr);

            if (null == tHis) {
                continue;
            }

            elapsed += (tCurr - tHis);
        }

        threadIds.forEach(e -> hisThreads.remove(e));

        long curr = System.nanoTime();
        long passed = curr - preTime;
        preTime = curr;

        elapsed = elapsed < 0 ? 0 : elapsed;

        if (0 >= AVAILABLE_PROCESSORS) {
            return 0;
        }

        if (0 >= passed) {
            return 0;
        }

        double v = Math.round(10000.0 * elapsed / passed / AVAILABLE_PROCESSORS) / 100.0;

        return Math.min(v, 100.0);
    }


}
