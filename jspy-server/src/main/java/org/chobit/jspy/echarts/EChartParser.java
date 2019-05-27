package org.chobit.jspy.echarts;

import org.chobit.jspy.echarts.annotation.EChartsTitle;
import org.chobit.jspy.echarts.annotation.Series;
import org.chobit.jspy.tools.LowerCaseKeyMap;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EChartParser {


    private Map<String, String> seriesMap = new HashMap<>(6);


    public EChartModel parse(List<LowerCaseKeyMap> data, Class resultModel) {

    }


    private void parseResultModel(Class model) {

        for (Field f : model.getFields()) {
            if (f.isAnnotationPresent(Series.class)) {
                Series series = f.getAnnotation(Series.class);

            }
        }
        ;
    }


}
