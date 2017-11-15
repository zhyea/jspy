package com.zhyea.jspy.annotations;


import java.lang.annotation.*;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

@Target({TYPE, METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface JSpyTimer {
}