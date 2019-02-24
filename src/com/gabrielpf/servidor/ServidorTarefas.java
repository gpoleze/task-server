package com.gabrielpf.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorTarefas {
    public static void main(String[] args) throws IOException, InterruptedException {

//        printAvaiableProcessors();

        System.out.println("--- Iniciando servidor ---");
        ServerSocket servidor = new ServerSocket(12345);

        ExecutorService threadPool = Executors.newCachedThreadPool();

        while (true) {
            Socket socket = servidor.accept();
            System.out.println("Aceitando novo cliente na porta: " + socket.getPort());

            DistrubuirTarefas distrubuirTarefas = new DistrubuirTarefas(socket);
            threadPool.execute(distrubuirTarefas);

        }

    }

    private static void printAvaiableProcessors() {
        Runtime runtime = Runtime.getRuntime();
        int qtdProcessadores = runtime.availableProcessors();
        System.out.println("Qtd de processadores: " + qtdProcessadores);
    }

    private static void printThreads() {
        Set<Thread> todasAsThreads = Thread.getAllStackTraces().keySet();

        for (Thread thread : todasAsThreads) {
            System.out.println(thread.getName());
        }
    }

}
