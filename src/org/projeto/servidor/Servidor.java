package org.projeto.servidor;

import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.projeto.inicio;
import org.projeto.arquivo.Arquivo;

import java.io.*;

public class Servidor {

	private ServerSocket servidor; //Servidor
	
	private boolean ouvir_porta = false;
	HashMap<Socket, String> servidores = new HashMap<Socket, String>();
	
	
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
				 InputStream input = conexao.getInputStream(); //Capturar entrada dos cliente
				 Scanner scan = new Scanner(input);
				 
				 String[] comandos = scan.nextLine().split("/");
				 
				 if (comandos[0].equals("CriarCliente")) {
					 servidores.put(conexao, comandos[1]); //Salvar Cliente
					 EnviarParaCliente(visualizarArquivos(), conexao);
				 }
				 
				 
				// new Arquivo(scan.nextLine(), conexao).Enviar();
				 
			} catch (Exception e) {
				new inicio.Logger("Conexão desligada de forma forçada de um cliente.");
			}
		}
	}
	
	
	
	private String visualizarArquivos() throws IOException {

		File diretorio = new File("diretorio");
		File arquivos[] = diretorio.listFiles();
		int i = 0;
		String  retorno = "Lista de Arquivos para Download: ";
		for (int j = arquivos.length; i < j; i++) {
			File arquivo = arquivos[i];
			retorno = "----" + arquivo.getName() + "\n";
		}
		
		return retorno;
	}
	
	private void EnviarParaCliente(String msg, Socket cliente) throws Exception {
		PrintStream enviar_nome_arquivo = new PrintStream (cliente.getOutputStream()); 
		enviar_nome_arquivo.println(msg);
	}
}
