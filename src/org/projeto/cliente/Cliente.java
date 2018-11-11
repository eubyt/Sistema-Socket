package org.projeto.cliente;

import java.io.InputStream;
import java.io.PrintStream;
import java.net.*;
import java.util.Random;
import java.util.Scanner;

import org.projeto.inicio;
import org.projeto.cliente.servidor.ClienteServer;

public class Cliente {
	
	private Socket conectar_servidor;
	
	private boolean ouvir_entrada = false;
	private String nome_arquivo;
	
	private int porta_servidor;
	private String ip = "127.0.0.1";
	
	public Cliente() {
		try {
			
		   this.conectar_servidor = new Socket("127.0.0.1", 12345);
		   this.porta_servidor = new Random().nextInt(1234); //Criar uma porta aleatoria
		   
		   NotificarServidor();
		   
		   this.ouvir_entrada = true;
		   
		   Ouvir();
		   Scanner s = new Scanner(System.in);
		   new inicio.Logger("Digite aqui nome do arquivo: ");
		   nome_arquivo = s.nextLine();
		   
		   EnviarNome();
		   
		   s.close();
		   
		} catch(Exception ex) {
			
		}
	}
	
	
	private void NotificarServidor() throws Exception{
		EnviarParaServidor("CriarCliente/" + this.ip + ":" + this.porta_servidor);
	}
	
	private void CriarServidorCliente() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				new ClienteServer(porta_servidor);
			}
		}).start(); //Criando servidor proprio de nosso cliente...
	}
	
	
	private void EnviarNome() throws Exception {
		EnviarParaServidor(this.nome_arquivo);
	}
	
	
	private void Ouvir() throws Exception {
		while(ouvir_entrada) {
			
			 InputStream input = conectar_servidor.getInputStream(); //Capturar entrada do servidor
			 Scanner scan = new Scanner(input);
			 while (scan.hasNext()) 
			 new inicio.Logger(scan.nextLine());
			 
			   // new Arquivo("recebido-"+nome_arquivo, this.conectar_servidor).Receber();
			    conectar_servidor.close();
			    
		}
	}
	
	
	private void EnviarParaServidor(String msg) throws Exception {
		PrintStream enviar_nome_arquivo = new PrintStream (this.conectar_servidor.getOutputStream()); 
		enviar_nome_arquivo.println(msg);
	}
}
