package com.gabrielpf.servidor.comando;

import java.io.PrintStream;
import java.net.Socket;

public class ComandoC2 implements Runnable {
    private final Socket socket;
    private final PrintStream saida;

    public ComandoC2(Socket socket, PrintStream saida) {
        this.socket = socket;
        this.saida = saida;
    }

    @Override
    public void run() {
        System.out.println(socket.getPort() + ": Executando a tarefa C2");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        throw new RuntimeException("Erro no comando c2");

//        System.out.println(socket.getPort() + ": Tarefa C2 finalizada");
//        saida.println(socket.getPort() + ": Comando C2 executado com sucesso");
    }
}
