package org.tungnt.springcms.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

public class SimpleMessageSender {
	
	private JmsTemplate jmsTemplate;
	
	public void sendMessage(final String message) {
		jmsTemplate.setDeliveryDelay(5000L);
		this.jmsTemplate.send(new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage jmsMessage = session.createTextMessage(message);
				System.out.println(">>> sending: "+jmsMessage.getText());
				// TODO Auto-generated method stub
				return jmsMessage;
			}
		});
	}
}
