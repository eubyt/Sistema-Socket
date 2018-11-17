package com.projeto.server.arquivo;

import java.net.Socket;
import java.util.ArrayList;

import com.projeto.server.DataCliente;

public class ArquivoData {

	String _nome;
	public DataCliente _cliente;
	public ArrayList<Socket> clientes = new ArrayList<Socket>();

	public ArquivoData(DataCliente cliente) {
		_nome = cliente.Arquivo;
		_cliente = cliente;
	}

}
