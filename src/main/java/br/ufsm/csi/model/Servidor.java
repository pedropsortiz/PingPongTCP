package br.ufsm.csi.model;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor implements Runnable{

    Scanner leitor = new Scanner(System.in);
    private final int porta = 5000;
    ServerSocket conexaoServidor = new ServerSocket(porta);
    Socket conexaoCliente;
    PrintWriter mensagemCliente;
    BufferedReader mensagemServidor;
    // Declaring ANSI_RESET so that we can reset the color
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_START = "\u001B[32m";


    public Servidor() throws IOException {
    }

    @Override
    public void run() {
        System.out.println(ANSI_START + "Escutando porta " + porta + ANSI_RESET);
        try {
            while (true){
                conexaoCliente = conexaoServidor.accept();
                System.out.println("Conexão estabelecida com o cliente, endereço: " + conexaoCliente.getInetAddress().getHostName() + ", porta: " + porta);

                ObjectOutputStream saida = new ObjectOutputStream(conexaoCliente.getOutputStream());
                ObjectInputStream entrada = new ObjectInputStream(conexaoCliente.getInputStream());

                System.out.println("\nPacote recebido do cliente: " +  entrada.readObject());

                Pacote pacoteCliente = Pacote.builder().tipoPacote(Pacote.TipoPacote.PONG).tempoNano(System.nanoTime()).build();
                saida.writeObject(pacoteCliente);
                saida.flush();

                conexaoCliente.close();
            }
        } catch (IOException e) {
//            System.out.println("Erro de conexão: " + new RuntimeException(e));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
