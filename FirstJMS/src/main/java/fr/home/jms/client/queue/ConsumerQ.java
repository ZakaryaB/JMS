package fr.home.jms.client.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ConsumerQ {

	public static void main(String[] args) {
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
			Destination destination = session.createQueue("Home.Queue");
			
			//Consommateur
			MessageConsumer consumer = session.createConsumer(destination);
			System.out.println("consumer ...");
			
			consumer.setMessageListener(new MessageListener() {
				
				@Override
				public void onMessage(Message message) {
					
					if(message instanceof TextMessage){
						try {
						TextMessage textMessage = (TextMessage) message;
							System.out.println(textMessage.getText());
						} catch (JMSException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("error : " + e.getMessage());
						}
					}
				}
			});
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
