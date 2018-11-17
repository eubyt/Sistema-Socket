package com.projeto.cliente.sytem;

import java.io.File;
import java.io.IOException;

import com.projeto.Main;
import com.projeto.cliente.Cliente;
import com.projeto.cliente.arquivo.SyClienteArquivo;
import com.projeto.diretorio.Diretorio;
import com.projeto.erro.CustomErro;
import com.projeto.server.arquivo.SyArquivo;

public class SyCliente extends Cliente {

	private SyServerCliente servidor_cliente = new SyServerCliente();

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
		if (comando.equals("Consultar"))
			BuscarArquivo(variaveis[0]);
		if (comando.equals("Baixar")) {

			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						servidor_cliente.PrepararSocket("", Porta_Privada);
						servidor_cliente.CarregarSocket();
					} catch (CustomErro e) {
						e.printStackTrace();
					}

				}

			}).start();
		}
		if (comando.equals("Enviar")) {
			File arquivo = new File(Diretorio.ListaDiretorios.ARQUIVOS_DOWNLOAD.nome +"/" + variaveis[0]);
			String[] ip = variaveis[1].split(":");
			int parte = Integer.parseInt(variaveis[2]);
			int partes = Integer.parseInt(variaveis[3]);

			SyClienteArquivo.Quebrar(arquivo, partes, parte); // 0,1,2 extensão .temp
			try {
				SyArquivo.Enviar(ip[0], Integer.parseInt(ip[1]), new File((parte-1) + ".temp"));
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
			
		}
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