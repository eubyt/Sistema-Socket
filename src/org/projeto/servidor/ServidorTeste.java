package org.projeto.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.projeto.cliente.util.ClienteDados;
import org.projeto.importante.util.ComunicarUtil;

public class ServidorTeste extends ComunicarUtil {

	private ServerSocket servidor = null;

	
	
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
				Thread t = new Thread(c.tratar());
				t.start();
			} catch (Exception e) {
			
			}
		}
	}

}
