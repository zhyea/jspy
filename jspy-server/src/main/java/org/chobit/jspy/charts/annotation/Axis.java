package org.chobit.jspy.charts.annotation;

import org.chobit.jspy.charts.AxisType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface Axis {

    AxisType type() default AxisType.INT;

    String format() default "";

}
