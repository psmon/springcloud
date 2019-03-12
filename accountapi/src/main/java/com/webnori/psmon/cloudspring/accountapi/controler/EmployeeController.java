package com.webnori.psmon.cloudspring.accountapi.controler;

import com.webnori.psmon.cloudspring.accountapi.persistence.User;
import com.webnori.psmon.cloudspring.accountapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/demo")
public class EmployeeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private UserRepository userRepository;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/department/{departmentId}")
    public List findByDepartment(@PathVariable("departmentId") Long departmentId) {
        LOGGER.info("Employee find: departmentId={}", departmentId);
        List<String>    dummyList = new ArrayList<>();
        dummyList.add("t1");
        dummyList.add("t2");
        dummyList.add(serverPort);
        dummyList.add(departmentId.toString());
        return dummyList;
    }

    @GetMapping(path="/add") // Map ONLY GET Requests
    public @ResponseBody String addNewUser (@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping("/user/{name}")
    public String findEmailByName(@PathVariable("name") String name) {
        return userRepository.findOneByName(name).getEmail();
    }

    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

}
