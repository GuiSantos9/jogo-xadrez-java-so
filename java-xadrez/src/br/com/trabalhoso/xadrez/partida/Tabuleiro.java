package br.com.trabalhoso.xadrez.partida;

// Importações necessárias para TODAS as suas peças
import br.com.trabalhoso.xadrez.pecas.*;
import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    private Peca[][] pecas;

    public Tabuleiro() {
        this.pecas = new Peca[8][8];
        iniciarPartida();
    }

    /**
     * Preenche o tabuleiro com todas as peças na posição inicial.
     */
    public void iniciarPartida() {
        // --- PEÇAS BRANCAS (Linhas 0 e 1) ---
        colocarPeca(new Torre(Cor.BRANCA), new Posicao(0, 0));
        colocarPeca(new Cavalo(Cor.BRANCA), new Posicao(0, 1));
        colocarPeca(new Bispo(Cor.BRANCA), new Posicao(0, 2));
        colocarPeca(new Rainha(Cor.BRANCA), new Posicao(0, 3));
        colocarPeca(new Rei(Cor.BRANCA), new Posicao(0, 4));
        colocarPeca(new Bispo(Cor.BRANCA), new Posicao(0, 5));
        colocarPeca(new Cavalo(Cor.BRANCA), new Posicao(0, 6));
        colocarPeca(new Torre(Cor.BRANCA), new Posicao(0, 7));
        for (int j = 0; j < 8; j++) {
            colocarPeca(new Peao(Cor.BRANCA), new Posicao(1, j));
        }

        // --- PEÇAS PRETAS (Linhas 6 e 7) ---
        for (int j = 0; j < 8; j++) {
            colocarPeca(new Peao(Cor.PRETA), new Posicao(6, j));
        }
        colocarPeca(new Torre(Cor.PRETA), new Posicao(7, 0));
        colocarPeca(new Cavalo(Cor.PRETA), new Posicao(7, 1));
        colocarPeca(new Bispo(Cor.PRETA), new Posicao(7, 2));
        colocarPeca(new Rainha(Cor.PRETA), new Posicao(7, 3));
        colocarPeca(new Rei(Cor.PRETA), new Posicao(7, 4));
        colocarPeca(new Bispo(Cor.PRETA), new Posicao(7, 5));
        colocarPeca(new Cavalo(Cor.PRETA), new Posicao(7, 6));
        colocarPeca(new Torre(Cor.PRETA), new Posicao(7, 7));
    }

    /**
     * Coloca uma peça em uma posição específica.
     */
    public void colocarPeca(Peca peca, Posicao posicao) {
        this.pecas[posicao.getLinha()][posicao.getColuna()] = peca;
    }

    /**
     * Retorna a peça em uma dada posição.
     */
    public Peca getPeca(Posicao posicao) {
        return this.pecas[posicao.getLinha()][posicao.getColuna()];
    }

    /**
     * Move uma peça permanentemente.
     */
    public void moverPeca(Posicao origem, Posicao destino) {
        Peca pecaMovida = getPeca(origem);
        this.pecas[origem.getLinha()][origem.getColuna()] = null;
        this.pecas[destino.getLinha()][destino.getColuna()] = pecaMovida;
    }

    // --- MÉTODOS QUE ESTAVAM FALTANDO (CAUSA DOS ERROS) ---

    /**
     * Move uma peça temporariamente para simulação e retorna a peça que foi capturada.
     */
    public Peca simularMovimento(Posicao origem, Posicao destino) {
        Peca pecaMovida = getPeca(origem);
        Peca pecaCapturada = getPeca(destino);
        pecas[origem.getLinha()][origem.getColuna()] = null;
        pecas[destino.getLinha()][destino.getColuna()] = pecaMovida;
        return pecaCapturada;
    }

    /**
     * Desfaz um movimento de simulação.
     */
    public void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
        Peca pecaMovida = getPeca(destino);
        pecas[origem.getLinha()][origem.getColuna()] = pecaMovida;
        pecas[destino.getLinha()][destino.getColuna()] = pecaCapturada;
    }

    /**
     * Encontra e retorna a posição atual de uma peça específica no tabuleiro.
     */
    public Posicao getPosicaoDaPeca(Peca peca) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pecas[i][j] == peca) {
                    return new Posicao(i, j);
                }
            }
        }
        return null;
    }

    /**
     * Encontra e retorna uma lista de todas as peças de uma determinada cor.
     */
    public List<Peca> getTodasPecas(Cor cor) {
        List<Peca> listaDePecas = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca peca = pecas[i][j];
                if (peca != null && peca.getCor() == cor) {
                    listaDePecas.add(peca);
                }
            }
        }
        return listaDePecas;
    }

    /**
     * Encontra a posição do Rei de uma determinada cor.
     */
    public Posicao getPosicaoDoRei(Cor cor) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca peca = pecas[i][j];
                if (peca instanceof Rei && peca.getCor() == cor) {
                    return new Posicao(i, j);
                }
            }
        }
        return null;
    }
}