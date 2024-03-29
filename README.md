
# PE Appointment

Demo project for the PE (Private Equity) Appointment service.

## **Milestone**

1. [CI/CD Setting in Gitlab](https://github.com/saLeox/GitLab_CICD_Instructor/blob/main/README.md)

2. [Data modeling](https://drive.google.com/file/d/1IsPhRL2Mh_ZsWuKF01jSWysiTcVBxlHT/view?usp=sharing)

3. Develop the persistence layer based on Druid and Mybaits-Plus

4. Deply onto Alibaba EC2 machine with the [API endpoint](http://47.93.30.94:8081/swagger-ui/index.html#/)

5. [Embed](https://github.com/saLeox/delayed-queue-rabbit/blob/main/README.md) RabbitMQ and Spring Cloud Stream to achieve delayed queue.

6. Register onto [Alibaba Nacos](http://47.93.30.94:8848/nacos/index.html#/serviceManagement?dataId=&group=&appName=&namespace=&pageSize=&pageNo=), and build a [Service Gateway](https://gitlab.com/gf-private-placement/gateway) with dynamic routing, online-document integration, [global logging](https://gitlab.com/gf-private-placement/logging) and [authentication](https://gitlab.com/gf-private-placement/uac).

7. Import the [workflow service](https://gitlab.com/gf-private-placement/workflow) to facilitate the approval processing.

8. [Mock microservice](https://gitlab.com/gf-private-placement/mock) for Client, Manager, and Call centre.

9. Implement [appointment validation](https://gitlab.com/gf-private-placement/appointment/-/tree/master/src/main/java/com/gof/springcloud/service/validator) based on strategy pattern and open feign.

10. Based on Redis, use distributed lock and lua script to [control the balance](https://github.com/saLeox/Concurrent-Balance-Deduction) under the control of Transaction management.

12. Develop the event endpoint that will be proceed by workflow.
