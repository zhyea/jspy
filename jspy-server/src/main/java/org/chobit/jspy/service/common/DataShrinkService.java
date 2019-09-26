package org.chobit.jspy.service.common;

import org.chobit.jspy.tools.Counter;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class DataShrinkService {


    private static final int INTERVAL_MINUTES = 15;

    private static final String DEFAULT_TIME_COLUMN = "event_time";

    private static final String DEFAULT_ID_COLUMN = "id";


    /**
     * 对查询结果进行缩减处理
     *
     * @param src 原始数据集
     * @return 缩减后的数据集
     */
    public List<LowerCaseKeyMap> shrink(List<LowerCaseKeyMap> src) {
        return shrink0(src, new String[]{DEFAULT_TIME_COLUMN});
    }


    /**
     * 计算要删除的数据ID
     *
     * @param src              源数据
     * @param nonMetricColumns 非指标字段名称
     * @return 计算出的要删除的ID
     */
    public Set<Integer> computeIdsToDel(List<LowerCaseKeyMap> src,
                                        String[] nonMetricColumns) {
        return computeIdsToDel(src, nonMetricColumns, DEFAULT_ID_COLUMN);
    }

    /**
     * 计算要删除的数据ID
     *
     * @param src              源数据
     * @param nonMetricColumns 非指标字段名称
     * @param idColumn         ID字段名称
     * @return 计算出的要删除的ID
     */
    public Set<Integer> computeIdsToDel(List<LowerCaseKeyMap> src,
                                        String[] nonMetricColumns,
                                        String idColumn) {
        // 计算缩水后留下来的记录
        List<LowerCaseKeyMap> reserved = shrink0(src, nonMetricColumns);
        // 移除保留的记录即是要删除的记录
        src.removeAll(reserved);

        List<Integer> ids = src.stream().map(e -> e.getInt(idColumn)).collect(Collectors.toList());

        return new TreeSet<>(ids);
    }

    /**
     * 执行具体的shrink操作
     *
     * @param src              源数据
     * @param nonMetricColumns 非指标字段
     * @return shrink后的数据集
     */
    private List<LowerCaseKeyMap> shrink0(List<LowerCaseKeyMap> src, String[] nonMetricColumns) {
        List<LowerCaseKeyMap> result = new LinkedList<>();

        long minTime = 0;
        List<LowerCaseKeyMap> seg = new LinkedList<>();
        for (LowerCaseKeyMap m : src) {
            long time = m.getTime(DEFAULT_TIME_COLUMN);
            if (minTime == 0) {
                minTime = time;
            }
            // 按时间截取数据
            if (time < minTime + TimeUnit.MINUTES.toMillis(INTERVAL_MINUTES)) {
                seg.add(m);
            } else {
                // 如截取的数据集规模不大于平均每分钟一条则不需要处理
                if (seg.size() <= INTERVAL_MINUTES * 1.5) {
                    result.addAll(seg);
                } else {
                    // 对截取的数据进行缩减
                    result.addAll(shrink1(seg, nonMetricColumns));
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
    private List<LowerCaseKeyMap> shrink1(List<LowerCaseKeyMap> seg, String[] nonMetricColumns) {

        List<LowerCaseKeyMap> result = new LinkedList<>();
        //进行比较的标尺
        LowerCaseKeyMap ruler = null;
        //计算出的这段数据的浮动区间
        Map<String, Double> floatingRanges = computeFloatingRange(seg, nonMetricColumns);
        //是否保留
        boolean reserve = false;

        for (LowerCaseKeyMap m : seg) {
            if (null == ruler) {
                ruler = m;
                result.add(m);
                continue;
            }
            Set<String> keys = metricKeys(m, nonMetricColumns);
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
    private Map<String, Double> computeFloatingRange(List<LowerCaseKeyMap> seg, String[] nonMetricColumns) {
        Map<String, Counter> counters = new HashMap<>(32);
        for (LowerCaseKeyMap m : seg) {
            Set<String> keys = metricKeys(m, nonMetricColumns);
            for (String k : keys) {
                // 使用Counter工具计算浮动区间
                Counter c = counters.get(k);
                if (null == c) {
                    c = new Counter();
                    counters.put(k, c);
                }
                c.add(m.getDouble(k));
            }
        }
        Map<String, Double> result = new HashMap<>(counters.size());
        counters.forEach((k, c) -> result.put(k, c.computeFloatingRange()));
        return result;
    }


    private Set<String> metricKeys(LowerCaseKeyMap map, String[] nonMetricColumns) {
        Set<String> keys = map.keySet();
        Set<String> r = new HashSet<>(keys);
        if (null != nonMetricColumns && nonMetricColumns.length > 0) {
            for (String c : nonMetricColumns) {
                r.remove(c);
            }
        } else {
            r.remove(DEFAULT_TIME_COLUMN);
        }
        return r;
    }
}
