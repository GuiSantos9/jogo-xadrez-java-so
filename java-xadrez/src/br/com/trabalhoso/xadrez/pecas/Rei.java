package br.com.trabalhoso.xadrez.pecas;
import br.com.trabalhoso.xadrez.partida.Posicao;
import br.com.trabalhoso.xadrez.partida.Tabuleiro;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação da peça Rei.
 * O Rei se move uma casa em qualquer direção.
 */
public class Rei extends Peca {

    public Rei(Cor cor) {
        super(cor);
    }

    @Override
    public List<Posicao> calcularMovimentosPossiveis(Tabuleiro tabuleiro) {
        List<Posicao> movimentos = new ArrayList<>();

        Posicao minhaPosicao = tabuleiro.getPosicaoDaPeca(this);

        // Verificação de segurança
        if (minhaPosicao == null) {
            return movimentos;
        }

        int linha = minhaPosicao.getLinha();
        int coluna = minhaPosicao.getColuna();

        // --- LÓGICA DE MOVIMENTO ---

        // Array com todas as 8 direções possíveis (vertical, horizontal e diagonal)
        int[][] direcoes = {
                {-1, -1}, {-1, 0}, {-1, 1}, // Cima (esquerda, centro, direita)
                { 0, -1},          { 0, 1}, // Meio (esquerda, direita)
                { 1, -1}, { 1, 0}, { 1, 1}  // Baixo (esquerda, centro, direita)
        };

        for (int[] dir : direcoes) {
            int novaLinha = linha + dir[0];
            int novaColuna = coluna + dir[1];

            Posicao destino = new Posicao(novaLinha, novaColuna);

            // 1. O destino está DENTRO do tabuleiro?
            if (novaLinha >= 0 && novaLinha <= 7 && novaColuna >= 0 && novaColuna <= 7) {

                // 2. Se está no tabuleiro, verificamos a peça que está lá
                Peca pecaNoDestino = tabuleiro.getPeca(destino);

                // Se a casa está VAZIA (null) ou contém uma peça INIMIGA,
                // o movimento é válido.
                if (pecaNoDestino == null || pecaNoDestino.getCor() != this.getCor()) {
                    movimentos.add(destino);
                }

                // Se a casa tiver uma peça AMIGA, não fazemos nada.
            }
        }

        return movimentos;
    }
}