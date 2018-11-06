
package org.projeto.importante;

import org.projeto.importante.util.ComunicarUtil;

public class Comunicar<Obj extends ComunicarUtil>  {

	private ComunicarUtil valor;
	private int Porta = 123;
	private String ip = "192.168.83.8";
	
	public Comunicar(Obj valor) {
		this.valor = valor;
		valor.Preparando(ip, this.Porta);
		Iniciar();
	}
	
	private void Iniciar() {
		valor.Iniciar();
	}
	
}