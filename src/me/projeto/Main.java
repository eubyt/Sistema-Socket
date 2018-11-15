package me.projeto;

import java.util.Scanner;

import me.projecto.lista.Opcoes;
import me.projeto.erro.CustomErro;
import me.projeto.socket.InterSocket;

/**
 * 
 * Classe principal do projeto, responsável pela execução.
 * 
 * @author UnixCF
 * @version 1.2
 * @since 2018-11-14
 */

public class Main extends Util {

	private static InterSocket socket;
	public static Scanner console = new Scanner(System.in);

	/**
	 * Responsável pelo carregamento do projeto
	 * 
	 * @param args não utilizado.
	 * 
	 */
	public static void main(String[] args) {
		System.out.println("Deseja iniciar este projeto como um servidor? [S/N]");
		System.out.println("[S] - Projeto vai ser iniciado como um servidor.");
		System.out.println("[N] - Projeto vai ser iniciado como um cliente");

		if (console.nextLine().equals("s"))
			Carregar(Opcoes.SERVIDOR);
		else
			Carregar(Opcoes.CLIENTE);

		// s.close(); //Removido para evitar erros

		try {
			CriarSocket(socket);
		} catch (CustomErro e) {
			e.printStackTrace();
		}
	}

	/**
	 * Aqui escolhemos quem vai ser carregado (Cliente ou Servidor)
	 * 
	 * @param opcao escolher entre CLIENTE / SERVIDOR, Enum Opcoes
	 */
	private static void Carregar(Opcoes opcao) {

		if (opcao == Opcoes.CLIENTE) {
			socket = getCliente();
			return;
		}

		if (opcao == Opcoes.SERVIDOR) {
			socket = getServer();
			return;
		}

	}

}
