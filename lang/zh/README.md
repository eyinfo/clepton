![logo](/img/logo.png)

---

#### Clepton

Clepton这是一个简化微服务开发框架，通过maven或gradle导入引用后经过简单的配置即可实现微服务必要的各项功能（拦截器、鉴权、aop、回收/缓存/集群策略、服务调度）。同时WebX层也为微服务RESTFull
Api开发提供丰富的数据响应格式。为微服务组件提供可插拔式无侵入性解决方案。
![framework](/img/service-framework.png)

#### 文档

- [英文文档](/README.md)

#### 更新内容

> [历史更新](/logs/history_update_zh.md)

- 框架插件模块调度与设计；
- 提供缓存策略配置；
- 完善WebX服务基础配置项；
- 持久层MyBatis自定义插件配置与使用（在MyBatis基础上扩展，非MyBatis-plus但功能与之差不多）
- 完善与丰富REST Full API数据响应格式；

> 待更新内容

- JWT实现API一键鉴权与动态配置；
- 实现服务全量日志记录（支持动态配置和分布式日志框架）；
- 一键集成与配置分布式服务监控插件；
- 框架集成Spring Security鉴权，从校验->UI对外提供简化配置；

#### 依赖引用

```xml
<dependency>
    <groupId>io.github.eyinfo</groupId>
    <artifactId>webx</artifactId>
    <version>1.0.0</version>
</dependency>
```
#### Springboot/cloud 快速集成
