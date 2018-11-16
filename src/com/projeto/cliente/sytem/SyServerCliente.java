package com.projeto.cliente.sytem;

import java.util.Random;

import com.projeto.erro.CustomErro;
import com.projeto.socket.InterSocket;

public class SyServerCliente implements InterSocket {

	private int Porta;

	SyServerCliente() {
		this.Porta = new Random().nextInt(1234);
	}

	@Override
	public void PrepararSocket(String endereco, int porta) throws CustomErro {

	}

	@Override
	public void CarregarSocket() {

	}

	@Override
	public void OuvirSocket() {

	}

}
