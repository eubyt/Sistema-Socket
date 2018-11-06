package org.projeto.cliente;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.projeto.importante.util.ComunicarUtil;

public class Cliente extends ComunicarUtil {

	Socket cliente;
    Scanner teclado = new Scanner(System.in);
    DataInputStream data = null;
    
    
	@Override
	public void Preparando(String ip,int porta) {
		try {
			cliente = new Socket(ip, porta);
			System.out.println("O cliente se conectou ao servidor!");
			
			data = new DataInputStream(cliente.getInputStream());
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    
	@Override
	public void Iniciar() {
	
		try {
			while (true) {
				System.out.println("Remoto: " + data.readUTF());
		        }
		        
		       
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	 
	}

}
