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


    public void Receber(String nome_arquivo) {
        try {
            System.out.println("Chegou algo aqui ");
            System.out.println(this.servidor.getInputStream().read());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void Enviar(String nome_arquivo) {
                EnviarUtil.Adicionar(new Enviar("aqui", servidor, false));
    }

}
