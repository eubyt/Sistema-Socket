package com.projeto.logger;

import java.text.MessageFormat;

import com.projeto.Main;

public class UsoLogger {

	private String mensagem;

	public UsoLogger(String texto, Object... valores) {
		mensagem = new MessageFormat(texto).format(valores);
	}

	public void println() {
		println(true);
	}

	public void println(boolean formatar) {
		if (formatar == true)
			System.out.println(MessageFormat.format("[{0}] {1}", Main._opcao.name(), mensagem));
		else
			System.out.println(mensagem);
	}
}
