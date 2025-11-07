package br.com.trabalhoso.xadrez.partida;
import br.com.trabalhoso.xadrez.pecas.*;

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
        colocarPeca(new Cavalo(Cor.BRANCA), new Posicao(0, 1));
        colocarPeca(new Bispo(Cor.BRANCA), new Posicao(0, 2));
        colocarPeca(new Rainha(Cor.BRANCA), new Posicao(0, 3));
        colocarPeca(new Rei(Cor.BRANCA),new Posicao(0, 4));
        colocarPeca(new Bispo(Cor.BRANCA), new Posicao(0, 5));
        colocarPeca(new Cavalo(Cor.BRANCA), new Posicao(0, 6));
        colocarPeca(new Torre(Cor.BRANCA), new Posicao(0, 7));

        // Linha 1 (Peões brancos)
        for (int j = 0; j < 8; j++) {
            colocarPeca(new Peao(Cor.BRANCA), new Posicao(1, j));
        }

        // --- PEÇAS PRETAS (Linhas 6 e 7) ---

        // Linha 6 (Peões pretos)
        for (int j = 0; j < 8; j++) {
            colocarPeca(new Peao(Cor.PRETA), new Posicao(6, j));
        }

        // Linha 7 (Peças "nobres" pretas)
        colocarPeca(new Torre(Cor.PRETA), new Posicao(7, 0));
        colocarPeca(new Cavalo(Cor.PRETA), new Posicao(7, 1));
        colocarPeca(new Bispo(Cor.PRETA), new Posicao(7, 2));
        colocarPeca(new Rainha(Cor.PRETA), new Posicao(7, 3));
        colocarPeca(new Rei(Cor.PRETA),new Posicao(7, 4));
        colocarPeca(new Bispo(Cor.PRETA), new Posicao(7, 5));
        colocarPeca(new Cavalo(Cor.PRETA), new Posicao(7, 6));
        colocarPeca(new Torre(Cor.PRETA), new Posicao(7, 7));

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

    /**
     * Encontra e retorna a posição atual de uma peça específica no tabuleiro.
     * Percorre a matriz 8x8 procurando pelo objeto da peça.
     * @param peca A peça que você quer encontrar.
     * @return A Posicao da peça, ou null se a peça não for encontrada.
     */
    public Posicao getPosicaoDaPeca(Peca peca) {
        // Percorre todas as linhas (i)
        for (int i = 0; i < 8; i++) {
            // Percorre todas as colunas (j) da linha atual
            for (int j = 0; j < 8; j++) {
                // Se a peça na matriz [i][j] for EXATAMENTE a mesma peça
                // que estamos procurando...
                if (pecas[i][j] == peca) {
                    // ... encontramos! Retorna uma nova Posicao com essas coordenadas.
                    return new Posicao(i, j);
                }
            }
        }
        // Se o loop terminar e não encontrarmos a peça, retorna null.
        return null;
    }

    /**
     * Encontra e retorna uma lista de todas as peças de uma determinada cor.
     * @param cor A cor das peças a procurar.
     * @return Uma List<Peca> contendo todas as peças ativas daquela cor.
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
     * @param cor A cor do Rei a procurar.
     * @return A Posicao do Rei, ou null se não for encontrado.
     */
    public Posicao getPosicaoDoRei(Cor cor) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca peca = pecas[i][j];
                // Verifica se a peça é uma instância da classe Rei E tem a cor correta
                if (peca instanceof Rei && peca.getCor() == cor) {
                    return new Posicao(i, j);
                }
            }
        }
        return null; // Não deve acontecer em um jogo normal
    }

    /**
     * Move uma peça temporariamente para simulação e retorna a peça que foi capturada.
     * @param origem A posição de origem.
     * @param destino A posição de destino.
     * @return A peça que estava na posição de destino (pode ser null).
     */
    public Peca simularMovimento(Posicao origem, Posicao destino) {
        Peca pecaMovida = getPeca(origem);
        Peca pecaCapturada = getPeca(destino); // Salva a peça que está no destino

        // Realiza o movimento na matriz
        pecas[origem.getLinha()][origem.getColuna()] = null;
        pecas[destino.getLinha()][destino.getColuna()] = pecaMovida;

        return pecaCapturada; // Retorna a peça que foi capturada
    }

    /**
     * Desfaz um movimento de simulação, colocando tudo de volta no lugar.
     * @param origem A posição de origem original.
     * @param destino A posição de destino original.
     * @param pecaCapturada A peça que foi capturada (pode ser null).
     */
    public void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
        Peca pecaMovida = getPeca(destino); // Pega a peça que foi movida

        // Coloca a peça movida de volta na origem
        pecas[origem.getLinha()][origem.getColuna()] = pecaMovida;

        // Coloca a peça capturada de volta no destino
        pecas[destino.getLinha()][destino.getColuna()] = pecaCapturada;
    }

}
