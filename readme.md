# 简介  
JSpy是一个Java分析工具。它提供了如下的功能：  
* 统计方法执行时间
* 计算Java对象的size
* 获取Java应用运行时信息（进程ID，启动时间，运行时长，虚拟机信息）
* 获取Java应用运行时内存信息（内存用量，GC信息）
* 获取操作系统概况


# 使用  
JSpy主要由两部分构成：jspy-agent和jspy-core。  
具体使用方式可以参考wiki：https://github.com/zhyea/jspy/wiki


# 计划  
jspy后续开发计划如下：
* 优化对象size计算方案  
* jvm线程监控，根据线程名称监控活跃线程数
* 数据库定期清理，根据数据近似度执行清理


