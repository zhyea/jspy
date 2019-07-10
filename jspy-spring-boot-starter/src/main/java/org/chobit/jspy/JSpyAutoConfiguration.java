package org.chobit.jspy;


import org.chobit.jspy.interceptor.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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
        return properties.getWatcher();
    }


    @Configuration
    @Import({ WatcherProxyConfiguration.class })
    @ConditionalOnBean(WatcherConfig.class)
    public static class WatcherConfigFoundConfiguration implements InitializingBean {

        @Override
        public void afterPropertiesSet() {
        }
    }
}
