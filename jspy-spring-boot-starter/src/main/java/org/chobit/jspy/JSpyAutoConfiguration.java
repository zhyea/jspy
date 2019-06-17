package org.chobit.jspy;


import org.chobit.jspy.interceptor.AnnotationWatcherAttributeSource;
import org.chobit.jspy.interceptor.WatcherAttributeSource;
import org.chobit.jspy.interceptor.WatcherInterceptor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

@Configuration
@ConditionalOnClass({JSpyClient.class})
@Import({AutoProxyRegistrar.class})
@EnableConfigurationProperties(JSpyProperties.class)
public class JSpyAutoConfiguration {


    private final JSpyProperties properties;


    public JSpyAutoConfiguration(JSpyProperties properties) {
        this.properties = properties;
    }


    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public WatcherAttributeSource jSpyWatcherAttributeSource() {
        return new AnnotationWatcherAttributeSource();
    }


    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public WatcherInterceptor jSpyWatcherInterceptor() {
        WatcherInterceptor interceptor = new WatcherInterceptor();
        interceptor.setAttrSource(jSpyWatcherAttributeSource());
        return interceptor;
    }

}
