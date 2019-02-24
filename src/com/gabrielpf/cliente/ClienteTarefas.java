package com.gabrielpf.cliente;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTarefas {
    public static void main(String[] args) throws IOException, InterruptedException {

        Socket socket = new Socket("localhost", 12345);

        System.out.println("Conexão estabalecida");

        Runnable enviaDados = () -> {
            try (PrintStream saida = new PrintStream(socket.getOutputStream())) {

                System.out.println("Servidor ouvindo, pode enviar comandos!");
                Scanner teclado = new Scanner(System.in);

                while (teclado.hasNextLine()) {
                    String linha = teclado.nextLine();

                    if (linha.trim().matches("(\\\\exit)")){
                        System.out.println("Comando fechar conexao recebido.");
                        break;}

                    saida.println(linha);

                }

                saida.close();
                teclado.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Runnable recebeDados = () -> {
            try {
                System.out.println("Recebendo dados do servidor");
                Scanner repostaServidor = new Scanner(socket.getInputStream());

                while (repostaServidor.hasNextLine()) {
                    String linha = repostaServidor.nextLine();

                    System.out.println("Resposta do servidor: " + linha);
                }

                repostaServidor.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread threadEnviaComando = new Thread(enviaDados);
        Thread threadRecebeResposta = new Thread(recebeDados);

        threadEnviaComando.start();
        threadRecebeResposta.start();

        threadEnviaComando.join();

        System.out.println("Fechando socket do Cliente");
        socket.close();
    }
}
