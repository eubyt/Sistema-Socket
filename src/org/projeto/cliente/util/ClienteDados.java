package org.projeto.cliente.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.projeto.Sistema;
import org.projeto.importante.logger.Logger;

public class ClienteDados {
	
	 private DataInputStream ler;
	 private DataOutputStream enviar;
	 public String ip;
	 
	 public String nome = null;
	 
	 
	public ClienteDados(Socket socket) {
		try {
			ip = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
			ler = new DataInputStream(socket.getInputStream());
			enviar = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public String Receber(Sistema.Tipo tipo) {
		try {
			return new Logger(ler.readUTF().toString(), tipo).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void Enviar(String mensagem, Sistema.Tipo tipo) {
		
		try {
			enviar.writeUTF(mensagem);
			enviar.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			new Logger("<Servidor -> " +ip+ "> Mensagem (" +mensagem+ ") enviada com sucesso. ", tipo);
		}
	
	}
	


}
