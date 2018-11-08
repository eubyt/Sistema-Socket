package org.projeto.cliente.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import org.projeto.Sistema;
import org.projeto.importante.logger.Logger;

public class ClienteDados {
	
	 public DataOutputStream enviar;
	 public String ip;
	 
	 public String nome = null;
	 public Socket socket;
	 
	public ClienteDados(Socket socket) {
		try {
			ip = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
			enviar = new DataOutputStream(socket.getOutputStream());
			this.socket = socket;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void Receber(Sistema.Tipo tipo) {


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
					socket.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	}
	
	public void Enviar(String mensagem, Sistema.Tipo tipo) {
		
		try {
			enviar.writeUTF(mensagem);
			enviar.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			new Logger("<Local -> " +ip+ "> Mensagem (" +mensagem+ ") enviada com sucesso. ", tipo);
		}
	
	}
	


}
