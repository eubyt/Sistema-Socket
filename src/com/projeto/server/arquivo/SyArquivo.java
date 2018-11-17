package com.projeto.server.arquivo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SyArquivo {

	public static void Enviar(String ip, int porta, File arquivo) throws IOException {
		
		Socket cliente = new Socket("127.0.0.1", porta);
		byte[] bytes = new byte[(int) arquivo.length()];
		BufferedInputStream bytes_arquivo = new BufferedInputStream(new FileInputStream(arquivo));
		int total = bytes_arquivo.read(bytes, 0, bytes.length);

		OutputStream enviar = cliente.getOutputStream();
		enviar.write(bytes, 0, bytes.length);
		enviar.flush();
		cliente.close();
		
	}
}
