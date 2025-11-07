package br.com.trabalhoso.xadrez.pecas;

import br.com.trabalhoso.xadrez.partida.Posicao;
import br.com.trabalhoso.xadrez.partida.Tabuleiro;
import java.util.ArrayList;
import java.util.List;

//Herda as características da Peça
<<<<<<< HEAD
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
=======
public class Peao extends Peca{

    public Peao(Cor cor){
>>>>>>> 62bd5b65928e9f1257116bd0ceeff7c050b7cc10
        super(cor);
    }

    @Override
<<<<<<< HEAD
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
=======
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
>>>>>>> 62bd5b65928e9f1257116bd0ceeff7c050b7cc10
                }
            }

            // 3. Captura Diagonal Esquerda
<<<<<<< HEAD
            if (coluna - 1 >= 0) { // Verifica se está dentro do tabuleiro
                Posicao posDiagEsq = new Posicao(linha + 1, coluna - 1);
                Peca pecaInimiga = tabuleiro.getPeca(posDiagEsq);
                if (pecaInimiga != null && pecaInimiga.getCor() == Cor.PRETA) {
                    movimentos.add(posDiagEsq);
=======
            Posicao diagEsq = new Posicao(linha - 1, coluna - 1);
            // Testa se a posição existe (linha > 0 && coluna > 0)
            if (linha > 0 && coluna > 0) {
                Peca pecaNoDestino = tabuleiro.getPeca(diagEsq);
                // Testa se há uma peça E se ela é inimiga
                if (pecaNoDestino != null && pecaNoDestino.getCor() == Cor.PRETA) {
                    movimentos.add(diagEsq);
>>>>>>> 62bd5b65928e9f1257116bd0ceeff7c050b7cc10
                }
            }

            // 4. Captura Diagonal Direita
<<<<<<< HEAD
            if (coluna + 1 <= 7) { // Verifica se está dentro do tabuleiro
                Posicao posDiagDir = new Posicao(linha + 1, coluna + 1);
                Peca pecaInimiga = tabuleiro.getPeca(posDiagDir);
                if (pecaInimiga != null && pecaInimiga.getCor() == Cor.PRETA) {
                    movimentos.add(posDiagDir);
=======
            Posicao diagDir = new Posicao(linha - 1, coluna + 1);
            // Testa se a posição existe (linha > 0 && coluna < 7)
            if (linha > 0 && coluna < 7) {
                Peca pecaNoDestino = tabuleiro.getPeca(diagDir);
                // Testa se há uma peça E se ela é inimiga
                if (pecaNoDestino != null && pecaNoDestino.getCor() == Cor.PRETA) {
                    movimentos.add(diagDir);
>>>>>>> 62bd5b65928e9f1257116bd0ceeff7c050b7cc10
                }
            }

        } else {
<<<<<<< HEAD
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
=======
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
>>>>>>> 62bd5b65928e9f1257116bd0ceeff7c050b7cc10
                }
            }

            // 3. Captura Diagonal Esquerda
<<<<<<< HEAD
            if (coluna - 1 >= 0) { // Verifica se está dentro do tabuleiro
                Posicao posDiagEsq = new Posicao(linha - 1, coluna - 1);
                Peca pecaInimiga = tabuleiro.getPeca(posDiagEsq);
                if (pecaInimiga != null && pecaInimiga.getCor() == Cor.BRANCA) {
                    movimentos.add(posDiagEsq);
=======
            Posicao diagEsq = new Posicao(linha + 1, coluna - 1);
            // Testa se a posição existe (linha < 7 && coluna > 0)
            if (linha < 7 && coluna > 0) {
                Peca pecaNoDestino = tabuleiro.getPeca(diagEsq);
                // Testa se há uma peça E se ela é inimiga
                if (pecaNoDestino != null && pecaNoDestino.getCor() == Cor.BRANCA) {
                    movimentos.add(diagEsq);
>>>>>>> 62bd5b65928e9f1257116bd0ceeff7c050b7cc10
                }
            }

            // 4. Captura Diagonal Direita
<<<<<<< HEAD
            if (coluna + 1 <= 7) { // Verifica se está dentro do tabuleiro
                Posicao posDiagDir = new Posicao(linha - 1, coluna + 1);
                Peca pecaInimiga = tabuleiro.getPeca(posDiagDir);
                if (pecaInimiga != null && pecaInimiga.getCor() == Cor.BRANCA) {
                    movimentos.add(posDiagDir);
=======
            Posicao diagDir = new Posicao(linha + 1, coluna + 1);
            // Testa se a posição existe (linha < 7 && coluna < 7)
            if (linha < 7 && coluna < 7) {
                Peca pecaNoDestino = tabuleiro.getPeca(diagDir);
                // Testa se há uma peça E se ela é inimiga
                if (pecaNoDestino != null && pecaNoDestino.getCor() == Cor.BRANCA) {
                    movimentos.add(diagDir);
>>>>>>> 62bd5b65928e9f1257116bd0ceeff7c050b7cc10
                }
            }
        }

        return movimentos;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 62bd5b65928e9f1257116bd0ceeff7c050b7cc10
