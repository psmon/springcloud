package com.webnori.psmon.cloudspring.lobbyapi.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "accountapi")
public interface AccountClient {
    @GetMapping("/demo/department/{departmentId}")
    List findByDepartment(@PathVariable("departmentId") String departmentId);

    @GetMapping("demo/add")
    String addUser(@RequestParam String name, @RequestParam String email);


    @GetMapping("demo/user/{name}")
    String findEmailByName(@PathVariable String name);

}
