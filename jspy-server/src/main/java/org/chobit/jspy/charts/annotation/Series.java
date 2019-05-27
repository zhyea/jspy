package org.chobit.jspy.charts.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface Series {

    String value() default "";

}
