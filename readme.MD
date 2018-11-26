# Spring Cloud for Distributed applications

Goal:
Distributed Micro Services Using Modern Web

<pre>

                                   +------+
                           +------>|api1  |
                           |       |      |
 +------+                  |       +------+
 |      |                  |
 |front |     +--------+   |
 |      +-----+        |   +       +------+
 +---+--+     |gateway +--++       |api1  |
     |        |        |   +------->      |
     |        +--------+   |       +------+
     |                     |
     |                     |
     |                     |
     +-->+--v------+       |       +------+
         |discovery|       |       |api2  |
         |         |       +------>|      |
         +---------+       |       +------+
                           |
                           |
                           |       +------+
                           |       |api2  |
                           +------>|      |
                                   +------+
</pre>

Links:
* https://spring.io/guides/gs/multi-module/
* http://spring.io/projects/spring-cloud-netflix