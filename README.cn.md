
# 私募预约

私募预约DEMO项目
  
## **里程碑**

1. [CI/CD 搭建 in Gitlab](https://github.com/saLeox/GitLab_CICD_Instructor/blob/main/README.md)

2. [数据建模](https://drive.google.com/file/d/1IsPhRL2Mh_ZsWuKF01jSWysiTcVBxlHT/view?usp=sharing)

3. 使用 Druid和Mybaits-Plus构建持久层

4. 部署到阿里云虚机 [入口](http://47.93.30.94:8081/swagger-ui/index.html#/)

5. [嵌入](https://github.com/saLeox/delayed-queue-rabbit/blob/main/README.md) RabbitMQ 和 Spring Cloud Stream以支持延时任务.

6. 注册至 [Alibaba Nacos](http://47.93.30.94:8848/nacos/index.html#/serviceManagement?dataId=&group=&appName=&namespace=&pageSize=&pageNo=), 搭建具有动态路由，全局在线文档，[全局日志](https://gitlab.com/gf-private-placement/logging)，和[鉴权](https://gitlab.com/gf-private-placement/uac) 的[服务路由](https://gitlab.com/gf-private-placement/gateway) .

7. 引入 [工作流微服务](https://gitlab.com/gf-private-placement/workflow) 来支持流转操作.

8. 构建客户，客户经理和呼叫中心的 [Mock microservice](https://gitlab.com/gf-private-placement/mock).

9. 基于策略模式与Open feign实现 [预约校验](https://gitlab.com/gf-private-placement/appointment/-/tree/master/src/main/java/com/gof/springcloud/service/validator) .

10. 使用 mybaits-plus乐观锁 (CAS) 和 Transaction management开发预约事务.

11. 开发其余API端口供工作流模块流转.
