package br.com.trabalhoso.xadrez.pecas;
import br.com.trabalhoso.xadrez.partida.Tabuleiro;

import java.util.ArrayList;
import java.util.List;

// Herda as características da Peça
public class Torre extends Peca {

    public Torre(Cor cor) {
        super(cor); // Chama o construtor da superclasse Peca
    }

    @Override
    public List<Posicao> calcularMovimentosPossiveis(Tabuleiro tabuleiro) {
        List<Posicao> movimentos = new ArrayList<>();

        // Primeiro, precisamos saber onde a torre está no tabuleiro.
        // Vamos assumir que existe um método no tabuleiro para nos dizer isso.
        Posicao minhaPosicao = tabuleiro.getPosicaoDaPeca(this);
        int linha = minhaPosicao.getLinha();
        int coluna = minhaPosicao.getColuna();

        // --- LÓGICA DE MOVIMENTO ---

        // 1. Olhar para CIMA
        // O loop começa na linha logo acima da torre e vai diminuindo até a borda (linha 0)
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
        // O loop começa na linha logo abaixo da torre e vai aumentando até a borda (linha 7)
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
        // O loop começa na coluna à esquerda e vai diminuindo até a borda (coluna 0)
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
        // O loop começa na coluna à direita e vai aumentando até a borda (coluna 7)
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
