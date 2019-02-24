package com.gabrielpf.servidor;

import java.net.Socket;

public class DistrubuirTarefas implements Runnable{
    private final Socket socket;

    public DistrubuirTarefas(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Distribuido tarefas para " + socket.getPort());

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
