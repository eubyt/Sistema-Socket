package org.projeto.cliente.util;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import org.projeto.Sistema;
import org.projeto.importante.logger.Logger;
import org.projeto.servidor.Operador;

public class ClienteDados {
	
	 public String ip;
	 
	 public String nome, arquivo = null;
	 
	 
	 public Socket socket;
	 
	 public Sistema.Tipo tipo;
	 
	public ClienteDados(ServerSocket server, Sistema.Tipo tipo) {
		try {
			this.socket = server.accept();
			Formatar(tipo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ClienteDados(Socket server, Sistema.Tipo tipo) {
		try {
			this.socket = server;
			Formatar(tipo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void Formatar(Sistema.Tipo tipo) {
		ip = socket.getInetAddress().getHostAddress();
		this.tipo = tipo;
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
				
				try {
					Scanner scan = new Scanner(socket.getInputStream());
					
					while (scan.hasNext()) {		
						if (Sistema.Tipo.SERVER == tipo) {
							Operador(scan.nextLine().toLowerCase());
						}
						else {
							new Logger(scan.nextLine(), Sistema.Tipo.SERVER);	
						}
					}
					
					scan.close();
					Fechar();
					this.finalize();
					
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		};
	}
	
	private void Operador(String msg) {
    new Operador(msg, this);
}
	

	public Runnable Enviar(final String mensagem) {
		return new Runnable() {

			@Override
			public void run() {
				try {
					PrintStream msg = new PrintStream(socket.getOutputStream());
					msg.println(mensagem);
					new Logger("Mensagem enviada para <" +mensagem+">", tipo);
					
					this.finalize();
				
				} catch (Throwable e) {
					e.printStackTrace();
				}
				
			}
			
		};
		
		
		
	}

	


}
