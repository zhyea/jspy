package org.chobit.jspy;


import org.chobit.jspy.interceptor.AnnotationJSpyWatcherAttributeSource;
import org.chobit.jspy.interceptor.JSpyWatcherAttributeSource;
import org.chobit.jspy.interceptor.JSpyWatcherInterceptor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

@Configuration
@ConditionalOnClass({JSpyClient.class})
@EnableConfigurationProperties(JSpyProperties.class)
public class JSpyAutoConfiguration {


    private final JSpyProperties properties;


    public JSpyAutoConfiguration(JSpyProperties properties) {
        this.properties = properties;
    }


    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public JSpyWatcherAttributeSource jSpyWatcherAttributeSource() {
        return new AnnotationJSpyWatcherAttributeSource();
    }


    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public JSpyWatcherInterceptor jSpyWatcherInterceptor() {
        JSpyWatcherInterceptor interceptor = new JSpyWatcherInterceptor();
        interceptor.setAttrSource(jSpyWatcherAttributeSource());
        return interceptor;
    }

}
