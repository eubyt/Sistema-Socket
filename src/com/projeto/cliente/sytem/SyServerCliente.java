package com.projeto.cliente.sytem;

import java.io.IOException;
import java.net.ServerSocket;

import com.projeto.cliente.arquivo.SyClienteArquivo;
import com.projeto.erro.CustomErro;
import com.projeto.socket.InterSocket;

public class SyServerCliente implements InterSocket {

	private ServerSocket sv;

	@Override
	public void PrepararSocket(String endereco, int porta) throws CustomErro {
		try {
			sv = new ServerSocket(porta);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void CarregarSocket() {
		while (true) {
			try {
				SyClienteArquivo.Receber(sv.accept(), SyCliente.nome_arquivo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void OuvirSocket() {

	}

}
