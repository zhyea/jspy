package com.zhyea.jspy.annotation;


import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@Documented
@Inherited
public @interface Watcher {


}
