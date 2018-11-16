package com.projeto.server;

import java.io.File;
import java.net.Socket;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.projeto.diretorio.Diretorio;
import com.projeto.server.arquivo.ArquivoData;
import com.projeto.socket.SocketAPI;

/**
 * Classe raiz e de execução das funções do servidor.
 * 
 * @author UnixCF
 * @since 2018-11-14
 */
public abstract class Server extends SocketAPI {

	protected List<String> arquivos_download;

	private HashMap<Socket, DataCliente> clientes = new HashMap<Socket, DataCliente>();
	private HashMap<String, ArquivoData> arquivos = new HashMap<String, ArquivoData>();

	/**
	 * Método de preparo para executar os comandos solicitado pelo cliente
	 * 
	 * @param comando   é o nome do comando que o cliente solicitou
	 * @param variaveis são os sufixos do comando
	 */
	public abstract void Comandos(Socket socket, String comando, String[] variaveis);

	protected void AdicionarCliente(Socket socket, DataCliente cliente) {
		clientes.put(socket, cliente);
	}

	protected DataCliente getCliente(Socket socket) {
		return clientes.get(socket);
	}

	private HashMap<Socket, DataCliente> getCliente() {
		return clientes;
	}

	protected List<String> getDownload() {

		List<String> lista_arquivos = new ArrayList<String>();

		File arquivos[] = Diretorio.getPasta(Diretorio.ListaDiretorios.ARQUIVOS_SERVIDOR).listFiles();

		if (arquivos.length <= 0)
			return null;

		int loop = 0;
		do {
			File arquivo = arquivos[loop++];
			lista_arquivos.add(MessageFormat.format("{0} - {1}", arquivo.getName(), arquivo.length()));

		} while (loop < arquivos.length);

		return lista_arquivos;
	}

	protected void EnviarListaDownload(Socket socket) {
		String arquivos = String.join(",", arquivos_download);
		EnviarMensagem(arquivos, socket);
	}

	protected void ConsultarArquivo(Socket socket) {
		AddArquivo(socket);

		for (Socket clientes : getCliente().keySet()) {
			if (clientes != socket)
				EnviarMensagem("Consultar/" + getCliente(socket).Arquivo, clientes);
		}
	}

	protected void AddArquivo(Socket cliente) {
		arquivos.put(getCliente(cliente).Arquivo, new ArquivoData(getCliente(cliente)));
	}

}
