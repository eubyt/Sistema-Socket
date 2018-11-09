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


	private String mensagem; //mensagem a ser enviada
	private boolean fechar; //fechar a conex�o do socket
	private Socket socket; //destino 
	
	
	//Setar valores
	private void enviar(String mensagem, Socket socket, Boolean fechar) {
		this.mensagem = mensagem;
		this.socket = socket;
		this.fechar = fechar;
	}

	
	public void Executar() {
		try {
			PrintStream msg = new PrintStream(socket.getOutputStream()); //Preparar socket para enviar a mensagem
			msg.println(mensagem); //Setando e enviando a mensagem
			if (fechar)
				msg.close(); //Fechar conex�o
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
}
