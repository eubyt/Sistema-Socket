package org.projeto.servidor;

import org.projeto.Sistema;
import org.projeto.cliente.util.ClienteDados;
import org.projeto.importante.logger.Logger;

public class Operador {
	
	
	public Operador(String mensagem, ClienteDados dados) {
		
		if (dados.nome == null) {
			 dados.nome = mensagem;
			 new Logger("Usuario cadastrado <" + dados.nome + ">.", dados.tipo);
		}
		
		if (mensagem.contains(":")) {
			String[] variavel = mensagem.split(":");
			if (variavel[0].equals("arquivo")) {
				 dados.arquivo = variavel[1];
				 new Logger("Arquivo solicitado <" + dados.arquivo + "> pelo cliente <" + dados.nome + ">.", dados.tipo);
			}
		}
		
		new Logger("Mensagem recebida do cliente <" +dados.nome+ ">: "+ mensagem, Sistema.Tipo.CLIENTE);
		
	}

}
