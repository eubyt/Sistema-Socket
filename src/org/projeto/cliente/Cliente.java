package org.projeto.cliente;

import java.io.File;
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
			
		   new File("baixados").mkdirs();
		   
		   this.conectar_servidor = new Socket("127.0.0.1", 12345);
		   this.porta_servidor = new Random().nextInt(1234); //Criar uma porta aleatoria
		   
		   NotificarServidor();
		   
		   this.ouvir_entrada = true;
		   
		   Ouvir();
		   
		   Scanner s = new Scanner(System.in);
		   new inicio.Logger("Digite aqui nome do arquivo: ");
		   nome_arquivo = s.nextLine();
		   
		   EnviarNome();
		   
		} catch(Exception ex) {
			ex.printStackTrace();
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
		EnviarParaServidor("Baixar/" +this.nome_arquivo);
		}
	
	
	private void Ouvir()  {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
				 InputStream input = conectar_servidor.getInputStream(); //Capturar entrada do servidor
				 Scanner entrada = new Scanner(input);
				 
				 while (entrada.hasNext()) {
					 String mensagem = entrada.nextLine();
					 
					 if (mensagem.contains("Buscar/")) {
						BuscarArquivo(mensagem);
					 } else {
					 String[] linhas = mensagem.split("/n");
					 int loop = 0;;
					 do {
						 new inicio.Logger(linhas[loop++]);
					 }while(loop < linhas.length);
					 }
				 }
				} catch (Exception ex) {
					
				}
			}
		}).start();
		
	}
	
	
	private void BuscarArquivo(String texto) {
		String[] a = texto.split("/");
		new inicio.Logger("Servidor solicitou a busca do arquivo: " + a[1]);
		if (new File("baixados").exists()) {
			new inicio.Logger("Arquivo " + a[1] + " existe...");
		} else
			new inicio.Logger("O arquivo " + a[1] + " não existe");
	}
	
	
	private void EnviarParaServidor(String msg) throws Exception {
		PrintStream enviar_nome_arquivo = new PrintStream (this.conectar_servidor.getOutputStream()); 
		enviar_nome_arquivo.println(msg);
		enviar_nome_arquivo.flush();
	}
}
