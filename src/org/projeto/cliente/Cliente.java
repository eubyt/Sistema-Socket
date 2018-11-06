package org.projeto.cliente;

import java.net.Socket;
import java.util.Scanner;

import org.projeto.Sistema;
import org.projeto.cliente.util.ClienteDados;
import org.projeto.importante.logger.Logger;
import org.projeto.importante.util.ComunicarUtil;

public class Cliente extends ComunicarUtil {

	Socket cliente;
    Scanner teclado = new Scanner(System.in);

    ClienteDados dados;
	private Sistema.Tipo tipo = Sistema.Tipo.CLIENTE;
	
	@Override
	public void Preparando(String ip,int porta) {
		try {
			new Logger("Criando servidor cliente..", tipo);
			cliente = new Socket(ip, porta);
			dados = new ClienteDados(cliente);
			new Logger("Servidor cliente criado com sucesso, conectado ao servidor principal <" +ip + ":" + porta +">", tipo);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
    
	@Override
	public void Iniciar() {
	
		try {
			
			while (true) {
				
				dados.Receber(tipo);
				//Definir Nome
				
				if (dados.nome == null) {
					String valor = console().nextLine();
					dados.Enviar(valor, tipo);
					dados.nome = valor;
					return;
				}
				
				
			}
		        
		       
		} catch (Exception e) {
			e.printStackTrace();
		} 
	 
	}


	private Scanner console() {
		return new Scanner(System.in);
	}



}
