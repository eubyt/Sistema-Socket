package org.projeto;

import org.projeto.cliente.Cliente;
import org.projeto.importante.Comunicar;
import org.projeto.servidor.Servidor;

public class Sistema {

	
	 
	
	 public static void main(String[] args) {
		 //Comunicar<Servidor> server = servidor();

		 Comunicar<Cliente> cliente = cliente();
	 
	 }
	 
	 
	 private static Comunicar<Servidor> servidor() {
		 return new Comunicar<Servidor>(new Servidor());
	 }
	 
	 private static Comunicar<Cliente> cliente() {
		 return new Comunicar<Cliente>(new Cliente());
	 }
}
