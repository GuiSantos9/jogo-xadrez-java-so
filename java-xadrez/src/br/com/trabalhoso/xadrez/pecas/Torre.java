package br.com.trabalhoso.xadrez.pecas;

import java.util.ArrayList;
import java.util.List;

//Herda as características da Peça
public class Torre extends Peca {

    public Torre(Cor cor){
        super(cor); //Heranca da superclasse
    }

    @Override
    public List<Posicao> calcularMovimentosPossíveis(){
        List<Posicao> movimentos = new ArrayList<>();

        //logica de movimentação

        return movimentos;
    }

}
