package org.projeto.servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Scanner;

import org.projeto.Sistema;
import org.projeto.enviar.EnviarUtil;
import org.projeto.threads.estrutura.EstruturaThreads;

public class Servidor implements EstruturaThreads{
	
	public ServerSocket servidor = null;
	
	public Servidor() {
		try {
			this.servidor = new ServerSocket(123); //abrir servidor socket
			new Sistema.Logger("Servidor iniciado...");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	//Este é um Thread somente para receber as mensagens
	@Override
	public Runnable Receber() {
		return new Runnable() {

			@Override
			public void run() {
				while(true) {
					try {						
						 Socket cliente = servidor.accept();
						 new Sistema.Logger("Conexão recebida <"+cliente.getInetAddress().getHostAddress() + ">...");
						 EnviarUtil.Adicionar(new org.projeto.enviar.Enviar("Conectado com sucesso...", cliente, false));
						 Scanner socket = new Scanner(cliente.getInputStream()); //Aceitar conexão e capturar o valor de entrada
						 while(socket.hasNextLine()){
							   String mensagem = socket.nextLine();
							   new Sistema.Logger("[CLIENTE - " + cliente.getInetAddress().getHostAddress() + "] " + mensagem); //Imprimir o resultado
							   PreparandoServidor.Comandos(mensagem, cliente);
						 }
						 
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
			
			
		};
	}

	//Este é um Thread somente para enviar as mensagens
	@Override
	public Runnable Enviar() {
		return new Runnable() {
			@Override
			public void run() {
				while (true) {
				  Iterator<org.projeto.enviar.Enviar> lista = EnviarUtil.enviar().iterator(); //Carregando ArrayList de mensagens
				  while(lista.hasNext()) {
					 lista.next().Executar(); //Enviando elas
				  }
				  EnviarUtil.enviar().clear(); //Limpando Array
				  try {	Thread.sleep(500); } catch (InterruptedException e) { } //Ter delay para executar este Thread
				}
			}
			
		};
	}

}
