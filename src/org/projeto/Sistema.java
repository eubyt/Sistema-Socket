package org.projeto;

import java.util.Scanner;

import org.projeto.cliente.Cliente;
import org.projeto.importante.Comunicar;
import org.projeto.servidor.Servidor;
import org.projeto.servidor.ServidorTeste;

public class Sistema {
	
	public static void main(String[] args) {
		 Scanner s = new Scanner(System.in);
		 System.out.println("Deseja iniciar como servidor? [S/N]");
		 String escolha = s.nextLine();
		 
		 if (escolha.toLowerCase().equals("s")) {
		     System.out.println("Sistema iniciado como servidor.");
		     Comunicar<ServidorTeste> server = servidor();
		 } else {
			 System.out.println("Sistema iniciado como cliente.");
			 Comunicar<Cliente> cliente = cliente();
		 }
	 
	 }
	 
	 
	 private static Comunicar<ServidorTeste> servidor() {
		 return new Comunicar<ServidorTeste>(new ServidorTeste());
	 }
	 
	 private static Comunicar<Cliente> cliente() {
		 return new Comunicar<Cliente>(new Cliente());
	 }
	 
	 
	 public enum Tipo {
		 SERVER,
		 CLIENTE;
	 }
}
