package fr.home.jms.client.topic;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ProducerT {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Vers:");
		String code = scanner.nextLine();
		try {
			// spécifier l'@ du Brocker à travers TCP
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
			
			//Connection
			Connection connection = connectionFactory.createConnection();
			connection.start();
			
			/*Session: la session n'est pas transactionnelle (pas de commit pour envoyer le msg) et 
			auto ACK pour demander un accusé de réception*/
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			//Destination
			Destination destination = session.createTopic("Home.Topic");
			
			//Proucteur
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			
			//create message
			TextMessage message = session.createTextMessage();
			message.setText("Helle word.. Im a Producer");
			message.setStringProperty("code", code);
			//envoie
			producer.send(message);
			System.out.println("message sent !!");
			
			//clean 
			session.close();
			connection.close();
			
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
