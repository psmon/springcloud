## Fast Data



### KAFKA

### Kafka usage strategy and usage range limitation

Here Kafka is used only as a queue for batch or analysis purposes. The reason for not using this as the main processing message queue for the service is as follows:
* Building a cluster of Kafka is very difficult.
* Kafka does not guarantee message order and Exactly-once. It is difficult or impossible to match order guarantees with Kafka.

Try using only KAFKA without AKKA in the following two issues It will be very difficult to solve it. That's why we chose AKKA as our main messaging tool.
If you use Kafka only as a data transmission for a batch or analysis system, Kafka will be a very fast and easy to use message queue.


### KAFKA Scope

![kafkascope](library/doc-res/kafka-scope.png)

### KAFKA with AKKA
- [atleastonce](https://doc.akka.io/docs/akka-stream-kafka/current/atleastonce.html)
- [transactions](https://doc.akka.io/docs/akka-stream-kafka/current/transactions.html)
