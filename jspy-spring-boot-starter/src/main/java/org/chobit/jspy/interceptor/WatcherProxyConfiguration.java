package org.chobit.jspy.interceptor;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

@Configuration
public class WatcherProxyConfiguration {


    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public WatcherAttributeSource watcherAttributeSource() {
        return new AnnotationWatcherAttributeSource();
    }


    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public WatcherInterceptor watcherInterceptor() {
        WatcherInterceptor interceptor = new WatcherInterceptor();
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
