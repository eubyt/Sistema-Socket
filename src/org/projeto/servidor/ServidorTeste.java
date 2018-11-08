package org.projeto.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import org.projeto.cliente.util.ClienteDados;
import org.projeto.importante.util.ComunicarUtil;

public class ServidorTeste extends ComunicarUtil {

	private ServerSocket servidor = null;
	
	public HashMap<String, ClienteDados> conexoes = new HashMap<String, ClienteDados>();
	
	
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
				ClienteDados c = new ClienteDados(servidor);
				if (conexoes.containsKey(c.ip)) {
					c.Fechar();
					conexoes.get(c.ip).Reabrir(servidor);
					c = conexoes.get(c.ip);
				} else 
					conexoes.put(c.ip, c);
				
				
				
				Thread t = new Thread(c.tratar());
				t.start();
			} catch (Exception e) {
			
			}
		}
	}

}
