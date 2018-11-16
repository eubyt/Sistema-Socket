package com.projeto.socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class SocketAPI implements InterSocket {

	protected ServerSocket server_socket;

	protected Socket servidor;

	protected int Porta;
	protected String ip;
	
	protected void EnviarMensagem(String mensagem) {
		EnviarMensagem(mensagem, servidor);
	}
	

	protected void EnviarMensagem(String mensagem, Socket socket) {
		try {
		PrintStream enviar = new PrintStream(socket.getOutputStream());
		enviar.println(mensagem);
		enviar.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void CriarConexao() {
		try {
			server_socket = new ServerSocket(this.Porta);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void Conectar() {
		try {
			servidor = new Socket(ip, Porta);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected Socket getAceitarConexao() {
		try {
			return this.server_socket.accept();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
