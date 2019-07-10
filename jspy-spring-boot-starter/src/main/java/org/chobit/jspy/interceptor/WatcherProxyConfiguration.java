package org.chobit.jspy.interceptor;

import org.chobit.jspy.WatcherConfig;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;


public class WatcherProxyConfiguration {


    private WatcherConfig config;

    public WatcherProxyConfiguration(WatcherConfig config) {
        this.config = config;
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public WatcherAttributeSource watcherAttributeSource() {
        return new AnnotationWatcherAttributeSource();
    }


    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public WatcherInterceptor watcherInterceptor() {
        WatcherInterceptor interceptor = new WatcherInterceptor(config);
        interceptor.setAttrSource(watcherAttributeSource());
        return interceptor;
    }


    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public BeanFactoryWatcherAttributeSourceAdvisor watcherAdvisor() {
        BeanFactoryWatcherAttributeSourceAdvisor advisor = new BeanFactoryWatcherAttributeSourceAdvisor();
        advisor.setWatcherAttributeSource(watcherAttributeSource());
        advisor.setAdvice(watcherInterceptor());
        return advisor;
    }

}
