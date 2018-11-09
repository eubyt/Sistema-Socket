package org.projeto.servidor;

import java.net.ServerSocket;

import java.util.Scanner;

import org.projeto.threads.estrutura.EstruturaThreads;

public class Servidor implements EstruturaThreads{
	
	public ServerSocket servidor = null;
	
	public Servidor() {
		try {
			this.servidor = new ServerSocket(123); //abrir servidor socket
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public Runnable Receber() {
		return new Runnable() {

			@Override
			public void run() {
				while(true) {
					try {
						
						 Scanner socket = new Scanner(servidor.accept().getInputStream()); //Aceitar conexão e capturar o valor de entrada
						 
						 while(socket.hasNextLine()){
							   System.out.println(socket.nextLine()); //Imprimir o resultado
						 }
						 
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
			
			
		};
	}

	@Override
	public Runnable Enviar() {
		return null;
	}

}
