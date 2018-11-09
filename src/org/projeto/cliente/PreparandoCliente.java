package org.projeto.cliente;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.projeto.Sistema;
import org.projeto.enviar.Enviar;
import org.projeto.enviar.EnviarUtil;

public class PreparandoCliente {

	public static boolean DigitarArquivo,ArquivoSelecionado = false; //Solicitar no console para usuario digitar o nome do arquivo
	public static String Arquivo; //Nome do arquivo digitado pelo usuario
	
	public static void Preparar(Scanner msg, Socket servidor) {
		if (!DigitarArquivo)
		  SolicitarArquivo(servidor);
	}
	
	private static void SolicitarArquivo(Socket servidor) {
		DigitarArquivo = true;
		new Sistema.Logger("[ARQUIVO] Digite o nome do arquivo que deseja baixar.");
		CriarFrameArquivo(servidor);
	}
	
	
	
	private static void CriarFrameArquivo(Socket servidor)
	{
		JFrame janela = new JFrame("Digite..");
		janela.setLayout( new FlowLayout() );
		
		janela.setLocationRelativeTo(null);  
		janela.setSize(300,130);
		
		
	    janela.add(new JLabel("Nome do Arquivo:"));
	
	    JTextField Digitar = new JTextField(20);
	    Digitar.setText("");
	    
	    janela.add(Digitar);
	    
	    
	    JButton botao = new JButton("Baixar");
	    botao.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (Digitar.getText() != "") {
	            	SelecionarArquivo(Digitar.getText(), servidor);
	            }
	        }
	    });
	    janela.add(botao);

	    janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setVisible(true);
		
	}
	public static void SelecionarArquivo(String msg, Socket servidor) {
		ArquivoSelecionado = true;
		Arquivo = msg;
		
		new Sistema.Logger("[ARQUIVO] Solicitando download do arquivo: " + Arquivo);
		EnviarUtil.Adicionar(new Enviar("Arquivo:" + Arquivo,  servidor, false));
		
	}
	
	
	
}
