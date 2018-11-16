package com.projeto.cliente.sytem;

import java.net.Socket;
import java.util.Scanner;

import com.projeto.cliente.Cliente;

public class SyClienteOuvir implements Runnable {

	private Socket servidor;
	private Cliente cliente;

	SyClienteOuvir(Cliente cliente, Socket servidor) {
		this.servidor = servidor;
		this.cliente = cliente;
	}

	@Override
	public void run() {

		do {
			Scanner entrada;
			try {
				entrada = new Scanner(servidor.getInputStream());
				do {
					String texto = entrada.nextLine();
					
					if (texto.contains("/")) {
						String[] variaveis = texto.split("/");
						String comando = variaveis[0];
						this.cliente.Comandos(comando, variaveis);
						
						
					} else
						System.out.println(texto);
					
				} while (entrada.hasNextLine());

			} catch (Exception e) {
				e.printStackTrace();
			}

		} while (true);

	}

}
