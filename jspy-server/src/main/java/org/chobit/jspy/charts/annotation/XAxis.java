package org.chobit.jspy.charts.annotation;

import org.chobit.jspy.charts.AxisType;
import org.chobit.jspy.charts.ValueType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface XAxis {

    AxisType type() default AxisType.value;

    ValueType valueType() default ValueType.INT;

    String format() default "";

}
