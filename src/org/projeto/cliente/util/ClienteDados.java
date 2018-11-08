package org.projeto.cliente.util;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import org.projeto.Sistema;
import org.projeto.importante.logger.Logger;

public class ClienteDados {
	
	 public String ip;
	 
	 public String nome = null;
	 public Socket socket;
	 
	public ClienteDados(ServerSocket server) {
		try {
			this.socket = server.accept();
			Formatar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ClienteDados(Socket server) {
		try {
			this.socket = server;
			Formatar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void Formatar() {
		ip = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
	}
	
	public void Fechar() {
		try {
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void Reabrir(Socket server) {
		try {
			this.socket = server;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Reabrir(ServerSocket server) {
		try {
			this.socket = server.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Runnable tratar() {
		return new Runnable() {
			@Override
			public void run() {
				System.out.println("Nova conexao com o cliente " + socket.getInetAddress().getHostAddress());
				
				try {
					Scanner scan = new Scanner(socket.getInputStream());
					
					while (scan.hasNext()) {
						System.out.println("Mensagem: " + scan.nextLine());
					}
					
					scan.close();
					Fechar();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	}
	
	
	public Runnable Enviar(final String mensagem) {
		return new Runnable() {

			@Override
			public void run() {
				try {
					PrintStream msg = new PrintStream(socket.getOutputStream());
					msg.println(mensagem);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		};
		
		
		
	}

	


}
