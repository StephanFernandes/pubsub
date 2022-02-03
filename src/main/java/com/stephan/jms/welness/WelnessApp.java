package com.stephan.jms.welness;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.stephan.jms.hr.Employee;

public class WelnessApp {

	public static void main(String[] args) throws NamingException, JMSException {

	InitialContext	context=new InitialContext();
	Topic topic = (Topic) context.lookup("topic/empTopic");
	
	try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
			JMSContext jmsContext=cf.createContext()){
		
		JMSConsumer consumer = jmsContext.createSharedConsumer(topic,"sharedconsumer");
		JMSConsumer consumer2 = jmsContext.createSharedConsumer(topic,"sharedconsumer");
		
		for(int i=1;i<=10;i+=2) {
		Message message = consumer.receive();
		Employee employee = message.getBody(Employee.class);//throws declation
		System.out.println("consumer1: "+employee.getFirstname());
		
		Message message2 = consumer2.receive();
		Employee employee2 = message2.getBody(Employee.class);//throws declation
		System.out.println("consumer2: "+employee2.getFirstname());
		}
		
		
		
		
		
	}
	
	}

}
