package org.projeto.enviar;

import java.util.ArrayList;

public class EnviarUtil {

	private static ArrayList<Enviar> enviar = new ArrayList<>();
	
	
	public static void Adicionar(Enviar en) {
		enviar.add(en);
	}
	
	public static ArrayList<Enviar> enviar() {
		return enviar;
	}
	
}
