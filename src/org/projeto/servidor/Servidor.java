package org.projeto.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

import org.projeto.Sistema;
import org.projeto.cliente.util.ClienteDados;
import org.projeto.importante.logger.Logger;
import org.projeto.importante.util.ComunicarUtil;

public class Servidor extends ComunicarUtil {

	
	private Sistema.Tipo tipo = Sistema.Tipo.SERVER;
	private ServerSocket servidor;
	
	private HashMap<String,ClienteDados> clientes = new HashMap();
	
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
		try {
		
            while (true) {
            	  
            	  ClienteDados dados = new ClienteDados(servidor.accept());
            	  if (clientes.containsKey(dados.ip))
            	  {
            		  Console(clientes.get(dados.ip));
            		  
            	  } else {
                	  new Logger("Cliente <" + dados.ip  + "> está conectando!", tipo);
                	  new Logger("Solicitando informações para o cliente <" + dados.ip + ">", tipo);
                	  
                	  dados.Enviar("Por favor, nos informe como devemos chamar este cliente:", tipo);
                	  clientes.put(dados.ip, dados);
            	  }
            	
         
            	  
            }
            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	private void Console(ClienteDados dados) {
		try {
			
			String mensagem = dados.ler.readUTF();
			
			if (dados.nome == null) {
				dados.nome = mensagem;
				dados.Enviar("Seja Bem-vindo, " + dados.nome, tipo);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	



}
