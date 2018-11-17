package com.projeto.erro;

import java.io.IOException;

public class CustomErro extends IOException {

	public CustomErro(String mensagem) {
		super(mensagem);
	}

}
