package org.projeto.cliente.servidor;

import java.net.ServerSocket;

public class ClienteServer {

	private ServerSocket servidor;
	public String ip;
	
	public ClienteServer(int porta) {
		try {
			this.servidor = new ServerSocket(porta);
			this.ip = servidor.getInetAddress().getHostAddress() + ":" + servidor.getLocalPort();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
