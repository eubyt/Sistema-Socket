package org.projeto.servidor;

import java.io.IOException;
import java.net.ServerSocket;

import org.projeto.Sistema;
import org.projeto.cliente.util.ClienteDados;
import org.projeto.importante.logger.Logger;
import org.projeto.importante.util.ComunicarUtil;

public class Servidor extends ComunicarUtil {

	
	private Sistema.Tipo tipo = Sistema.Tipo.SERVER;
	private ServerSocket servidor;
	
	
	@Override
	public void Preparando(String ip, int porta) {
		try {
			new Logger("Servidor iniciado em " + ip +":"+ porta, tipo);
			servidor = new ServerSocket(porta);
			new Logger("Servidor est� aguardando as conex�es..", tipo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void Iniciar() {
		try {
		
            while (true) {
            	  ClienteDados dados = new ClienteDados(servidor.accept());
            	  
            	  new Logger("Cliente <" + dados.ip  + "> est� conectando!", tipo);
            	  new Logger("Solicitando informa��es para o cliente <" + dados.ip + ">", tipo);
            	  
            	  dados.Enviar("Por favor, nos informe como devemos chamar este cliente:", tipo);
            	  
            }
            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}





}
