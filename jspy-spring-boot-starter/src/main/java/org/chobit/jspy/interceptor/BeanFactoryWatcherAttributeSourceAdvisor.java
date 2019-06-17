package org.chobit.jspy.interceptor;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;

public class BeanFactoryWatcherAttributeSourceAdvisor extends AbstractBeanFactoryPointcutAdvisor {



    @Override
    public Pointcut getPointcut() {
        return null;
    }



}
