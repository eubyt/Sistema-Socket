package org.projeto.threads;

import org.projeto.threads.estrutura.EstruturaThreads;

public class ThreadsProjeto<L extends EstruturaThreads> {

	
	L carregar = null;
	
	public ThreadsProjeto(L load) {
		this.carregar = load;
	}
	
	
	public void iniciar() {
		//Iniciar os Threads do Servidor ou Cliente
		new Thread(carregar.Enviar()).start(); //Criar Thread para Enviar as mensagens
		new Thread(carregar.Receber()).start(); //Criar Thread para escutar a porta do Socket
	}
	
	
	
	
	
	
}
