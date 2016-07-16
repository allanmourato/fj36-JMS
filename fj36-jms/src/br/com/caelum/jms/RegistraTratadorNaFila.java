package br.com.caelum.jms;

import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class RegistraTratadorNaFila {
	
	public static void main(String[] args) throws NamingException {
		
		InitialContext ic = new InitialContext();
		
		ConnectionFactory factory = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		Queue queue = (Queue) ic.lookup("jms/FILA.GERADOR");
		try (JMSContext context = factory.createContext("jms", "jms2")) {
			JMSConsumer consumer = context.createConsumer(queue);
			consumer.setMessageListener(new TratadorDeMensagem());
			context.start();
			Scanner teclado = new Scanner(System.in);
			System.out.println("Tratador esperando as mensagens na fila JMS.");
			
			teclado.nextLine();
			
			teclado.close();
			context.stop();
		}
	}

}
