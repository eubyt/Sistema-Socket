package com.projeto.cliente;

import java.net.Socket;

import com.projeto.socket.SocketAPI;

/**
 * Classe raiz e de execução das funções do cliente.
 * 
 * @author UnixCF
 * @since 2018-11-14
 */
public abstract class Cliente extends SocketAPI {

	protected boolean BaixarServidor = false;
	
	public abstract void PrepararCliente();
	
	public abstract void BuscarArquivo(String nome_arquivo);

	public abstract void EnviarArquivo();
	
	public abstract void Comandos(String comando, String[] variaveis);
	
	protected void ClienteInfo() {
		EnviarMensagem("CriarCliente/" + this.ip + ":" + this.Porta);
	}
	
	
	
}
