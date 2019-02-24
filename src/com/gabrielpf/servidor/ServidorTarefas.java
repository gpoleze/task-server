package com.gabrielpf.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTarefas {
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("--- Iniciando servidor ---");
        ServerSocket servidor = new ServerSocket(12345);

        while (true) {
            Socket socket = servidor.accept();
            System.out.println("Aceitando novo cliente na porta: " + socket.getPort());

            DistrubuirTarefas distrubuirTarefas = new DistrubuirTarefas(socket);
            Thread threadCliente = new Thread(distrubuirTarefas);
            threadCliente.start();
        }

    }
}
