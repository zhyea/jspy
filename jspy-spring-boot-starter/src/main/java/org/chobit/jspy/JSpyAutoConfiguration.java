package org.chobit.jspy;


import org.chobit.jspy.interceptor.AnnotationWatcherAttributeSource;
import org.chobit.jspy.interceptor.BeanFactoryWatcherAttributeSourceAdvisor;
import org.chobit.jspy.interceptor.WatcherAttributeSource;
import org.chobit.jspy.interceptor.WatcherInterceptor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

@Configuration
@ConditionalOnClass({JSpyClient.class})
@EnableConfigurationProperties(JSpyProperties.class)
public class JSpyAutoConfiguration {


    @Bean
    public JSpyClientStarter jSpyClientStarter(JSpyProperties properties) {
        return new JSpyClientStarter(properties);
    }


    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public WatcherAttributeSource watcherAttributeSource() {
        return new AnnotationWatcherAttributeSource();
    }


    @Bean
    @ConditionalOnProperty(prefix = "jspy.watcher", name = "enable", havingValue = "true")
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public WatcherInterceptor watcherInterceptor(JSpyProperties properties) {
        WatcherInterceptor interceptor = new WatcherInterceptor(properties.getWatcher());
        interceptor.setAttrSource(watcherAttributeSource());
        return interceptor;
    }


    @Bean
    @ConditionalOnProperty(prefix = "jspy.watcher", name = "enable", havingValue = "true")
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public BeanFactoryWatcherAttributeSourceAdvisor watcherAdvisor(JSpyProperties properties) {
        BeanFactoryWatcherAttributeSourceAdvisor advisor = new BeanFactoryWatcherAttributeSourceAdvisor();
        advisor.setWatcherAttributeSource(watcherAttributeSource());
        advisor.setAdvice(watcherInterceptor(properties));
        return advisor;
    }

}
