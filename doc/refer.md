


https://blog.csdn.net/houzhizhen/article/details/71534313


可以参考的内容

* metrics-ehcache
* metrics-httpclient
* metrics-jdbi
* metrics-jersey
* metrics-jetty
* metrics-log4j
* metrics-logback
* metrics-jvm
* metrics-servlet 注意不是metrics-servlets

以及
* metrics-librato 提供Librato Metrics报表
* Metrics Spring Integration 提供了Spring的集成
* sematext-metrics-reporter 提供了SPM报表.
* wicket-metrics提供Wicket应用.
* metrics-guice 提供Guice集成.
* metrics-scala 提供了为Scala优化的API.



https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-developing-auto-configuration.html

* Spring-boot-

## 要做的事情

* 替换数据库连接池为Hirik
* 分析Simple缓存的使用及配置方案
* 尝试在加载自动化配置项的时候排除某些配置
* 不再使用自定义的Caffeine配置，进行全局统一配置
* 完成定期删除过期数据的能力
* 完成数据shrink的能力
* 尝试去掉data.sql文件
