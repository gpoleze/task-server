package com.gabrielpf.servidor;

import com.gabrielpf.servidor.comando.ComandoC1;
import com.gabrielpf.servidor.comando.ComandoC2;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

public class DistrubuirTarefas implements Runnable {
    private final ExecutorService threadPool;
    private final Socket socket;
    private final ServidorTarefas servidor;

    public DistrubuirTarefas(ExecutorService threadPool, Socket socket, ServidorTarefas servidor) {
        this.threadPool = threadPool;
        this.socket = socket;
        this.servidor = servidor;
    }

    @Override
    public void run() {

        try {
            System.out.println("Distribuido tarefas para " + socket.getPort());

            Scanner entradaCliente = new Scanner(socket.getInputStream());
            PrintStream saidaCliente = new PrintStream(socket.getOutputStream());

            while (entradaCliente.hasNextLine()) {
                String comando = entradaCliente.nextLine().toLowerCase();

                System.out.println(socket.getPort() + ": Comando recebido: " + comando);
                switch (comando) {
                    case "c1": {
                        saidaCliente.println(socket.getPort() + ": Confirmação comando c1");
                        threadPool.execute(new ComandoC1(socket, saidaCliente));
                        break;
                    }
                    case "c2": {
                        saidaCliente.println(socket.getPort() + ": Confirmação comando c2");
                        threadPool.execute(new ComandoC2(socket, saidaCliente));
                        break;
                    }
                    case "shutdown": {
                        saidaCliente.println(socket.getPort() + ": Desligando Servidor");
                        servidor.parar();
                        break;
                    }
                    default: {
                        saidaCliente.println(socket.getPort() + ": Comando não encontrado");
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
