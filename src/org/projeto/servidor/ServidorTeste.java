package org.projeto.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import org.projeto.cliente.util.ClienteDados;
import org.projeto.importante.util.ComunicarUtil;

public class ServidorTeste extends ComunicarUtil implements Runnable {

	private ServerSocket servidor = null;
	private Socket socket = null;
	
	
	public ServidorTeste(Socket cliente) {
		this.socket = cliente;
	}

	@Override
	public void Preparando(String ip, int porta) {
		try {
			servidor = new ServerSocket(porta);
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void Iniciar() {
		System.out.println("Aguardando conexão do cliente...");   
		while (true) {
			try {
				Socket cliente = servidor.accept();
				ClienteDados c = new ClienteDados(cliente);
				Thread t = new Theread()
				
			} catch (Exception e) {
			
			}
		}
	}

	@Override
	public void run() {
		System.out.println("Nova conexao com o cliente " + this.socket.getInetAddress().getHostAddress());
		 Socket clientSocket = null;
		 try {
			clientSocket = servidor.accept();
			
			 Scanner s = null;
	          s = new Scanner(this.socket.getInputStream());
	          
	          while(s.hasNextLine()){
	                System.out.println(s.nextLine());
	            }
	          
	          s.close();
	          this.socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
