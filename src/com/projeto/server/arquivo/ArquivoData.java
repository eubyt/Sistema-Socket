package com.projeto.server.arquivo;

import com.projeto.server.DataCliente;

public class ArquivoData {

	String _nome;
	public DataCliente _cliente;
	
	public ArquivoData(DataCliente cliente) {
		_nome = cliente.Arquivo;
		_cliente = cliente;
	}
	
}
