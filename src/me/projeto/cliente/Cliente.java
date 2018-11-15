package me.projeto.cliente;

import me.projeto.socket.SocketAPI;

/**
 * Classe raiz e de execução das funções do cliente.
 * 
 * @author UnixCF
 * @since 2018-11-14
 */
public abstract class Cliente extends SocketAPI {

	public abstract void PrepararCliente();
	
	public abstract void BuscarArquivo();

	public abstract void EnviarArquivo();
	
	protected void ClienteInfo() {
		EnviarMensagem("CriarCliente/" + this.ip + ":" + this.Porta);
	}
	
}
