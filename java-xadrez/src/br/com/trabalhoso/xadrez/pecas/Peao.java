package br.com.trabalhoso.xadrez.pecas;

//Herda as características da Peça
import br.com.trabalhoso.xadrez.partida.Posicao;
import br.com.trabalhoso.xadrez.partida.Tabuleiro;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação da peça Peão.
 * Sua lógica de movimento é a mais complexa, pois depende da cor,
 * captura na diagonal e se move para frente.
 */
public class Peao extends Peca {

    public Peao(Cor cor) {
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

        if (this.getCor() == Cor.BRANCA) {
            // --- PEÃO BRANCO (move-se "para baixo" no array, linha + 1) ---

            // 1. Movimento de uma casa para frente
            Posicao posFrente = new Posicao(linha + 1, coluna);
            if (posFrente.getLinha() <= 7 && tabuleiro.getPeca(posFrente) == null) {
                movimentos.add(posFrente);

                // 2. Movimento de duas casas (só se estiver na linha inicial E a 1ª casa estiver livre)
                // (Assumindo que os peões brancos começam na linha 1)
                if (linha == 1) {
                    Posicao posFrenteDupla = new Posicao(linha + 2, coluna);
                    if (tabuleiro.getPeca(posFrenteDupla) == null) {
                        movimentos.add(posFrenteDupla);
                    }
                }
            }

            // 3. Captura Diagonal Esquerda
            if (coluna - 1 >= 0) { // Verifica se está dentro do tabuleiro
                Posicao posDiagEsq = new Posicao(linha + 1, coluna - 1);
                Peca pecaInimiga = tabuleiro.getPeca(posDiagEsq);
                if (pecaInimiga != null && pecaInimiga.getCor() == Cor.PRETA) {
                    movimentos.add(posDiagEsq);
                }
            }

            // 4. Captura Diagonal Direita
            if (coluna + 1 <= 7) { // Verifica se está dentro do tabuleiro
                Posicao posDiagDir = new Posicao(linha + 1, coluna + 1);
                Peca pecaInimiga = tabuleiro.getPeca(posDiagDir);
                if (pecaInimiga != null && pecaInimiga.getCor() == Cor.PRETA) {
                    movimentos.add(posDiagDir);
                }
            }

        } else {
            // --- PEÃO PRETO (move-se "para cima" no array, linha - 1) ---

            // 1. Movimento de uma casa para frente
            Posicao posFrente = new Posicao(linha - 1, coluna);
            if (posFrente.getLinha() >= 0 && tabuleiro.getPeca(posFrente) == null) {
                movimentos.add(posFrente);

                // 2. Movimento de duas casas (só se estiver na linha inicial E a 1ª casa estiver livre)
                // (Assumindo que os peões pretos começam na linha 6)
                if (linha == 6) {
                    Posicao posFrenteDupla = new Posicao(linha - 2, coluna);
                    if (tabuleiro.getPeca(posFrenteDupla) == null) {
                        movimentos.add(posFrenteDupla);
                    }
                }
            }

            // 3. Captura Diagonal Esquerda
            if (coluna - 1 >= 0) { // Verifica se está dentro do tabuleiro
                Posicao posDiagEsq = new Posicao(linha - 1, coluna - 1);
                Peca pecaInimiga = tabuleiro.getPeca(posDiagEsq);
                if (pecaInimiga != null && pecaInimiga.getCor() == Cor.BRANCA) {
                    movimentos.add(posDiagEsq);
                }
            }

            // 4. Captura Diagonal Direita
            if (coluna + 1 <= 7) { // Verifica se está dentro do tabuleiro
                Posicao posDiagDir = new Posicao(linha - 1, coluna + 1);
                Peca pecaInimiga = tabuleiro.getPeca(posDiagDir);
                if (pecaInimiga != null && pecaInimiga.getCor() == Cor.BRANCA) {
                    movimentos.add(posDiagDir);
                }
            }
        }

        return movimentos;
    }
}