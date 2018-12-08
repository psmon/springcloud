package com.webnori.psmon.cloudspring.lobbyapi;

import com.webnori.psmon.cloudspring.library.service.MyService;
import com.webnori.psmon.cloudspring.lobbyapi.restclient.AccountClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class LobbyapiApplicationTests {
	private static final Logger LOGGER = LoggerFactory.getLogger(LobbyapiApplicationTests.class);

	@Autowired
	AccountClient accountClient;

	@Test
	public void contextLoads() {
		// TestFor : Client Side Load Balancing with Ribbon
		// Reqired : Eureka Server / AccountAPI with Eureka
		List ribbonRuslt= accountClient.findByDepartment("1234");
		Assert.assertTrue(ribbonRuslt.size()>0);
	}

}
