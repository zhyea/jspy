package org.chobit.jspy.core.annotation;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface JSpyWatcher {

    String value() default "";

}
