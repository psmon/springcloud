# AKKA


## Messaging with AKKA System

![goal](doc-res/akka-actorpath.png)

src:
* [Spring with AKKA](../accountapi/src/main/java/com/webnori/psmon/cloudspring/accountapi/config)
* [Message for Actor](../library/src/main/java/com/webnori/psmon/cloudspring/library/common/message)
* [Actor](../accountapi/src/main/java/com/webnori/psmon/cloudspring/accountapi/actor)
* [Create Actor](../accountapi/src/main/java/com/webnori/psmon/cloudspring/accountapi/AccountapiApplication.java)


How to send a message to an actor from another remote application

    ActorSelection remoteGreeter = system.actorSelection("akka.tcp://accountapi@127.0.0.1:2552/user/greeter");
    remoteGreeter.tell(new Greet("This is Remote Message"),ActorRef.noSender());

Akka Documents Summary:
1. [Local Actor](https://doc.akka.io/docs/akka/current/actors.html#actor-api)
2. [Remote Actor](https://doc.akka.io/docs/akka/current/remoting.html)
3. [Router](https://doc.akka.io/docs/akka/current/routing.html)
4. [Stream](https://doc.akka.io/docs/akka/current/stream/stream-flows-and-basics.html)
5. [Cluster](https://doc.akka.io/docs/akka/current/common/cluster.html#intro)
6. [Akka with Any Stream](https://developer.lightbend.com/docs/alpakka/current/)

# Akka Remote

RemoteActor works similarly to LocalActor and is scalable.

more info : https://getakka.net/articles/Remoting/ - It is described in .net code, but it is the same as the concept of java.

![image](doc-res/rest-actor.png)

related src:
- [accountapi/actor](../accountapi/src/main/java/com/webnori/psmon/cloudspring/accountapi/actor)
- [accountapi/config](../accountapi/src/main/resources/application.conf)
- [lobbyapi/actorTest](../lobbyapi/src/test/java/com/webnori/psmon/cloudspring/lobbyapi/actor)


## Actor Config for Remote

    akka {
      actor {
        provider = remote
      }
      remote {
        enabled-transports = ["akka.remote.netty.tcp"]
        netty.tcp {
          hostname = "0.0.0.0"
          port = 2552
        }
      }
    }

## RemoteTest
    @Test
    public void testIt() {
        new TestKit(system) {{
            ActorRef probe = getRef();
            ActorSelection accountGreeter =
                    system.actorSelection("akka.tcp://accountapi@0.0.0.0:2552/user/greeter");

            accountGreeter.tell(new CMD_REMOTE(5,"hi"),getRef());
            String expectMessage = expectMsgClass(Duration.ofSeconds(5),CMD_REMOTE.class).getMessage();
        }};
    }



# Akka with DDD
Spring has a web total solution, and AKKA has a total solution for message processing. It may be a difficult attempt to harmonize the two things, but it will be a good experience and we will prepare some samples.

* [Akka-Actors-DDD-VER1](https://www.infoq.com/articles/Reactive-Systems-Akka-Actors-DomainDrivenDesign)
* [Akka-Actors-DDD-VER2](https://www.slideshare.net/Lightbend/using-the-actor-model-with-domaindriven-design-ddd-in-reactive-systems-with-vaughn-vernon)
* [AkkaEventSourcing](https://mromeh.com/2018/04/27/spring-boot-akka-event-sourcing-starter-part-1/)
* [more...](https://www.google.co.kr/search?newwindow=1&source=hp&ei=jQNCXIuxHYjhvASs1JmQAQ&q=spring+akka+ddd&btnK=Google+Search&oq=spring+akka+ddd)

## Compare with Other
* Spring Boot with DDD : preparing....
* KAFKA Stream with DDD : preparing....

