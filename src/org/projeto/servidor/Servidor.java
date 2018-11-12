package org.projeto.servidor;

import java.net.*;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Scanner;

import org.projeto.inicio;

import java.io.*;

public class Servidor {

	private ServerSocket servidor; //Servidor
	
	private boolean ouvir_porta = false;
	HashMap<Socket, String> servidores = new HashMap<Socket, String>();
	HashMap<String, Socket> arquivo = new HashMap<String, Socket>();
	ArrayList<Socket> pessoas = new ArrayList<Socket>();
	
	public Servidor() {
		
		try {
		this.servidor = new ServerSocket(12345);
		this.ouvir_porta = true;
		
		new inicio.Logger("Servidor iniciado...");
		//Chamar Entrada
		Entrada();
		
		} catch(Exception ex) {
			
		}
		
	}
	
	
	
	private void Entrada() throws IOException {
		while(ouvir_porta) {
			
			  Socket conexao = servidor.accept(); //ouvir a conexao
			  new inicio.Logger("Conexão -> " + conexao);
			  try {
		
					new Thread(new Runnable() {
						@Override
						public void run() {
							try {
							Scanner entrada = new Scanner(conexao.getInputStream());
					         
							  while (entrada.hasNextLine()) {
					        	     String texto = entrada.nextLine();
							
									 String[] comandos = texto.split("/");
									 
									 if (comandos[0].equals("CriarCliente")) {
										 servidores.put(conexao, comandos[1]); //Salvar Cliente
										 EnviarParaCliente(visualizarArquivos(), conexao);
									 } else
									 if(comandos[0].equals("Baixar")) {
										  new inicio.Logger("Solicitando download do arquivo " + comandos[1] + "....");
										  Buscar(comandos[1], conexao);
										  Thread.sleep(1000);
										  EnviarParaCliente(pessoas.size() + " possuem o seu arquivo..", conexao);
										  SolicitarDownload(comandos[1]);
										  
									 } else if (comandos[0].equals("Localizado")) {
										pessoas.add(conexao);
										 
									 }
							  }
							}
							 catch (Exception e) {
								  
							  }
						}
						}).start();;
				  
				 

				 
				// new Arquivo(scan.nextLine(), conexao).Enviar();
				 
			} catch (Exception e) {
				new inicio.Logger("Conexão desligada de forma forçada de um cliente.");
			}
		}
	}
	
	
	private void SolicitarDownload(String arquivo) {
		int numero= 1;
		for (Socket clientes : pessoas) {
			   try {
				EnviarParaCliente("Download/"+arquivo+"/" + servidores.get((this.arquivo.get(arquivo)))+"/" + pessoas.size() + "/"+numero++, clientes);
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void Buscar(String arquivo, Socket cliente_que_solicitou) {
	    this.arquivo.put(arquivo, cliente_que_solicitou);
	    
		for (Socket clientes : servidores.keySet()) {
		try {
			if (clientes != cliente_que_solicitou)
			   EnviarParaCliente("Buscar/"+arquivo, clientes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	
	private String visualizarArquivos() throws IOException {

		File diretorio = new File("diretorio");
		File arquivos[] = diretorio.listFiles();
		int i = 0;
		String  retorno = "Lista de Arquivos para Download:/n";
		for (int j = arquivos.length; i < j; i++) {
			File arquivo = arquivos[i];
			retorno += " [-] " + arquivo.getName() + "/n";
		}
		
		return retorno;
	}
	
	private void EnviarParaCliente(String msg, Socket cliente) throws Exception {
		PrintStream enviar_nome_arquivo = new PrintStream (cliente.getOutputStream()); 
		enviar_nome_arquivo.println(msg);
		enviar_nome_arquivo.flush();
	}
}
