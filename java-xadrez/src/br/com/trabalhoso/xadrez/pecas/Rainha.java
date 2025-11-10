package br.com.trabalhoso.xadrez.pecas;

import br.com.trabalhoso.xadrez.partida.Posicao;
import br.com.trabalhoso.xadrez.partida.Tabuleiro;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação da peça Rainha.
 * Seu movimento é a combinação dos movimentos da Torre e do Bispo.
 */
public class Rainha extends Peca {

    public Rainha(Cor cor) {
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

        // --- LÓGICA DA TORRE (MOVIMENTOS RETOS) ---

        // 1. Olhar para CIMA
        for (int i = linha - 1; i >= 0; i--) {
            Posicao proximaPosicao = new Posicao(i, coluna);
            if (!avaliarCasa(tabuleiro, proximaPosicao, movimentos)) {
                break; // Se a casa bloqueou, para o loop
            }
        }

        // 2. Olhar para BAIXO
        for (int i = linha + 1; i <= 7; i++) {
            Posicao proximaPosicao = new Posicao(i, coluna);
            if (!avaliarCasa(tabuleiro, proximaPosicao, movimentos)) {
                break;
            }
        }

        // 3. Olhar para a ESQUERDA
        for (int j = coluna - 1; j >= 0; j--) {
            Posicao proximaPosicao = new Posicao(linha, j);
            if (!avaliarCasa(tabuleiro, proximaPosicao, movimentos)) {
                break;
            }
        }

        // 4. Olhar para a DIREITA
        for (int j = coluna + 1; j <= 7; j++) {
            Posicao proximaPosicao = new Posicao(linha, j);
            if (!avaliarCasa(tabuleiro, proximaPosicao, movimentos)) {
                break;
            }
        }

        // --- LÓGICA DO BISPO (MOVIMENTOS DIAGONAIS) ---

        // 5. Diagonal Cima-Esquerda
        for (int i = linha - 1, j = coluna - 1; i >= 0 && j >= 0; i--, j--) {
            Posicao proximaPosicao = new Posicao(i, j);
            if (!avaliarCasa(tabuleiro, proximaPosicao, movimentos)) {
                break;
            }
        }

        // 6. Diagonal Cima-Direita
        for (int i = linha - 1, j = coluna + 1; i >= 0 && j <= 7; i--, j++) {
            Posicao proximaPosicao = new Posicao(i, j);
            if (!avaliarCasa(tabuleiro, proximaPosicao, movimentos)) {
                break;
            }
        }

        // 7. Diagonal Baixo-Esquerda
        for (int i = linha + 1, j = coluna - 1; i <= 7 && j >= 0; i++, j--) {
            Posicao proximaPosicao = new Posicao(i, j);
            if (!avaliarCasa(tabuleiro, proximaPosicao, movimentos)) {
                break;
            }
        }

        // 8. Diagonal Baixo-Direita
        for (int i = linha + 1, j = coluna + 1; i <= 7 && j <= 7; i++, j++) {
            Posicao proximaPosicao = new Posicao(i, j);
            if (!avaliarCasa(tabuleiro, proximaPosicao, movimentos)) {
                break;
            }
        }

        return movimentos;
    }

    /**
     * Metodo auxiliar para avaliar uma casa e adicionar à lista de movimentos.
     * @return Retorna 'true' se o caminho estiver livre, 'false' se estiver bloqueado.
     */
    private boolean avaliarCasa(Tabuleiro tabuleiro, Posicao posicao, List<Posicao> movimentos) {
        Peca pecaNoCaminho = tabuleiro.getPeca(posicao);

        if (pecaNoCaminho == null) { // A casa está vazia?
            movimentos.add(posicao);
            return true;
        } else if (pecaNoCaminho.getCor() != this.getCor()) { // É uma peça inimiga?
            movimentos.add(posicao); // Adiciona como captura
            return false; // Para, pois a peça bloqueia
        } else { // É uma peça amiga
            return false; // Para, pois a peça bloqueia
        }
    }
}