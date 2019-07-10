package org.chobit.jspy;


import org.chobit.jspy.interceptor.WatcherProxyConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({JSpyClient.class})
@EnableConfigurationProperties(JSpyProperties.class)
public class JSpyAutoConfiguration {


    @Bean
    public JSpyClientStarter jSpyClientStarter(JSpyProperties properties) {
        return new JSpyClientStarter(properties);
    }


    @Bean
    public WatcherProxyConfiguration watcherProxyConfiguration(JSpyProperties properties) {
        if (null != properties.getWatcher()) {
            return new WatcherProxyConfiguration(properties.getWatcher());
        }
        return null;
    }

}
