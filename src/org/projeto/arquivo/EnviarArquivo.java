package org.projeto.arquivo;



import java.io.*;
import java.net.Socket;


public class EnviarArquivo {


    private Socket servidor; //Aqui entra o servidor socket

    //Diretorios
    private String diretorio_servidor = "servidor/";


    public EnviarArquivo(Socket servidor) {
        this.servidor = servidor;
    }


    public void Receber(InputStream inputStream, String nome_arquivo) {
    	///AQUI VAI CÓDIGO PARA RECEBER ARQUIVO
       
    }


    public void Enviar(String nome_arquivo) {
///AQUI VAI CÓDIGO PARA ENVIAR ARQUIVO


   }

}
