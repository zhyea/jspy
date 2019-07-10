package org.chobit.jspy;


import org.chobit.jspy.interceptor.*;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Role;

@Configuration
@ConditionalOnClass({JSpyClient.class})
@EnableConfigurationProperties(JSpyProperties.class)
@Import(WatcherProxyConfiguration.class)
public class JSpyAutoConfiguration {


    @Bean
    public JSpyClientStarter jSpyClientStarter(JSpyProperties properties) {
        return new JSpyClientStarter(properties);
    }


    @Bean
    public WatcherConfig watcherConfig(JSpyProperties properties) {
        return null;
    }


    @Bean
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
