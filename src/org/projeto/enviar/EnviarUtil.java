package org.projeto.enviar;

import java.util.ArrayList;

import org.projeto.Sistema;

public class EnviarUtil {

	//ArrayList com mensagens a ser enviada
	private static ArrayList<Enviar> enviar = new ArrayList<>();
	
	
	//Utilizar este metodo para setar as mensagens para enviar
	public static void Adicionar(Enviar en) {
		enviar.add(en);
	}
	
	public static ArrayList<Enviar> enviar() {
		return enviar;
	}
	
}
