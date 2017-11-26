package com.zhyea.jspy.annotations;


import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface JSpyMeasure {
}
