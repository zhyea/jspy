package org.chobit.jspy;


import org.chobit.jspy.interceptor.WatcherProxyConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnClass({JSpyClient.class})
@EnableConfigurationProperties(JSpyProperties.class)
@Import(WatcherProxyConfiguration.class)
public class JSpyAutoConfiguration {


    private final JSpyProperties properties;


    public JSpyAutoConfiguration(JSpyProperties properties) {
        this.properties = properties;
    }

}
