package org.projeto.cliente;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Scanner;

import org.projeto.Sistema;
import org.projeto.enviar.EnviarUtil;
import org.projeto.threads.estrutura.EstruturaThreads;

public class Cliente implements EstruturaThreads{

	
	public Socket servidor = null;
	
	public Cliente() {
		
		try {
			servidor = new Socket("127.0.0.1", 123);
			EnviarUtil.Adicionar(new org.projeto.enviar.Enviar("Testando conexão..", servidor, false));

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//Este é um Thread somente para receber as mensagens
		@Override
		public Runnable Receber() {
			return new Runnable() {

				@Override
				public void run() {
					while(true) {
						try {						
							 Scanner socket = new Scanner(servidor.getInputStream()); //Aceitar conexão e capturar o valor de entrada
							 while(socket.hasNextLine()){
								   new Sistema.Logger("[SERVIDOR] " + socket.nextLine()); //Imprimir o resultado
							 }
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
				}
				
				
			};
		}

		//Este é um Thread somente para enviar as mensagens
		@Override
		public Runnable Enviar() {
			return new Runnable() {

				int contar = 0;
				@Override
				public void run() {
					
					Iterator<org.projeto.enviar.Enviar> lista = EnviarUtil.enviar().iterator(); //Carregando ArrayList de mensagens
	
					while(lista.hasNext()) {
						lista.next().Executar(); //Enviando elas
					}
					
					EnviarUtil.enviar().clear(); //Limpando Array
					
				}
				
			};
		}
}
