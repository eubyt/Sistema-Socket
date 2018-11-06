package org.projeto;

import java.util.Scanner;

import org.projeto.cliente.Cliente;
import org.projeto.importante.Comunicar;
import org.projeto.servidor.Servidor;

public class Sistema {

	
	 
	
	 public static void main(String[] args) {
		 Scanner s = new Scanner(System.in);
		 
		 System.out.println("Iniciar como um servidor? [S/N]"); 
		 String escolha = s.nextLine();
		 
		 if (escolha.toLowerCase().equals("s")) {
		     Comunicar<Servidor> server = servidor();
		     System.out.println("Sistema iniciado como servidor.");
		 } else {
			 Comunicar<Cliente> cliente = cliente();
			 System.out.println("Sistema iniciado como cliente.");
		 }
	 
	 }
	 
	 
	 private static Comunicar<Servidor> servidor() {
		 return new Comunicar<Servidor>(new Servidor());
	 }
	 
	 private static Comunicar<Cliente> cliente() {
		 return new Comunicar<Cliente>(new Cliente());
	 }
}
