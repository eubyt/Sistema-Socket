package org.projeto.importante;

import java.util.ArrayList;
import java.util.Iterator;

public class Tarefas {
	
	private static ArrayList<Criar> lista = new ArrayList<>();
	
	
	public static void Adicionar(Criar criar) {
		lista.add(criar);
	}
	
	public static void Executar() {
		Iterator<Criar> executar = lista.iterator();

		while (executar.hasNext()) {
				executar.next().Executar();
		}
		
		lista.clear();
	}
	
	public interface Criar {
		
		public void Executar();
	}

}
