package org.chobit.jspy.charts.annotation;


import org.chobit.jspy.charts.ChartType;
import org.chobit.jspy.charts.ValueType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.chobit.jspy.charts.ValueType.LONG;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Series {

    String value() default "";

    String id() default "";

    boolean selected() default true;

    int yAxisIndex() default 0;

    ChartType type() default ChartType.line;

    ValueType valType() default LONG;

    String unit() default "";
}
