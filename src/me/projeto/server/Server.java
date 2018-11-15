package me.projeto.server;

import me.projeto.socket.SocketAPI;

/**
 * Classe raiz e de execu��o das fun��es do servidor.
 * 
 * @author UnixCF
 * @since 2018-11-14
 */
public abstract class Server extends SocketAPI {

	/**
	 * M�todo de preparo para executar os comandos solicitado pelo cliente
	 * 
	 * @param comando   � o nome do comando que o cliente solicitou
	 * @param variaveis s�o os sufixos do comando
	 */
	public abstract void Comandos(String comando, String[] variaveis);

}
