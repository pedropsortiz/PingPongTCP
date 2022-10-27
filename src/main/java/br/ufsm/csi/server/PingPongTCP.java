package br.ufsm.csi.server;

import br.ufsm.csi.model.Cliente;
import br.ufsm.csi.model.Servidor;

import java.io.IOException;

public class PingPongTCP {
    public static void main(String[] args) throws IOException {
        new PingPongTCP();
    }

    public PingPongTCP() throws IOException {
        new Thread(new Servidor()).start();
        new Thread(new Cliente()).start();

    }
}
