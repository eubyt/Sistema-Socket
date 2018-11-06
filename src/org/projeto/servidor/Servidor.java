package org.projeto.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

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
            	  
            	  if (!clientes.containsKey(dados.ip))
                  {
                	  new Logger("Cliente <" + dados.ip  + "> está conectando!", tipo);
                	  new Logger("Solicitando informações para o cliente <" + dados.ip + ">", tipo);
                	  
                	  dados.Enviar("Por favor, nos informe como devemos chamar este cliente:", tipo);
                	  clientes.put(dados.ip, dados);
            	  }
            	
            	  
            	  Console(dados);
         
            	  
            }
            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	private void Console(ClienteDados dados) {
		try {
			ClienteDados cliente = clientes.get(dados.ip);
			String mensagem = dados.ler.readUTF();
			
			if (cliente.nome == null) {
				cliente.nome = mensagem;
				dados.Enviar("Seja Bem-vindo, " + cliente.nome, tipo);
				
				  Iterator notificar = getClientes();
				    
				  
	        	 while (notificar.hasNext()){
	        		 
	        		 Entry<String,ClienteDados> x = (Entry<String, ClienteDados>) notificar.next();
	        		 
	        		if (x.getValue().ip!= cliente.ip)
            		   x.getValue().Enviar("O cliente <" +cliente.nome+ "> se conectou..", tipo);
            	}
			} else {
				String[] arquivo = mensagem.split(":");
				new Logger("Cliente<" + cliente.nome + "> solicitou a procura do arquivo " + arquivo[1], tipo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Iterator getClientes() {
		  Set<Entry<String,ClienteDados>> set = clientes.entrySet();
		  return set.iterator();
	}

	
	



}
