package com.projeto.server;

import java.net.Socket;

import com.projeto.server.system.SyServer;

public class DataCliente {

	public int porta;
	public String ip, endereco, Arquivo;

	public DataCliente(SyServer sy, Socket socket, String endereco) {
		String[] enderecoFormatado = endereco.split(":");

		porta = Integer.parseInt(enderecoFormatado[1]);
		ip = enderecoFormatado[0];
		this.endereco = endereco;

		sy.EnviarListaDownload(socket);
	}

}
