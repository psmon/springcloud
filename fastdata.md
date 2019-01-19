## Fast Data

We will separate our snow data types as follows to implement different message usage strategies.
- RealTime Message with DomainLogic for React 
- BigData for Analysis
- FastData for Statistics

### KAFKA

Pure Kafka are generally difficult to handle four things:
* Cluster
* guarantee message order
* guarantee message exactly-once
* Back pressure

Although Kafka can be used across the service level, 
the architecture is likely to complement Kafka's shortcomings.
So we will only use Kafka here for analysis or batch purposes.


### KAFKA Scope

![kafkascope](library/doc-res/kafka-scope.png)

### KAFKA with AKKA
- [atleastonce](https://doc.akka.io/docs/akka-stream-kafka/current/atleastonce.html)
- [transactions](https://doc.akka.io/docs/akka-stream-kafka/current/transactions.html)
