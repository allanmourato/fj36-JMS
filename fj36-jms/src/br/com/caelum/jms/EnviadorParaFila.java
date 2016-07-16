package br.com.caelum.jms;
import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class EnviadorParaFila {

	public static void main(String[] args) throws NamingException {

		InitialContext ic = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		Queue queue = (Queue) ic.lookup("jms/FILA.GERADOR");
		
		try(JMSContext context = factory.createContext("jms", "jms2")) {
			JMSProducer producer = context.createProducer();
			Scanner scanner = new Scanner(System.in);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				producer.send(queue, line);
			}
			scanner.close();
		}
	}

}
