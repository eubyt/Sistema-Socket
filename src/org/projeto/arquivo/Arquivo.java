package org.projeto.arquivo;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import org.projeto.inicio;

public class Arquivo {

	private String nome_arquivo; 
	private Socket conexao;
	
	private File arquivo;
	private byte[] bytes;
	
	public Arquivo(String nome_arquivo, Socket conexao) {
		this.nome_arquivo = nome_arquivo;
		this.conexao = conexao;
		try {
			Formatar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //Setar os valores
	}
	
	private void Formatar() throws Exception {
		this.arquivo = new File ("diretorio/" + nome_arquivo);
		
		if (!this.arquivo.exists()) {
			//Se caso ele não existir preparar este classe para download
			this.bytes = new byte[40028922]; // tamanho maximo de bytes, sim resolvi fazer está referencia ,-,
			
			return;
		}
		//Se existir preparar para upload
	}
	
	
	/// @envi é o arquivo para enviar
	public void Enviar(int quebrar, int envi) throws Exception {
		Quebrar(this.arquivo, quebrar);
		System.out.println(envi);
		System.out.println((envi-1));
		this.arquivo = new File ((envi-1) + ".zip");
		this.bytes = new byte[(int)this.arquivo.length()]; //Criar uma Array de Bytes com o tamnho do arquivo
		
		BufferedInputStream bytes_arquivo = new BufferedInputStream(new FileInputStream(this.arquivo)); //Ler os bytes do arquivo
		int total = bytes_arquivo.read(this.bytes, 0, this.bytes.length); //Aqui estamos escrevendo os bytes do arquivo em nossa Array
		System.out.println(total);
		bytes_arquivo.close(); //Fechando o Buffered
		
		 OutputStream enviar = this.conexao.getOutputStream(); //Preparando a conexão para envio
		 enviar.write(this.bytes, 0, this.bytes.length); //Enviado nossa Array de Bytes, mesma logica da linha 40...
		 enviar.flush(); //forçar os dados a serem escritos...
		 enviar.close(); //fechando conexão
		 new inicio.Logger("Arquivo enviado...");
		 
	}
	

	
	
	public void Receber() throws Exception {
		 DataInputStream dis = new DataInputStream(conexao.getInputStream());
		 File arquivo = new File("0.zip");
		 if (!arquivo.exists()) {
		 DataOutputStream dos = new DataOutputStream(new FileOutputStream(arquivo));
		 byte[] buffer = new byte[8192]; // or wherever you like > 0
		 int count;
         while ((count = dis.read(buffer)) > 0)
         {
             dos.write(buffer, 0, count);
         }
		 } else {
			 byte[] buffer = new byte[8192]; // or wherever you like > 0
			 int count;
	         while ((count = dis.read(buffer)) > 0)
	         {
	             Files.write(arquivo.toPath(),buffer, StandardOpenOption.APPEND);
	         }
		 }
		 
		 
	}
	
	public void Quebrar(File arquivo, int numero) {
		try {
	       FileReader fr = new FileReader( arquivo );
           long tamanhoTotal = Files.size( arquivo.toPath() );
           int quantidade = numero;
           long tamanhoPorArquivo = tamanhoTotal / quantidade;
           long tamanhoUltimoArquivo = tamanhoPorArquivo + (tamanhoTotal % quantidade);
           long maximo;
           for ( int i = 0; i < quantidade; i++ ) {
               if ( i == quantidade - 1 ) {
                   maximo = tamanhoUltimoArquivo;
               } else {
                   maximo = tamanhoPorArquivo;
               }
               File arquivoAtual = new File( i + ".zip" );
               FileWriter fw = new FileWriter( arquivoAtual );
               for ( int j = 0; j < maximo; j++ ) {
                   fw.write( fr.read() );
               }
               fw.close();
           }
           fr.close();
       } catch ( Exception exc ) {
           exc.printStackTrace();
       }
}
	
}
