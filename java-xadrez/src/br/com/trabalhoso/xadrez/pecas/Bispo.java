package br.com.trabalhoso.xadrez.pecas;

//Herda as características da Peça
import br.com.trabalhoso.xadrez.partida.Posicao;
import br.com.trabalhoso.xadrez.partida.Tabuleiro;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação da peça Bispo.
 * O Bispo se move em linhas diagonais.
 */
public class Bispo extends Peca {

    public Bispo(Cor cor) {
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

        // --- LÓGICA DE MOVIMENTO ---

        // Usaremos este método auxiliar para manter o código limpo
        // (Você pode colocar esse método dentro da classe Bispo,
        // ou até mesmo na classe Peca se quiser reutilizá-lo)

        // 1. Diagonal Cima-Esquerda
        verificarDiagonal(tabuleiro, movimentos, minhaPosicao, -1, -1);

        // 2. Diagonal Cima-Direita
        verificarDiagonal(tabuleiro, movimentos, minhaPosicao, -1, 1);

        // 3. Diagonal Baixo-Esquerda
        verificarDiagonal(tabuleiro, movimentos, minhaPosicao, 1, -1);

        // 4. Diagonal Baixo-Direita
        verificarDiagonal(tabuleiro, movimentos, minhaPosicao, 1, 1);

        return movimentos;
    }

    /**
     * Método auxiliar para verificar uma direção diagonal.
     * @param tabuleiro O tabuleiro do jogo.
     * @param movimentos A lista de movimentos válidos.
     * @param posInicial A posição inicial do bispo.
     * @param dirLinha A direção do movimento na linha (-1 para cima, 1 para baixo).
     * @param dirColuna A direção do movimento na coluna (-1 para esquerda, 1 para direita).
     */
    private void verificarDiagonal(Tabuleiro tabuleiro, List<Posicao> movimentos, Posicao posInicial, int dirLinha, int dirColuna) {
        int linha = posInicial.getLinha() + dirLinha;
        int coluna = posInicial.getColuna() + dirColuna;

        // Loop 'while' continua enquanto a posição estiver dentro do tabuleiro
        while (linha >= 0 && linha <= 7 && coluna >= 0 && coluna <= 7) {
            Posicao proximaPosicao = new Posicao(linha, coluna);
            Peca pecaNoCaminho = tabuleiro.getPeca(proximaPosicao);

            if (pecaNoCaminho == null) { // A casa está vazia?
                movimentos.add(proximaPosicao);
            } else if (pecaNoCaminho.getCor() != this.getCor()) { // É uma peça inimiga?
                movimentos.add(proximaPosicao); // Adiciona como captura
                break; // Para o loop, pois o bispo é bloqueado
            } else { // É uma peça amiga
                break; // Para o loop, pois o bispo é bloqueado
            }

            // Vai para a próxima casa na mesma diagonal
            linha += dirLinha;
            coluna += dirColuna;
        }
    }
}