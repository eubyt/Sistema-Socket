package com.projeto.server.system;

import java.net.Socket;

import com.projeto.erro.CustomErro;
import com.projeto.logger.UsoLogger;
import com.projeto.server.DataCliente;
import com.projeto.server.Server;

public class SyServer extends Server {
	
	@Override
	public void PrepararSocket(String endereco, int porta) throws CustomErro {

		if ((porta > 12345) || (porta == 0) || (porta < 0))
			throw new CustomErro("Porta invalida ou maior que 12345. Porta: " + porta);

		this.Porta = porta;
		this.arquivos_download = getDownload();
	}

	@Override
	public void CarregarSocket() {
		CriarConexao();		
	}

	@Override
	public void OuvirSocket() {
		do {
			Socket conexao = getAceitarConexao(); // ouvir a conexao
			System.out.println("Nova conexão: " + conexao);
			new Thread(new SyServerOuvir(conexao, this)).start();

		} while (true);
	}

	@Override
	public void Comandos(Socket socket, String comando, String[] variaveis) {
		
		if (comando.equals("CriarCliente")) {
			
			AdicionarCliente(socket, new DataCliente(this, socket, variaveis[0]));
			new UsoLogger("Cliente adicionado {0}", getCliente(socket).endereco).println();;
			
		}
		
		if (comando.equals("ProcurarArquivo")) {
			getCliente(socket).Arquivo = variaveis[0];
			ConsultarArquivo(socket);
		}
		
	}
	

}
