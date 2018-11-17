package com.projeto;

import java.text.MessageFormat;
import java.util.Scanner;

import com.projeto.diretorio.Diretorio;
import com.projeto.erro.CustomErro;
import com.projeto.lista.Opcoes;
import com.projeto.socket.InterSocket;

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
	public static Opcoes _opcao;

	private static String ip;
	private static int porta;

	/**
	 * Responsável pelo carregamento do projeto
	 * 
	 * @param args não utilizado.
	 * 
	 */
	public static void main(String[] args) {

		System.out.println("IP do servidor:");
		ip = console.nextLine();

		System.out.println("Porta do servidor:");
		porta = Integer.parseInt(console.nextLine());

		System.out.println("Deseja iniciar este projeto como um servidor? [S/N]");
		System.out.println("[S] - Projeto vai ser iniciado como um servidor.");
		System.out.println("[N] - Projeto vai ser iniciado como um cliente");

		if (console.nextLine().equals("s"))
			Carregar(Opcoes.SERVIDOR);
		else
			Carregar(Opcoes.CLIENTE);

		System.out.println(MessageFormat.format("Você escolheu o tipo {0}", _opcao.name()));

		// s.close(); //Removido para evitar erros

		Diretorio.CriarDiretorio();

		try {
			CriarSocket(socket, ip, porta);
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

		_opcao = opcao;

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
