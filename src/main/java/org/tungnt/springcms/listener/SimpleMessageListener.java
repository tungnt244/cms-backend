package org.tungnt.springcms.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

public class SimpleMessageListener {

	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		try {
			System.out.println("received: " + textMessage.getText());
		}catch (JMSException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
