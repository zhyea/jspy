package org.chobit.jspy;


import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({JSpyClient.class})
@EnableConfigurationProperties(JSpyProperties.class)
public class JSpyAutoConfiguration {


    private final JSpyProperties properties;



    public JSpyAutoConfiguration(JSpyProperties properties) {
        this.properties = properties;
    }
}
