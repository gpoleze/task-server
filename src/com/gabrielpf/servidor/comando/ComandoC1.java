package com.gabrielpf.servidor.comando;

import java.io.PrintStream;
import java.net.Socket;

public class ComandoC1 implements Runnable {
    private final Socket socket;
    private final PrintStream saida;

    public ComandoC1(Socket socket, PrintStream saida) {
        this.socket = socket;
        this.saida = saida;
    }

    @Override
    public void run() {
        System.out.println(socket.getPort() + ": Executando a tarefa C1");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(socket.getPort() + ": Tarefa C1 finalizada");
        saida.println(socket.getPort() + ": Comando C1 executado com sucesso");
    }
}
