package com.gabrielpf.servidor;

import java.io.IOException;

public class ServidorPrincipal {

    public static void main(String[] args) throws IOException {

        ServidorTarefas servidor = new ServidorTarefas();
        servidor.rodar();
        servidor.parar();
    }
}
