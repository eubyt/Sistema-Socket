package org.projeto.cliente;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.projeto.Sistema;
import org.projeto.enviar.Enviar;
import org.projeto.enviar.EnviarUtil;

public class PreparandoCliente {

	public static boolean DigitarArquivo,ArquivoSelecionado, ArquivoExiste = false; //Solicitar no console para usuario digitar o nome do arquivo
	public static String Arquivo; //Nome do arquivo digitado pelo usuario

	public static void Preparar(Scanner msg, Socket servidor) {
		if (!DigitarArquivo)
		  SolicitarArquivo(servidor);

		if (ArquivoExiste) {
			PreparandoCliente.BaixarArquivo(msg,servidor);
		}
		else
		if (ArquivoSelecionado) {
			String msg_string = msg.nextLine();
			new Sistema.Logger("[SERVIDOR] " + msg_string);

			if (msg_string.contains("foi localizado"))
				ArquivoExiste = true;
			}
	}


	//TENTANDO BAIXAR O ARQUIVO

	private static List<Byte> bytes = new LinkedList<Byte>();
	public static void BaixarArquivo(Scanner msg,Socket servidor) {
		try {

			//ESTE METODO TEM UM ERRO, SOMENTE ARQUIVOS .TXT É POSSIVEL BAIXAR...
			OutputStream file = new FileOutputStream("clientes/" + Arquivo);

			byte[] by = Base64.getDecoder().decode(msg.nextLine());

			int tamanho = by.length-1;
			while (tamanho != -1) {
				bytes.add(by[tamanho--]);
			}

			byte[] array = new byte[bytes.size()];

			for (int i = 0; i < bytes.size(); i++) {
				array[i] = bytes.get(i).byteValue();
			}

			file.write(array);


		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static void SolicitarArquivo(Socket servidor) {
		DigitarArquivo = true;
		new Sistema.Logger("[ARQUIVO] Digite o nome do arquivo que deseja baixar.");
		CriarFrameArquivo(servidor);
	}
	
	
	
	private static void CriarFrameArquivo(final Socket servidor)
	{
		final JFrame janela = new JFrame("Digite..");
		janela.setLayout( new FlowLayout() );
		
		janela.setLocationRelativeTo(null);  
		janela.setSize(300,130);
		
		
	    janela.add(new JLabel("Nome do Arquivo:"));
	
	    final JTextField Digitar = new JTextField(20);
	    Digitar.setText("");
	    
	    janela.add(Digitar);
	    
	    
	    JButton botao = new JButton("Baixar");
	    botao.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (Digitar.getText() != "") {
	            	SelecionarArquivo(Digitar.getText(), servidor);
	            	janela.dispose();
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
