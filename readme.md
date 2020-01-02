<a href="https://github.com/zhyea/jspy/wiki">
   <img src="https://github.com/zhyea/jspy/blob/master/doc/imgs/spy.png" width="96px" height="108px" alt="JSPY" >
</a>


# Summary  

JSPY是一个Java应用性能监控分析工具(APM)，适用于中小型开发团队。  

JSPY的特点如下：

1. 轻量级，不超过10k的客户端依赖
1. 代码低侵入，最严重的侵入也只是添加一行注解
1. 丰富的监测指标，包括内存、CPU、类加载、线程以及目标方法调用等指标 
1. 对各种指标均提供了直观的报表和列表展示
1. 保存历史指标数据，便于问题追踪
1. 定期整理冗余历史数据，减少资源占用

更多详情及**使用文档**请参考[ &nbsp;WIKI&nbsp; ](https://github.com/zhyea/jspy/wiki)。

# License

[GNU GENERAL PUBLIC LICENSE (GNU)](https://raw.githubusercontent.com/zhyea/jspy/dev2/LICENSE)

# Credits

JSPY依赖了如下项目：

* JSPY-CORE  
    * oshi

* JSPY-CLIENT  
    * quartz
    * jackson
    * okHttp

* JSPY-SERVER & JSPY-SPRING-BOOT-STARTER  
    * spring
    * dbcp2
    * mybatis
    * h2
    * BootStrap
    * JQuery
    * eCharts

* JSPY-AGENT  
    * ByteBuddy
