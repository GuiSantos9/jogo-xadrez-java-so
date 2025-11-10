package br.com.trabalhoso.xadrez.pecas;

import br.com.trabalhoso.xadrez.partida.Posicao;
import br.com.trabalhoso.xadrez.partida.Tabuleiro;
import java.util.ArrayList;
import java.util.List;

// Herda as características da Peça
public class Torre extends Peca {

    public Torre(Cor cor) {
        super(cor); // Chama o construtor da superclasse Peca
    }

    @Override
    // --- CORREÇÃO AQUI: A assinatura agora bate com a classe Peca ---
    public List<Posicao> calcularMovimentosPossiveis(Tabuleiro tabuleiro) {
        List<Posicao> movimentos = new ArrayList<>();

        // --- LINHAS ADICIONADAS ---
        // 1. Pergunta ao tabuleiro onde esta peça (this) está.
        Posicao minhaPosicao = tabuleiro.getPosicaoDaPeca(this);

        // 2. Verificação de segurança (impede erro se a peça não for encontrada)
        if (minhaPosicao == null) {
            return movimentos;
        }
        // --- FIM DA CORREÇÃO ---

        int linha = minhaPosicao.getLinha();
        int coluna = minhaPosicao.getColuna();

        // 1. Olhar para CIMA
        for (int i = linha - 1; i >= 0; i--) {
            Posicao proximaPosicao = new Posicao(i, coluna);
            Peca pecaNoCaminho = tabuleiro.getPeca(proximaPosicao);

            if (pecaNoCaminho == null) { // A casa está vazia?
                movimentos.add(proximaPosicao);
            } else if (pecaNoCaminho.getCor() != this.getCor()) { // É uma peça inimiga?
                movimentos.add(proximaPosicao); // Adiciona como captura
                break; // Para o loop, pois a torre é bloqueada
            } else { // É uma peça amiga
                break; // Para o loop, pois a torre é bloqueada
            }
        }

        // 2. Olhar para BAIXO
        for (int i = linha + 1; i <= 7; i++) {
            Posicao proximaPosicao = new Posicao(i, coluna);
            Peca pecaNoCaminho = tabuleiro.getPeca(proximaPosicao);

            if (pecaNoCaminho == null) {
                movimentos.add(proximaPosicao);
            } else if (pecaNoCaminho.getCor() != this.getCor()) {
                movimentos.add(proximaPosicao);
                break;
            } else {
                break;
            }
        }

        // 3. Olhar para a ESQUERDA
        for (int j = coluna - 1; j >= 0; j--) {
            Posicao proximaPosicao = new Posicao(linha, j);
            Peca pecaNoCaminho = tabuleiro.getPeca(proximaPosicao);

            if (pecaNoCaminho == null) {
                movimentos.add(proximaPosicao);
            } else if (pecaNoCaminho.getCor() != this.getCor()) {
                movimentos.add(proximaPosicao);
                break;
            } else {
                break;
            }
        }

        // 4. Olhar para a DIREITA
        for (int j = coluna + 1; j <= 7; j++) {
            Posicao proximaPosicao = new Posicao(linha, j);
            Peca pecaNoCaminho = tabuleiro.getPeca(proximaPosicao);

            if (pecaNoCaminho == null) {
                movimentos.add(proximaPosicao);
            } else if (pecaNoCaminho.getCor() != this.getCor()) {
                movimentos.add(proximaPosicao);
                break;
            } else {
                break;
            }
        }

        return movimentos;
    }
}