package com.gabrielpf.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServidorTarefas {


    private final ExecutorService threadPool;
    private final ServerSocket servidor;
    private AtomicBoolean estaRodando;

    public ServidorTarefas() throws IOException {
        System.out.println("--- Iniciando servidor ---");
        threadPool = Executors.newFixedThreadPool(4, new ThreadFactoryService(Executors.defaultThreadFactory()));
        servidor = new ServerSocket(12345);
        estaRodando = new AtomicBoolean(true);
    }

    public void rodar() throws IOException {
        try {
            while (estaRodando.get()) {
                Socket socket = servidor.accept();
                System.out.println("Aceitando novo cliente na porta: " + socket.getPort());

                DistrubuirTarefas distrubuirTarefas = new DistrubuirTarefas(threadPool, socket, this);
                threadPool.execute(distrubuirTarefas);
            }
        } catch (SocketException se) {
            System.out.println("SocketException. Está rodando? " + estaRodando);
            ;
        }
    }

    public void parar() throws IOException {
        estaRodando.set(false);
        servidor.close();
        threadPool.shutdown();
    }

    public static void printAvaiableProcessors() {
        Runtime runtime = Runtime.getRuntime();
        int qtdProcessadores = runtime.availableProcessors();
        System.out.println("Qtd de processadores: " + qtdProcessadores);
    }

    public static void printThreads() {
        Set<Thread> todasAsThreads = Thread.getAllStackTraces().keySet();

        for (Thread thread : todasAsThreads) {
            System.out.println(thread.getName());
        }
    }

}
