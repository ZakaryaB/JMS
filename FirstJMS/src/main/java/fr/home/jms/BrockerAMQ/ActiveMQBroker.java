package fr.home.jms.BrockerAMQ;

import org.apache.activemq.broker.BrokerService;

public class ActiveMQBroker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			BrokerService brokerService = new BrokerService();
			brokerService.setPersistent(false);
			brokerService.addConnector("tcp://0.0.0.0:61616");
			brokerService.start();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
