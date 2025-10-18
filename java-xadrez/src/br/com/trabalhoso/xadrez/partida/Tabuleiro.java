package br.com.trabalhoso.xadrez.partida;
import br.com.trabalhoso.xadrez.pecas.Cor;
import br.com.trabalhoso.xadrez.pecas.Peca;
import br.com.trabalhoso.xadrez.pecas.Torre;

import java.util.List;
import java.util.ArrayList;

public class Tabuleiro {
    private Peca[][] pecas = new Peca[8][8];

    public Tabuleiro(){
        this.pecas = new Peca[8][8];
        iniciarPartida();
    }

    public void iniciarPartida(){
        colocarPeca(new Torre(Cor.BRANCA), new Posicao(0, 0));
        //etc
    }

    public void colocarPeca(Peca peca, Posicao posicao){
        this.pecas[posicao.getLinha()][posicao.getColuna()] = peca;
    }

    public Peca getPeca(Posicao posicao){
        return this.pecas[posicao.getLinha()][posicao.getColuna()];
    }

    public void moverPeca(Posicao origem, Posicao destino){
        Peca pecaMovida = getPeca(origem);
        this.pecas[origem.getLinha()][origem.getColuna()] = null;
        this.pecas[destino.getLinha()][destino.getColuna()] = pecaMovida;
    }

}
