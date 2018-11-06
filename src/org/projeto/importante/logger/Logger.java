package org.projeto.importante.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.projeto.Sistema;
import org.projeto.Sistema.Tipo;

public class Logger {

	
	public Logger(String mensagem, Tipo tipo) {
		String msg = data() + " [" + tipo.name() + "] " + mensagem;
		System.out.println(msg);
	}


	public String Logger(String mensagem, Sistema.Tipo tipo) {
		String msg = data() + " [" + tipo.name() + "] " + mensagem;
		System.out.println(msg);
		return msg;
	}
	
	
	private String data() {
		Date data = new Date();
		return new SimpleDateFormat("[dd/MM/yyyy - HH:mm:ss]").format(data);
	}
	
	

}
