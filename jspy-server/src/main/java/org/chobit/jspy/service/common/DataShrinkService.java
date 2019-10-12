package org.chobit.jspy.service.common;

import org.chobit.jspy.tools.Counter;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.chobit.jspy.constants.Constants.DEFAULT_ID_COLUMN;
import static org.chobit.jspy.constants.Constants.DEFAULT_TIME_COLUMN;

@Service
public class DataShrinkService {


    private static final int INTERVAL_MINUTES = 15;


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
    Set<Integer> computeIdsToDel(List<LowerCaseKeyMap> src,
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
    private Set<Integer> computeIdsToDel(List<LowerCaseKeyMap> src,
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
        //计算出的这段数据的浮动区间
        Map<String, Double> floatingRanges = computeFloatingRange(seg, nonMetricColumns);
        //收集报表指标
        Set<String> metrics = metricKeys(seg.get(0), nonMetricColumns);

        for (String m : metrics) {
            List<LowerCaseKeyMap> sorted = seg.stream()
                    .sorted((e1, e2) -> (e1.getDouble(m) > e2.getDouble(m)) ? 1 : 0)
                    .collect(Collectors.toList());
            double f = floatingRanges.get(m);
            shrink2(sorted, m, f, result);
        }

        result.sort((e1, e2) -> e1.getTime(DEFAULT_TIME_COLUMN) > e2.getTime(DEFAULT_TIME_COLUMN) ? 1 : 0);

        return result;
    }


    /**
     * 对数据进行缩减
     * <p>
     * 策略是从数据集两端按浮动区间对其进行缩减操作
     *
     * @param sortedList    排序后的数据集
     * @param metric        指标
     * @param floatingRange 浮动区间
     * @param result        结果集存储对象
     */
    private void shrink2(List<LowerCaseKeyMap> sortedList,
                         String metric,
                         double floatingRange,
                         List<LowerCaseKeyMap> result) {
        if (sortedList.size() <= 0) {
            return;
        }
        int size = sortedList.size();
        // 将当前数据集的第一个值（当前最大/最小值）置入结果集
        LowerCaseKeyMap ruler = sortedList.get(0);
        if (!result.contains(ruler)) {
            result.add(ruler);
        }
        // 迭代数据，开始按浮动区间进行缩减
        double r = ruler.getDouble(metric);
        for (int i = 1; i < size; i++) {
            double v = sortedList.get(i).getDouble(metric);
            if (floatingRange < Math.abs(v - r)) {
                List<LowerCaseKeyMap> sub = sortedList.subList(i, size - 1);
                // 将排序后的数据集反转，从另一端重新开始缩减
                Collections.reverse(sub);
                shrink2(sub, metric, floatingRange, result);
            }
        }
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
