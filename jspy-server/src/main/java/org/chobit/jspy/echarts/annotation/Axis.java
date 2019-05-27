package org.chobit.jspy.echarts.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface Axis {

    String value() default "";

}
