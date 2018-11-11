package org.projeto.arquivo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;

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
		this.arquivo = new File ((envi-1) + ".zip");
		this.bytes = new byte[(int)this.arquivo.length()]; //Criar uma Array de Bytes com o tamnho do arquivo
		
		BufferedInputStream bytes_arquivo = new BufferedInputStream(new FileInputStream(this.arquivo)); //Ler os bytes do arquivo
		bytes_arquivo.read(this.bytes, 0, this.bytes.length); //Aqui estamos escrevendo os bytes do arquivo em nossa Array
	
		bytes_arquivo.close(); //Fechando o Buffered
		
		 OutputStream enviar = this.conexao.getOutputStream(); //Preparando a conexão para envio
		 enviar.write(this.bytes, 0, this.bytes.length); //Enviado nossa Array de Bytes, mesma logica da linha 40...
		 enviar.flush(); //forçar os dados a serem escritos...
		 enviar.close(); //fechando conexão
		 new inicio.Logger("Arquivo enviado...");
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
	
	
	
	public void Receber() throws Exception {
	    InputStream entrada = this.conexao.getInputStream(); //Ouvir a entrada da Array de bytes..
	    int total_bytes = entrada.read(this.bytes, 0, this.bytes.length); //Salvado os bytes na Array, novamente mesma merda da linha 40...
	    BufferedOutputStream bytes_arquivo = new BufferedOutputStream(new FileOutputStream(this.arquivo)); //Criar e preparar arquivo para escrever
	    int loop = total_bytes;
	    
	    while(total_bytes > -1) {
	    	total_bytes = entrada.read(this.bytes, loop, (this.bytes.length-loop)); //Carregar todos os bytes neste loop
	    	loop += total_bytes;
	    }
	    
		bytes_arquivo.write(this.bytes, 0, loop); //Escrever no arquivo
		bytes_arquivo.close(); //Fechando
		new inicio.Logger("Arquivo recebido...");
	}
	
	
	

}
