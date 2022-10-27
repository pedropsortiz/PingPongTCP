package br.ufsm.csi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pacote implements Serializable {

    private TipoPacote tipoPacote;
    private Long tempoNano;
    private static final String nome = "Pedro";

    public Pacote(Object readObject) {

    }

    public TipoPacote getTipoPacote() {
        return tipoPacote;
    }

    public void setTipoPacote(TipoPacote tipoPacote) {
        this.tipoPacote = tipoPacote;
    }

    public Long getTempoNano() {
        return tempoNano;
    }

    public void setTempoNano(Long tempoNano) {
        this.tempoNano = tempoNano;
    }

    public String getNome() {
        return nome;
    }

    public enum TipoPacote { PING, PONG }

}
