package org.projeto.servidor;

import java.io.*;
import java.net.Socket;
import java.util.Base64;
import java.util.Scanner;

import org.projeto.Sistema;
import org.projeto.arquivo.EnviarArquivo;
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
					Thread.sleep(1550);
					new EnviarArquivo(socket).Enviar(arquivo);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
	}


	/*
	private static ByteArrayOutputStream byts = new ByteArrayOutputStream();

	private static void EnviarArquivo(String arquivo, Socket socket) {
		try {
			Scanner in = new Scanner(new FileReader("servidor/" + arquivo));
			OutputStream file = new FileOutputStream("clientes/" + arquivo);

			while (in.hasNextLine()) {
				String texto = in.nextLine();
				byte[] bytes = texto.getBytes();
				byts.write(bytes);
				int tamanho = bytes.length-1;
				byte[] base64 = Base64.getEncoder().encode(bytes);

				System.out.println(new String(base64));

				EnviarUtil.Adicionar(new Enviar("" +new String(base64), socket, false));
				Thread.sleep(149);

			}

			file.write(byts.toByteArray());

			in.close();


		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
*/
}
