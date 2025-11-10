package br.com.trabalhoso.xadrez.pecas;

import br.com.trabalhoso.xadrez.partida.Posicao;
import br.com.trabalhoso.xadrez.partida.Tabuleiro;
import java.util.ArrayList;
import java.util.List;

// Herda as características da Peça
public class Bispo extends Peca {

    public Bispo(Cor cor) {
        super(cor); // Chama o construtor da superclasse Peca
    }

    @Override
    public List<Posicao> calcularMovimentosPossiveis(Tabuleiro tabuleiro) {
        List<Posicao> movimentos = new ArrayList<>();

        // --- LINHAS CORRIGIDAS ---
        // 1. Pergunta ao tabuleiro onde esta peça (this) está.
        Posicao minhaPosicao = tabuleiro.getPosicaoDaPeca(this);

        // 2. Verificação de segurança (impede erro se a peça não for encontrada)
        if (minhaPosicao == null) {
            return movimentos;
        }
        // --- FIM DA CORREÇÃO ---

        int linha = minhaPosicao.getLinha();
        int coluna = minhaPosicao.getColuna();


        // 1. Olhar para CIMA-ESQUERDA (Noroeste)
        for (int l = linha - 1, c = coluna - 1; l >= 0 && c >= 0; l--, c--) {
            Posicao proximaPosicao = new Posicao(l, c);
            Peca pecaNoCaminho = tabuleiro.getPeca(proximaPosicao);

            if (pecaNoCaminho == null) { // Casa vazia
                movimentos.add(proximaPosicao);
            } else if (pecaNoCaminho.getCor() != this.getCor()) { // Peça inimiga
                movimentos.add(proximaPosicao); // Adiciona captura
                break; // Para o loop, pois foi bloqueado
            } else { // Peça amiga
                break; // Para o loop, pois foi bloqueado
            }
        }

        // 2. Olhar para CIMA-DIREITA (Nordeste)
        for (int l = linha - 1, c = coluna + 1; l >= 0 && c <= 7; l--, c++) {
            Posicao proximaPosicao = new Posicao(l,c);
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

        // 3. Olhar para BAIXO-ESQUERDA (Sudoeste)
        for (int l = linha + 1, c = coluna - 1; l <= 7 && c >= 0; l++, c--) {
            Posicao proximaPosicao = new Posicao(l, c);
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

        // 4. Olhar para BAIXO-DIREITA (Sudeste)
        for (int l = linha + 1, c = coluna + 1; l <= 7 && c <= 7; l++, c++) {
            Posicao proximaPosicao = new Posicao(l, c);
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