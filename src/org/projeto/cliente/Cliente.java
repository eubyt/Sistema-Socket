package org.projeto.cliente;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Scanner;

import org.projeto.arquivo.EnviarArquivo;
import org.projeto.enviar.EnviarUtil;
import org.projeto.threads.estrutura.EstruturaThreads;

public class Cliente implements EstruturaThreads{

	
	public Socket servidor = null;
	
	public Cliente() {
		
		try {
			servidor = new Socket("127.0.0.1", 123);
			EnviarUtil.Adicionar(new org.projeto.enviar.Enviar("Testando conexão..", servidor, false));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
						 	     InputStream input = servidor.getInputStream();
								 PreparandoCliente.Preparar(input, servidor);	
									
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

				@Override
				public void run() {
					while (true) {
					Iterator<org.projeto.enviar.Enviar> lista = EnviarUtil.enviar().iterator(); //Carregando ArrayList de mensagens
	
					while(lista.hasNext()) {
						lista.next().Executar(); //Enviando elas
					}
					
					EnviarUtil.enviar().clear(); //Limpando Array
					  try {	Thread.sleep(200); } catch (InterruptedException e) { } //Ter delay para executar este Thread
					}
				}
				
			};
		}
}
