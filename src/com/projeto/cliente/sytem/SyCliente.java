package com.projeto.cliente.sytem;

import com.projeto.Main;
import com.projeto.cliente.Cliente;

public class SyCliente extends Cliente {

	private String nome_arquivo;
	private SyServerCliente servidor_cliente;

	@Override
	public void PrepararSocket(String endereco, int porta) {
		this.Porta = porta;
		this.ip = endereco;
		this.servidor_cliente = new SyServerCliente();

	}

	@Override
	public void CarregarSocket() {
		Conectar();
		PrepararCliente();
	}

	@Override
	public void OuvirSocket() {
		do {

		} while (true);
	}

	@Override
	public void BuscarArquivo() {

	}

	@Override
	public void EnviarArquivo() {

	}

	@Override
	public void PrepararCliente() {
		ClienteInfo();

		System.out.println("Digite o nome do arquivo para download:");
		nome_arquivo = Main.console.nextLine();

		System.out.println("Arquivo solicitado: " + nome_arquivo);
	}

}