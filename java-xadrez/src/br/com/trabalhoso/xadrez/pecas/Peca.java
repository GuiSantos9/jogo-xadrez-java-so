package br.com.trabalhoso.xadrez.pecas;
import br.com.trabalhoso.xadrez.partida.Posicao;
import br.com.trabalhoso.xadrez.partida.Tabuleiro;

import java.util.List;
    import java.util.ArrayList;

    //Toda peca tem pelo menos duas características
    //Uma cor e um movimento
    //Superclasse
    public abstract class Peca {
        private Cor cor;

        public Peca(Cor cor){
            this.cor = cor;
        }

        public Cor getCor(){
            return this.cor;
        }

        public abstract List<Posicao> calcularMovimentosPossiveis(Tabuleiro tabuleiro);

    }
