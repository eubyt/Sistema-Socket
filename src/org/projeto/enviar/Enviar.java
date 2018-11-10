package org.projeto.enviar;

import org.projeto.Sistema;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
	private boolean fechar; //fechar a conexão do socket
	private Socket socket; //destino

	private byte[] bytes;
	private boolean enviar_bytes = false;


	public Enviar(byte[] bytes, Socket servidor, boolean fechar) {
		this.bytes = bytes;
		this.enviar_bytes = true;
		this.socket = servidor;
		this.fechar = fechar;
	}

	//Setar valores
	private void enviar(String mensagem, Socket socket, Boolean fechar) {
		this.mensagem = mensagem;
		this.socket = socket;
		this.fechar = fechar;
	}

	
	public void Executar() {

		try {
		if (enviar_bytes) {

			socket.getOutputStream().write(bytes);

			if (fechar)
				socket.getOutputStream().close(); //Fechar conexão
		} else {

				PrintStream msg = new PrintStream(socket.getOutputStream()); //Preparar socket para enviar a mensagem
				msg.println(mensagem); //Setando e enviando a mensagem
				if (fechar)
					msg.close(); //Fechar conexão
		}
		} catch (Exception e) {
			e.printStackTrace();
			new Sistema.Logger("Ocorreu um erro ao enviar um pacote");
		}
	}

	
}
