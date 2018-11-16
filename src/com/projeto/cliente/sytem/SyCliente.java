package com.projeto.cliente.sytem;

import java.io.File;

import com.projeto.Main;
import com.projeto.cliente.Cliente;
import com.projeto.diretorio.Diretorio;

public class SyCliente extends Cliente {

	private String nome_arquivo;
	private SyServerCliente servidor_cliente;

	private boolean Lista = false;

	@Override
	public void PrepararSocket(String endereco, int porta) {
		this.Porta = porta;
		this.ip = endereco;
		this.servidor_cliente = new SyServerCliente();

	}

	@Override
	public void CarregarSocket() {
		Conectar();
		ClienteInfo();
	}

	@Override
	public void OuvirSocket() {
		new Thread(new SyClienteOuvir(this, servidor)).start();
		PrepararCliente();
	}

	@Override
	public void EnviarArquivo() {

	}

	@Override
	public void PrepararCliente() {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Digite o nome do arquivo para download:");
		nome_arquivo = Main.console.nextLine();

		System.out.println("Arquivo solicitado: " + nome_arquivo);
		this.EnviarMensagem("ProcurarArquivo/" + nome_arquivo);
		Lista = true;
	}

	@Override
	public void Comandos(String comando, String[] variaveis) {
		if(comando.equals("Consultar")) 
			BuscarArquivo(variaveis[0]);
		
		
	}

	@Override
	public void BuscarArquivo(String nome_arquivo) {

		System.out.println("Buscando arquivo " + nome_arquivo);
		File arquivo_download = new File(Diretorio.ListaDiretorios.ARQUIVOS_DOWNLOAD.nome + "/" + nome_arquivo);
		if (arquivo_download.exists()) {
			System.out.println("Arquivo " + nome_arquivo + " existe");
			EnviarMensagem("Localizado/" + nome_arquivo);
		} else 
			System.out.println("Arquivo não existe: " + nome_arquivo);
		
	}

}