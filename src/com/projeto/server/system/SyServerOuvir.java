package com.projeto.server.system;

import java.net.Socket;
import java.util.Scanner;

import com.projeto.server.Server;

public class SyServerOuvir implements Runnable {

	private Socket cliente;
	private Server servidor;

	private String separador = "/";

	SyServerOuvir(Socket cliente, Server servidor) {
		this.cliente = cliente;
		this.servidor = servidor;
	}

	@Override
	public void run() {
		Scanner entrada;
		try {
			entrada = new Scanner(cliente.getInputStream());
			do {
				String texto = entrada.nextLine();
				CriarComandos(texto);
			} while (entrada.hasNextLine());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	void CriarComandos(String texto) {
		String[] variaveis = texto.split(separador);
		String comando = variaveis[0];
		variaveis = texto.replace(variaveis[0] + "/", "").split("/");
		servidor.Comandos(cliente, comando, variaveis);
	}

}
