package com.webnori.psmon.cloudspring.lobbyapi;

import com.webnori.psmon.cloudspring.library.service.MyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LobbyapiApplicationTests {
	private static final Logger LOGGER = LoggerFactory.getLogger(LobbyapiApplicationTests.class);

	@Test
	public void contextLoads() {
		//LOGGER.info(myService.message());
	}

}
