package org.projeto.servidor;

import java.net.Socket;

import org.projeto.Sistema;

public class PreparandoServidor {

	
	public static void Comandos(String mensagem, Socket socket) {
		if (mensagem.contains(":")) {
			String[] comando = mensagem.split(":");
			
			if (comando[0].equals("Arquivo")) {
				String nome_arquivo = comando[1];
				new Sistema.Logger("[Arquivo] Consultando o arquivo " + nome_arquivo);
			}
			
		}
	}
}
