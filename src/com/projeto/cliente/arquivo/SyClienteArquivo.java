package com.projeto.cliente.arquivo;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import com.projeto.diretorio.Diretorio;

public class SyClienteArquivo {
	
	
	public static void Quebrar(File arquivo, int numero) {
		try {
			FileReader fr = new FileReader(arquivo);
			long tamanhoTotal = Files.size(arquivo.toPath());
			int quantidade = numero;
			long tamanhoPorArquivo = tamanhoTotal / quantidade;
			long tamanhoUltimoArquivo = tamanhoPorArquivo + (tamanhoTotal % quantidade);
			long maximo;
			for (int i = 0; i < quantidade; i++) {
				if (i == quantidade - 1) {
					maximo = tamanhoUltimoArquivo;
				} else {
					maximo = tamanhoPorArquivo;
				}
				File arquivoAtual = new File(i + ".temp");
				FileWriter fw = new FileWriter(arquivoAtual);
				for (int j = 0; j < maximo; j++) {
					fw.write(fr.read());
				}
				fw.close();
			}
			fr.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public static void Receber(Socket conexao, String ar) throws IOException {

		DataInputStream input = new DataInputStream(conexao.getInputStream());
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		File arquivo = new File(Diretorio.ListaDiretorios.ARQUIVOS_DOWNLOAD.nome + "/"+ ar);
		StandardOpenOption tipo = null;

		if (arquivo.exists())
			tipo = StandardOpenOption.APPEND;
		else {
			CriarArquivo(arquivo, input);
			return;
		}

		byte[] buffer = new byte[8192];
		int contar;

		
		
		
		while ((contar = input.read(buffer)) > 0) {
			baos.write(buffer, 0, contar);
		} 
		
		Files.write(arquivo.toPath(), baos.toByteArray(), tipo); 

	}
	
	
	private static void CriarArquivo(File arquivo, DataInputStream input) throws IOException {
		
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(arquivo));
		
		byte[] buffer = new byte[8192];
		int contar;
		
		while ((contar = input.read(buffer)) > 0) {
			dos.write(buffer, 0, contar);
		} 
		
		dos.close();
		
	}

}
