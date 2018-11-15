package me.projeto.server;

import me.projeto.socket.SocketAPI;

/**
 * Classe raiz e de execução das funções do servidor.
 * 
 * @author UnixCF
 * @since 2018-11-14
 */
public abstract class Server extends SocketAPI {

	/**
	 * Método de preparo para executar os comandos solicitado pelo cliente
	 * 
	 * @param comando   é o nome do comando que o cliente solicitou
	 * @param variaveis são os sufixos do comando
	 */
	public abstract void Comandos(String comando, String[] variaveis);

}
