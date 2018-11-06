package org.projeto.importante;

import org.projeto.importante.util.ComunicarUtil;

public class Comunicar<Obj extends ComunicarUtil>  {

	private ComunicarUtil valor;
	private int Porta = 123;
	
	public Comunicar(Obj valor) {
		this.valor = valor;
		valor.Preparando(this.Porta);
		Iniciar();
	}
	
	private void Iniciar() {
		valor.Iniciar();
	}
	
}