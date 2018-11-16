package com.projeto.server;

public class DataCliente {

	public int porta;
	public String ip, endereco;

	public DataCliente(String endereco) {
		String[] enderecoFormatado = endereco.split(":");

		porta = Integer.parseInt(enderecoFormatado[1]);
		ip = enderecoFormatado[0];
		this.endereco = endereco;
	}

}
