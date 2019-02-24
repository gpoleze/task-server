package com.gabrielpf.servidor;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class DistrubuirTarefas implements Runnable {
    private final Socket socket;

    public DistrubuirTarefas(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            System.out.println("Distribuido tarefas para " + socket.getPort());

            Scanner scanner = new Scanner(socket.getInputStream());

            while (scanner.hasNextLine()){
                String comando = scanner.nextLine();
                System.out.println(comando);
            }
            scanner.close();

            Thread.sleep(2000);
            System.out.println("Tarefa finalizada para " + socket.getPort());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
