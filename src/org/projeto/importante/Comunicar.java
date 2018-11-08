
package org.projeto.importante;

import java.io.File;

import org.projeto.importante.util.ComunicarUtil;

public class Comunicar<Obj extends ComunicarUtil>  {

	private ComunicarUtil valor;
	private int Porta = 123;
	private String ip = "192.168.83.8";
	
	public Comunicar(Obj valor) {
		this.valor = valor;
		Diretorio();
		valor.Preparando(ip, this.Porta);
		Iniciar();
	}
	
	private void Iniciar() {
		valor.Iniciar();
	}
	
	private void Diretorio() {
		new File("servidor", "arquivos").mkdirs();
		new File("servidor", "usuario").mkdirs();
	}
	
}