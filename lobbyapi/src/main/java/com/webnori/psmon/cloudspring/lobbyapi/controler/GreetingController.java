package com.webnori.psmon.cloudspring.lobbyapi.controler;


import com.webnori.psmon.cloudspring.library.service.MyService;
import com.webnori.psmon.cloudspring.lobbyapi.restclient.AccountClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RefreshScope
@RestController
public class GreetingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingController.class);

    @Autowired
    AccountClient accountClient;

    private final MyService myService;

    public GreetingController(MyService myService) {
        this.myService = myService;
    }

    // Config Server Test
    @Value("${message:Hello default}")
    private String message;

    @RequestMapping("/message")
    String getMessage() {
        return this.message;
    }

    @RequestMapping("/message2")
    String getMessage2() {
        return myService.message();
    }

    @GetMapping("/organization/{organizationId}/with-employees")
    public List findByOrganizationWithEmployees(@PathVariable("organizationId") Long organizationId) {
        LOGGER.info("Department find: organizationId={}", organizationId);
        List departments = accountClient.findByDepartment(organizationId.toString());
        return departments;
    }

}
