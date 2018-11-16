package com.projeto.server;

import java.net.Socket;
import java.util.HashMap;

import com.projeto.socket.SocketAPI;

/**
 * Classe raiz e de execu��o das fun��es do servidor.
 * 
 * @author UnixCF
 * @since 2018-11-14
 */
public abstract class Server extends SocketAPI {

	private HashMap<Socket, DataCliente> clientes = new HashMap<Socket, DataCliente>();

	/**
	 * M�todo de preparo para executar os comandos solicitado pelo cliente
	 * 
	 * @param comando   � o nome do comando que o cliente solicitou
	 * @param variaveis s�o os sufixos do comando
	 */
	public abstract void Comandos(Socket socket, String comando, String[] variaveis);

	protected void AdicionarCliente(Socket socket, DataCliente cliente) {
		clientes.put(socket, cliente);
	}

	protected DataCliente getCliente(Socket socket) {
		return clientes.get(socket);
	}
}
