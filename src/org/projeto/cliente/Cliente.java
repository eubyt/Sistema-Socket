package org.projeto.cliente;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.projeto.inicio;
import org.projeto.arquivo.Arquivo;
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
		   CriarServidorCliente();
		   
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	private void NotificarServidor() throws Exception{
		EnviarParaServidor("CriarCliente/" + this.ip + ":" + this.porta_servidor);
	}
	
	private void CriarServidorCliente() {
		Thread r = new Thread(new Runnable() {
			@Override
			public void run() {
				new ClienteServer(porta_servidor);
			}
		});//Criando servidor proprio de nosso cliente...
		r.start();
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		r.stop();
		System.out.println("Misturando...");
		
		int b = 0;
		List<File> arquivos = new ArrayList<File>();
		boolean tem = true;
		while(tem) {
			File a = new File("diretorio/" + b++ +".zip");
			if (a.exists()) {
				arquivos.add(a);
			} 
			else
				tem = false;
		}
		try {
			Juntar(arquivos, new File("recebido.zip"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
}
	

	public static void Juntar(List<File> files, File into)
	        throws IOException {
	    try (FileOutputStream fos = new FileOutputStream(into);
	         BufferedOutputStream mergingStream = new BufferedOutputStream(fos)) {
	        for (File f : files) {
	            Files.copy(f.toPath(), mergingStream);
	        }
	    }
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
					 
					 if (mensagem.contains("Download/")) {
						    String[] linhas = mensagem.split("/");
							System.out.println("Enviando arquivo para " + linhas[2]);
							EnviarArquivo(linhas[1], linhas[2], linhas[3], linhas[4]);
							
						 } else
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
	
	
	private void EnviarArquivo(String arquivo, String ip, String quebrar, String enviar) {
		String[] destino_ip = ip.split(":");
		try {
			Socket destino = new Socket(destino_ip[0], Integer.parseInt(destino_ip[1]));
			new Arquivo(arquivo, destino).Enviar(Integer.parseInt(quebrar), Integer.parseInt(enviar));
			
			destino.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void BuscarArquivo(String texto) {
		String[] a = texto.split("/");
		new inicio.Logger("Servidor solicitou a busca do arquivo: " + a[1]);
		if (new File("baixados").exists()) {
			new inicio.Logger("Arquivo " + a[1] + " existe...");
			try {
				EnviarParaServidor("Localizado/" + a[1]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
			new inicio.Logger("O arquivo " + a[1] + " não existe");
	}
	
	
	private void EnviarParaServidor(String msg) throws Exception {
		PrintStream enviar_nome_arquivo = new PrintStream (this.conectar_servidor.getOutputStream()); 
		enviar_nome_arquivo.println(msg);
		enviar_nome_arquivo.flush();
	}
}
