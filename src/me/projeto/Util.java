package me.projeto;

/**
 * Classe de utilitarios para a Main
 */
import me.projeto.cliente.Cliente;
import me.projeto.cliente.sytem.SyCliente;
import me.projeto.erro.CustomErro;
import me.projeto.server.Server;
import me.projeto.server.system.SyServer;
import me.projeto.socket.InterSocket;

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

	static void CriarSocket(InterSocket socket) throws CustomErro {
		socket.PrepararSocket("127.0.0.1", 12344);
		socket.CarregarSocket();
		socket.OuvirSocket();
	}
}
