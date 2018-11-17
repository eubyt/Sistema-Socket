package com.projeto.diretorio;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import com.projeto.Main;
import com.projeto.lista.Opcoes;

public class Diretorio {

	private static HashMap<ListaDiretorios, File> arquivo = new HashMap<ListaDiretorios, File>();

	public static void CriarDiretorio() {
		Iterator<ListaDiretorios> lista = Arrays.asList(ListaDiretorios.values()).iterator();
		do {
			ListaDiretorios valor = lista.next();

			if (valor.opcao.equals(Main._opcao))
				arquivo.put(valor, CriarPasta(valor.nome));

		} while (lista.hasNext());
	}

	public static File getPasta(ListaDiretorios lista) {
		return arquivo.get(lista);
	}

	private static File CriarPasta(String nome) {
		File pasta = new File(nome);
		pasta.mkdirs();
		return pasta;
	}

	public static enum ListaDiretorios {

		ARQUIVOS_SERVIDOR(Opcoes.SERVIDOR, "Arquivos"), ARQUIVOS_DOWNLOAD(Opcoes.CLIENTE, "Baixados");

		protected Opcoes opcao;
		public String nome;

		ListaDiretorios(Opcoes opcoes, String pasta) {
			opcao = opcoes;
			nome = pasta;
		}

	}

}
