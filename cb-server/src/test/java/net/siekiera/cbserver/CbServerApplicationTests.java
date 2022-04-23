package net.siekiera.cbserver;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CbServerApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

	@RepeatedTest(10)
	void repeatedTest() {
		restTemplate.getForObject("http://localhost:"+port+"/delayed", String.class);
	}

}
