package com.stephan.jms.hr;

import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class HRApp {

	public static void main(String[] args) throws NamingException {

	InitialContext	context=new InitialContext();
	Topic topic = (Topic) context.lookup("topic/empTopic");
	
	try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
			JMSContext jmsContext=cf.createContext()){
		
		Employee employee = new Employee();
		employee.setId(123);
		employee.setFirstname("stephan");
		employee.setLastname("frds");
		employee.setDesignation("software_engineer");
		employee.setEmail("stephan@stephan.com");
		employee.setPhone("123456");
		//shared subscription added
		for(int i=1;i<=10;i++) {
			jmsContext.createProducer().send(topic, employee);
		}
		
		//jmsContext.createProducer().send(topic, employee);
		System.out.println("Message sent");
		
		
	}
	
	}

}
