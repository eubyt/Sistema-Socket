package org.projeto.servidor;

import java.io.*;
import java.net.Socket;

import org.projeto.Sistema;
import org.projeto.enviar.Enviar;
import org.projeto.enviar.EnviarUtil;

public class PreparandoServidor {

	
	public static void Comandos(String mensagem, Socket socket) {
		if (mensagem.contains(":")) {
			String[] comando = mensagem.split(":");

			if (comando[0].equals("Arquivo")) {
				String nome_arquivo = comando[1];
				new Sistema.Logger("[Arquivo] Consultando o arquivo " + nome_arquivo);
				ProcurarArquivo(nome_arquivo, socket);
			}

		}
	}

		private static void ProcurarArquivo(String arquivo, Socket socket) {
			File consultar = new File("servidor/" + arquivo);
			if (!consultar.exists())
				EnviarUtil.Adicionar(new Enviar("O arquivo " + arquivo + " nao existe...", socket));
				else  {
				EnviarUtil.Adicionar(new Enviar("O arquivo " + arquivo + " foi localizado, iniciando download..", socket, false));
				try {
					Thread.sleep(550);
					EnviarArquivo(arquivo, socket);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
	}


	private static void EnviarArquivo(String arquivo, Socket socket) {
		try {
			FileInputStream file = new FileInputStream("servidor/" + arquivo);
			System.out.println("Enviando arquivo...");
			byte[] buf = new byte[4096];

			while(true){
				int len = file.read(buf);
				if(len == -1) break;
				EnviarUtil.Adicionar(new Enviar("" + len, socket, false));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
