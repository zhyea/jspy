package org.chobit.jspy.interceptor;

import org.chobit.jspy.WatcherConfig;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;


public class WatcherProxyConfiguration {


    @Bean
    @ConditionalOnBean(WatcherConfig.class)
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public WatcherAttributeSource watcherAttributeSource() {
        return new AnnotationWatcherAttributeSource();
    }


    @Bean
    @ConditionalOnBean(WatcherConfig.class)
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public WatcherInterceptor watcherInterceptor(WatcherConfig config) {
        WatcherInterceptor interceptor = new WatcherInterceptor(config);
        interceptor.setAttrSource(watcherAttributeSource());
        return interceptor;
    }


    @Bean
    @ConditionalOnBean(WatcherConfig.class)
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public BeanFactoryWatcherAttributeSourceAdvisor watcherAdvisor(WatcherConfig config) {
        BeanFactoryWatcherAttributeSourceAdvisor advisor = new BeanFactoryWatcherAttributeSourceAdvisor();
        advisor.setWatcherAttributeSource(watcherAttributeSource());
        advisor.setAdvice(watcherInterceptor(config));
        return advisor;
    }
}
