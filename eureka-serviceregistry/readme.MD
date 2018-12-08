# Eureka

default url :  http://localhost:8761

![enter image description here](../library/doc-res/spring-eureka.png)

This is how the normal Discovery Service works. The reason for this functionality in Micro Service is simple.
Let's say you want to increase and decrease the API flexibly during operation. The Discovery Service detects this and provides a flexible
You can continue to provide services to your users, which is useful even in the event of a disability. The node of the provided service suddenly goes down
Let's do it. The DiscoveryService will detect this and block access.

## Ribbon 

![Alt text](../library/doc-res/spring-eureka-concept.png)


Service

    # Application in Account API
    @EnableDiscoveryClient
    @EnableFeignClients
    @SpringBootApplication
    public class AccountapiApplication {...}
    
    # Controller in Account API
    @GetMapping("/department/{departmentId}")
    public List findByDepartment(@PathVariable("departmentId") Long departmentId) {...}

Client

    # Client Interface in LobbyAPI
    @FeignClient(name = "accountapi")
    public interface AccountClient {
        @GetMapping("/demo/department/{departmentId}")
        List findByDepartment(@PathVariable("departmentId") String departmentId);
    }
    
    # Test 
    @Autowired
    AccountClient accountClient;
    
    List ribbonRuslt= accountClient.findByDepartment("1234");
    Assert.assertTrue(ribbonRuslt.size()>0);

![Alt text](../library/doc-res/spring-ribbon.png)

We can call Rest, which is easily load balanced with a known application name.

## Docker
* build : docker build -t psmon.eureka .
* run : docker run --net psmonlocal --ip 172.18.1.2 -it -d -p 18761:8761 -e "SPRING_PROFILES_ACTIVE=dock-local" --name eureka1 psmon.eureka
* rerun : docker restart eureka1
* stop : docker stop eureka1

## NetWork
* network : docker network create --subnet=172.18.0.0/16 psmonlocal
* docker network inspect bridge



## Documents(KOR):
* doc : http://wiki.webnori.com/display/webfr/Discovery+Service+-+Eureka)
