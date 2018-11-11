package org.projeto;

import java.io.File;
import java.util.Scanner;

import org.projeto.cliente.Cliente;
import org.projeto.servidor.Servidor;

public class inicio {

	public static void main(String[] inicio) {
		Scanner s = new Scanner(System.in);
		new Logger("Deseja iniciar este projeto como um servidor? [S/N]");
		new Logger("[S] - Projeto vai ser iniciado como um servidor.");
		new Logger("[N] - Projeto vai ser iniciado como um cliente");
		
		new File("diretorio").mkdirs();
		
		if (s.nextLine().equals("s")) {
			new Servidor();
		} else {
			new Cliente();
		}
	}
	
	
public static class Logger {
		
		public Logger(String mensagem) {
			//Imprimir no Console as msg
			System.out.println("[PROJETO] " + mensagem);
		}
		
}

}
