package org.chobit.jspy;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import org.chobit.jspy.core.annotation.JSpyWatcher;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.declaresMethod;
import static net.bytebuddy.matcher.ElementMatchers.isAnnotatedWith;


public class JSpyAgent {


    public static void premain(String options, Instrumentation ins) {

        // 启动JSpyClient，开始搜集数据
        JSpyClient client = Client.build(options);
        client.start();

        // 配置shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> client.shutdown(true)));

        // 配置会对含有JSpyWatcher注解的类进行aop
        new AgentBuilder.Default()
                .type(declaresMethod(isAnnotatedWith(JSpyWatcher.class)))
                .transform(
                        (builder, type, classLoader, module) ->
                                builder.method(isAnnotatedWith(JSpyWatcher.class))
                                        .intercept(MethodDelegation.to(WatcherInterceptor.class))
                ).installOn(ins);
    }


}
