package com.gabrielpf.servidor;

import java.io.IOException;
import java.io.PrintStream;
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

            Scanner entradaCliente = new Scanner(socket.getInputStream());
            PrintStream saidaCliente = new PrintStream(socket.getOutputStream());

            while (entradaCliente.hasNextLine()) {
                String comando = entradaCliente.nextLine();


                System.out.println("Comando recebido: " + comando);
                switch (comando) {
                    case "c1": {
                        saidaCliente.println("Confirmação comando c1");
                        break;
                    }
                    case "c2": {
                        saidaCliente.println("Confirmação comando c2");
                        break;
                    }
                    default: {
                        saidaCliente.println("Comando não encontrado");
                    }
                }

            }

            saidaCliente.close();
            entradaCliente.close();

            System.out.println("Tarefa finalizada para " + socket.getPort());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
