package br.com.trabalhoso.xadrez.pecas;

import br.com.trabalhoso.xadrez.partida.Posicao;
import br.com.trabalhoso.xadrez.partida.Tabuleiro;
import java.util.ArrayList;
import java.util.List;

//Herda as características da Peça
public class Peao extends Peca{

    public Peao(Cor cor){
        super(cor);
    }

    @Override
    public List<Posicao> calcularMovimentosPossiveis(Tabuleiro tabuleiro, Posicao minhaPosicao) {
        List<Posicao> movimentos = new ArrayList<>();
        int linha = minhaPosicao.getLinha();
        int coluna = minhaPosicao.getColuna();

        if (getCor() == Cor.BRANCA) {
            // --- MOVIMENTOS DO PEÃO BRANCO (anda "para cima", linha diminui) ---

            // 1. Mover 1 casa para frente
            Posicao frenteUma = new Posicao(linha - 1, coluna);
            // Testa se a posição existe (linha >= 0) e se está vaga
            if (linha > 0 && tabuleiro.getPeca(frenteUma) == null) {
                movimentos.add(frenteUma);

                // 2. Mover 2 casas (só na primeira jogada)
                // Testa se está na linha inicial (6) e se a casa 2 à frente está vaga
                Posicao frenteDuas = new Posicao(linha - 2, coluna);
                if (linha == 6 && tabuleiro.getPeca(frenteDuas) == null) {
                    movimentos.add(frenteDuas);
                }
            }

            // 3. Captura Diagonal Esquerda
            Posicao diagEsq = new Posicao(linha - 1, coluna - 1);
            // Testa se a posição existe (linha > 0 && coluna > 0)
            if (linha > 0 && coluna > 0) {
                Peca pecaNoDestino = tabuleiro.getPeca(diagEsq);
                // Testa se há uma peça E se ela é inimiga
                if (pecaNoDestino != null && pecaNoDestino.getCor() == Cor.PRETA) {
                    movimentos.add(diagEsq);
                }
            }

            // 4. Captura Diagonal Direita
            Posicao diagDir = new Posicao(linha - 1, coluna + 1);
            // Testa se a posição existe (linha > 0 && coluna < 7)
            if (linha > 0 && coluna < 7) {
                Peca pecaNoDestino = tabuleiro.getPeca(diagDir);
                // Testa se há uma peça E se ela é inimiga
                if (pecaNoDestino != null && pecaNoDestino.getCor() == Cor.PRETA) {
                    movimentos.add(diagDir);
                }
            }

        } else {
            // --- MOVIMENTOS DO PEÃO PRETO (anda "para baixo", linha aumenta) ---

            // 1. Mover 1 casa para frente
            Posicao frenteUma = new Posicao(linha + 1, coluna);
            // Testa se a posição existe (linha < 7) e se está vaga
            if (linha < 7 && tabuleiro.getPeca(frenteUma) == null) {
                movimentos.add(frenteUma);

                // 2. Mover 2 casas (só na primeira jogada)
                // Testa se está na linha inicial (1) e se a casa 2 à frente está vaga
                Posicao frenteDuas = new Posicao(linha + 2, coluna);
                if (linha == 1 && tabuleiro.getPeca(frenteDuas) == null) {
                    movimentos.add(frenteDuas);
                }
            }

            // 3. Captura Diagonal Esquerda
            Posicao diagEsq = new Posicao(linha + 1, coluna - 1);
            // Testa se a posição existe (linha < 7 && coluna > 0)
            if (linha < 7 && coluna > 0) {
                Peca pecaNoDestino = tabuleiro.getPeca(diagEsq);
                // Testa se há uma peça E se ela é inimiga
                if (pecaNoDestino != null && pecaNoDestino.getCor() == Cor.BRANCA) {
                    movimentos.add(diagEsq);
                }
            }

            // 4. Captura Diagonal Direita
            Posicao diagDir = new Posicao(linha + 1, coluna + 1);
            // Testa se a posição existe (linha < 7 && coluna < 7)
            if (linha < 7 && coluna < 7) {
                Peca pecaNoDestino = tabuleiro.getPeca(diagDir);
                // Testa se há uma peça E se ela é inimiga
                if (pecaNoDestino != null && pecaNoDestino.getCor() == Cor.BRANCA) {
                    movimentos.add(diagDir);
                }
            }
        }

        return movimentos;
    }
}
