package org.projeto.arquivo;

import org.projeto.cliente.PreparandoCliente;
import org.projeto.enviar.Enviar;
import org.projeto.enviar.EnviarUtil;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class EnviarArquivo {


    private Socket servidor; //Aqui entra o servidor socket

    //Diretorios
    private String diretorio_servidor = "servidor/";

    public EnviarArquivo(Socket servidor) {
        this.servidor = servidor;
    }


    public void Receber(InputStream inputStream, String nome_arquivo) {
        try {
            Scanner socket = new Scanner(inputStream); //Aceitar conexão e capturar o valor de entrada

            while (socket.hasNextLine()) {
                System.out.println(socket.nextLine());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void Enviar(String nome_arquivo) {
                EnviarUtil.Adicionar(new Enviar("aqui", servidor, false));
    }

}
