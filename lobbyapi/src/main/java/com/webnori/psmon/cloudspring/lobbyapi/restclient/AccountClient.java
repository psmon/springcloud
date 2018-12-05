package com.webnori.psmon.cloudspring.lobbyapi.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "accountapi")
public interface AccountClient {
    @GetMapping("/department/{departmentId}")
    List findByDepartment(@PathVariable("departmentId") String departmentId);
}
