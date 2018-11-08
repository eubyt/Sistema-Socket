
package org.projeto.cliente;

import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

import org.projeto.Sistema;
import org.projeto.cliente.util.ClienteDados;
import org.projeto.importante.logger.Logger;
import org.projeto.importante.util.ComunicarUtil;

public class Cliente extends ComunicarUtil {

	Socket cliente;
    Scanner teclado = new Scanner(System.in);
    
    public HashMap<String, ClienteDados> conexoes = new HashMap<String, ClienteDados>();
    
	private Sistema.Tipo tipo = Sistema.Tipo.CLIENTE;
	
	@Override
	public void Preparando(String ip,int porta) {
		try {
			new Logger("Criando servidor cliente..", tipo);
			cliente = new Socket(ip, porta);
			new Logger("Servidor cliente criado com sucesso, conectado ao servidor principal <" +ip + ":" + porta +">", tipo);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
    
	@Override
	public void Iniciar() {

		
		try {
			while (true) {
				
				ClienteDados c = new ClienteDados(cliente, tipo);
				if (conexoes.containsKey(c.ip)) 
					c = conexoes.get(c.ip);
				 else 
					conexoes.put(c.ip, c);

				new Thread(c.tratar()).start();
				
				if (c.nome == null) {
					c.nome = console().nextLine();
					new Thread(c.Enviar(c.nome)).start();
					new Logger("Seja Bem-vindo, " + c.nome, tipo);
					new Logger("Digite o nome do arquivo que deseja baixar:", tipo);
				}  else {
					String arquivo = console().nextLine();
					new Thread(c.Enviar("Arquivo:" + arquivo)).start();
				}
				
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}


	private Scanner console() {
		return new Scanner(System.in);
	}



}
