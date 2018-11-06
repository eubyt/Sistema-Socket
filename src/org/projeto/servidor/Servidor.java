package org.projeto.servidor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

import org.projeto.importante.util.ComunicarUtil;

public class Servidor extends ComunicarUtil {

	
	private ServerSocket servidor;
	private List<PrintStream> clientes;
	
	@Override
	public void Preparando(int porta) {
		try {
			servidor = new ServerSocket(porta);
			System.out.println("Servidor iniciou..");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void Iniciar() {
		try {
		
            while (true) {
            	  Socket cliente = servidor.accept();
            	  
            	  DataOutputStream data = new DataOutputStream(cliente.getOutputStream());
            	  data.writeUTF("Ola");
            	  data.flush();
            	  
                  System.out.println("Nova conexão com o cliente " +     
                      cliente.getInetAddress().getHostAddress()
                  );
                  

            }
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
