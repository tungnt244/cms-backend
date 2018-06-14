package org.tungnt.springcms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.tungnt.springcms.service.SimpleMessageSender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringCmsApplicationTests {
	
	@Autowired
	SimpleMessageSender messageSender;
	
	@Test
	public void contextLoads() {
		for(int i=0; i< 10; i++) {
			messageSender.sendMessage("Text message"+i);
		}
	}

}
