# RabbitMQ : Messaging with Spring Boot

### Reference Documentation

* Swagger - http://localhost:8082/mongo-db/swagger-ui/index.html#/
* Get All API - http://localhost:8082/mongo-db/api/student/all
* Project requirements
    * Maven
    * Java 21
    * Intellij
    * Rabbit MQ
* Each type of publisher/consumer present in

````
package com.demo.rabbitmq
````

* RabbitMQ Exchanges:
    * Real Time (with JSON)
        * Queue-1
    * Direct-Exchange [_type=direct_]
        * ![img.png](img.png)
        * AC
        * TV
        * Mobile
    * Fanout-Exchange [_type=fanout_]
        * ![img_1.png](img_1.png)
        * AC
        * Mobile
    * Topic-Exchange [_type=topic_]
        * ![img_2.png](img_2.png)
        * AC - _# more than 1 word allowed_
        * Mobile - _* only 1 word allowed_
        * TV - _fails for key=tv.mobile.ac_
    * Topic-Exchange [_type=headers_]
        * ![img_3.png](img_3.png)
        * ![img_4.png](img_4.png)
        * any - _* implies or*_
        * all - _* implies and*_