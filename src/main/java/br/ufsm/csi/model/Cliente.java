package br.ufsm.csi.model;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente implements Runnable{

    Scanner leitor = new Scanner(System.in);
    String endereco, porta, stringPacoteCliente;
    Socket conexao;
    ObjectOutputStream saida;
    ObjectInputStream entrada;

    @Override
    public void run() {
        while (true){
            System.out.print("\nDigite o endereço IP do servidor: ");
            endereco = leitor.nextLine();
            System.out.print("Digite a porta do servidor: ");
            porta = leitor.nextLine();

            try {
                //Estabelecendo conexão com o servidor
                conexao = new Socket(endereco, Integer.parseInt(porta));

                saida = new ObjectOutputStream(conexao.getOutputStream());
                entrada = new ObjectInputStream(conexao.getInputStream());

                //Criação do pacote que será enviado para o servidor
                Pacote pacoteCliente = Pacote.builder().tipoPacote(Pacote.TipoPacote.PING).tempoNano(System.nanoTime()).build();

                //Enviando a mensagem do cliente para o servidor
                saida.writeObject(pacoteCliente);
                saida.flush();
                //Printando mensagem recebeida pelo servidor
                //Pacote mensagemEntrada = new Pacote();
                Pacote pacoteServidor = (Pacote) entrada.readObject();
                System.out.println("Pacote recebido do servidor: " + pacoteServidor);
                System.out.println("Diferença de tempo: " + ((pacoteServidor.getTempoNano()-pacoteCliente.getTempoNano())/1_000_000) + " ms.");
                //Pacote pacoteServidor = entrada.readObject();
                entrada.close();
                saida.close();
                conexao.close();

            } catch (IOException e) {
                System.out.println("\nErro de conexão com o servidor: " + new RuntimeException(e));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
