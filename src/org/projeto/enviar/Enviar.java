package org.projeto.enviar;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Enviar {
	
	public Enviar(String mensagem, Socket socket) {
		enviar(mensagem, socket, true);
	}

	public Enviar(String mensagem, Socket socket, Boolean fechar) {
		enviar(mensagem, socket, fechar);
	}


	private String mensagem;
	private boolean fechar;
	private Socket socket;
	
	private void enviar(String mensagem, Socket socket, Boolean fechar) {
		this.mensagem = mensagem;
		this.socket = socket;
		this.fechar = fechar;
	}

	
	public void Executar() {
		try {
			PrintStream msg = new PrintStream(socket.getOutputStream());
			msg.println(mensagem);
			if (fechar)
				msg.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
}
