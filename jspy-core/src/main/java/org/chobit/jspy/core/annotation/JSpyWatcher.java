package org.chobit.jspy.core.annotation;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface JSpyWatcher {

    /**
     * name of method
     */
    String value() default "";

}
