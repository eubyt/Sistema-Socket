package org.projeto.servidor;

import java.io.IOException;
import java.net.ServerSocket;

import java.util.HashMap;


import org.projeto.Sistema;
import org.projeto.cliente.util.ClienteDados;
import org.projeto.importante.logger.Logger;
import org.projeto.importante.util.ComunicarUtil;

public class ServidorTeste extends ComunicarUtil {

	private ServerSocket servidor = null;
	
	private Sistema.Tipo tipo = Sistema.Tipo.SERVER;
	
	public HashMap<String, ClienteDados> conexoes = new HashMap<String, ClienteDados>();
	
	
	@Override
	public void Preparando(String ip, int porta) {
		try {
			
			new Logger("Servidor iniciado em " + ip +":"+ porta, tipo);
			servidor = new ServerSocket(porta);
			new Logger("Servidor está aguardando as conexões..", tipo);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void Iniciar() {

		while (true) {
			
			try {
				
				ClienteDados c = new ClienteDados(servidor, tipo);
				
				new Logger("Cliente <" + c.ip  + "> está conectando!", tipo);
				
				if (conexoes.containsKey(c.ip)) {
					c.Fechar();
					conexoes.get(c.ip).Reabrir(servidor);
					c = conexoes.get(c.ip);
				} else {
	            	new Logger("Solicitando informações para o cliente <" + c.ip + ">", tipo);
					conexoes.put(c.ip, c);
					
					new Thread(c.Enviar("Por favor, nos informe como devemos chamar este cliente:")).start();
				}
				
			c.tratar();
				
			} catch (Exception e) {
			
			}
		}
	}

}
