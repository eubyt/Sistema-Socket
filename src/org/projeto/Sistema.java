package org.projeto;

import java.util.Scanner;

import org.projeto.cliente.Cliente;
import org.projeto.servidor.Servidor;
import org.projeto.threads.ThreadsProjeto;
import org.projeto.threads.estrutura.EstruturaThreads;


public class Sistema {
	
	private static String versao = "0.8.5";
	
	public static void main(String[] args) {
		
		 //Capturar a opção digitada no console
		 Scanner s = new Scanner(System.in);
		 
		 //Mensagens de inicio
		 new Logger("Seja Bem-vindo, este é um simulador de Servidor-Cliente [Versão " + versao + "]");
		 new Logger("Deseja iniciar este projeto como um servidor? [S/N]");
		 new Logger("[S] - Projeto vai ser iniciado como um servidor.");
		 new Logger("[N] - Projeto vai ser iniciado como um cliente");
		 
		 //Capturar a escolha, 
		 ThreadsProjeto<EstruturaThreads> carregar = load(s.nextLine());
	
		 //Iniciar o objeto
		 carregar.iniciar();
		 
		 //Fechar o scanner
		 s.close();
		 
	 
	 }
	
	
	private static ThreadsProjeto<EstruturaThreads> load(String escolha) {
		if (escolha.equals("s"))
		    return new ThreadsProjeto<EstruturaThreads>(new Servidor());
		else
			return new ThreadsProjeto<EstruturaThreads>(new Cliente()); //Retornar a class do processo de Cliente
	}
	
	
	public static class Logger {
		
		public Logger(String mensagem) {
			//Imprimir no Console as msg
			System.out.println("[PROJETO] " + mensagem);
		}
		
	}

	 
	 public enum Tipo {
		 SERVER,
		 CLIENTE;
	 }
}
