package com.projeto;

import com.projeto.cliente.Cliente;
import com.projeto.cliente.sytem.SyCliente;
import com.projeto.erro.CustomErro;
import com.projeto.server.Server;
import com.projeto.server.system.SyServer;
import com.projeto.socket.InterSocket;

/**
 * Classe de utilitarios para a Main
 */


public abstract class Util {

	/**
	 * Retornar para a Main quando solicitar criação da Class Cliente
	 * 
	 * @return Class SyCliente
	 */
	static Cliente getCliente() {
		return new SyCliente();
	}

	/**
	 * Retornar para a Main quando solicitar a criação da Class Servidor
	 * 
	 * @return SyServer
	 */
	static Server getServer() {
		return new SyServer();
	}

	static void CriarSocket(InterSocket socket, String ip, int porta) throws CustomErro {
		socket.PrepararSocket(ip, porta);
		socket.CarregarSocket();
		socket.OuvirSocket();
	}
}
