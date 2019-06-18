package org.chobit.jspy.interceptor;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;
import org.springframework.lang.Nullable;

public class BeanFactoryWatcherAttributeSourceAdvisor extends AbstractBeanFactoryPointcutAdvisor {


    @Nullable
    private WatcherAttributeSource watcherAttributeSource;


    private final WatcherAttributeSourcePointcut pointcut = new WatcherAttributeSourcePointcut() {
        @Override
        protected WatcherAttributeSource getWatcherAttributeSource() {
            return watcherAttributeSource;
        }
    };


    public void setWatcherAttributeSource(WatcherAttributeSource watcherAttributeSource) {
        this.watcherAttributeSource = watcherAttributeSource;
    }


    public void setClassFilter(ClassFilter classFilter) {
        this.pointcut.setClassFilter(classFilter);
    }


    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }


}
