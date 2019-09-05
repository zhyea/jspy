package org.chobit.jspy.service.common;

import org.chobit.jspy.tools.Counter;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class DataShrinkService {


    private static final int INTERVAL_MINUTES = 6;

    private static final String TIME_COLUMN = "event_time";


    /**
     * 对查询结果进行缩减处理
     *
     * @param src 原始数据集
     * @return 缩减后的数据集
     */
    public List<LowerCaseKeyMap> shrink(List<LowerCaseKeyMap> src) {
        List<LowerCaseKeyMap> result = new LinkedList<>();

        long minTime = 0;
        List<LowerCaseKeyMap> seg = new LinkedList<>();
        for (LowerCaseKeyMap m : src) {
            long time = m.getTime(TIME_COLUMN);
            if (minTime == 0) {
                minTime = time;
            }
            // 按时间截取数据
            if (time < minTime + TimeUnit.MINUTES.toMillis(INTERVAL_MINUTES)) {
                seg.add(m);
            } else {
                // 如截取的数据集规模不大则不需要处理
                if (seg.size() <= 5) {
                    result.addAll(seg);
                } else {
                    result.addAll(shrink1(seg));
                }

                seg.clear();
                minTime = 0;
            }
        }

        return result;
    }


    /**
     * 对一段数据进行缩减处理
     */
    private List<LowerCaseKeyMap> shrink1(List<LowerCaseKeyMap> seg) {

        List<LowerCaseKeyMap> result = new LinkedList<>();
        //进行比较的标尺
        LowerCaseKeyMap ruler = null;
        //计算出的这段数据的浮动区间
        Map<String, Double> floatingRanges = computeFloatingRange(seg);
        //是否保留
        boolean reserve = false;

        for (LowerCaseKeyMap m : seg) {
            if (null == ruler) {
                ruler = m;
                result.add(m);
                continue;
            }
            Set<String> keys = m.keySet();
            for (String k : keys) {
                double r = ruler.getDouble(k);
                double v = m.getDouble(k);
                double f = floatingRanges.get(k);
                // 当浮动区间小于当前数据和标尺数据之差时，该数据可以保留
                if (f < Math.abs(v - r)) {
                    reserve = true;
                    break;
                }
            }

            if (reserve) {
                ruler = m;
                result.add(m);
            }

            reserve = false;
        }
        return result;
    }


    /**
     * 计算一段查询结果每个字段的浮动区间
     */
    private Map<String, Double> computeFloatingRange(List<LowerCaseKeyMap> seg) {
        Map<String, Counter> counters = new HashMap<>(8);
        for (LowerCaseKeyMap m : seg) {
            Set<String> keys = new HashSet<>(m.keySet());
            keys.remove(TIME_COLUMN);
            for (String k : keys) {
                Counter c = counters.get(k);
                if (null == c) {
                    c = new Counter();
                    counters.put(k, c);
                }
                c.add(m.getDouble(k));
            }
        }
        Map<String, Double> result = new HashMap<>(counters.size());
        for (Map.Entry<String, Counter> e : counters.entrySet()) {
            result.put(e.getKey(), e.getValue().computeFloatingRange());
        }
        return result;
    }
}
