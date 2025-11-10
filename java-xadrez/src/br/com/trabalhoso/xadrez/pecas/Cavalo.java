package br.com.trabalhoso.xadrez.pecas;

import br.com.trabalhoso.xadrez.partida.Posicao;
import br.com.trabalhoso.xadrez.partida.Tabuleiro;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação da peça Cavalo.
 * O Cavalo se move em "L" e pode pular sobre outras peças.
 */
public class Cavalo extends Peca {

    public Cavalo(Cor cor) {
        super(cor);
    }

    @Override
    public List<Posicao> calcularMovimentosPossiveis(Tabuleiro tabuleiro) {
        List<Posicao> movimentos = new ArrayList<>();

        Posicao minhaPosicao = tabuleiro.getPosicaoDaPeca(this);

        // Verificação de segurança, caso a peça não esteja no tabuleiro
        if (minhaPosicao == null) {
            return movimentos;
        }

        int linha = minhaPosicao.getLinha();
        int coluna = minhaPosicao.getColuna();

        // --- LÓGICA DE MOVIMENTO ---

        // O Cavalo tem 8 movimentos fixos em "L".
        // Vamos criar um array com todas as posições de destino possíveis.
        Posicao[] possiveisDestinos = {
                new Posicao(linha - 2, coluna - 1), // Cima-Esquerda
                new Posicao(linha - 2, coluna + 1), // Cima-Direita
                new Posicao(linha + 2, coluna - 1), // Baixo-Esquerda
                new Posicao(linha + 2, coluna + 1), // Baixo-Direita
                new Posicao(linha - 1, coluna - 2), // Esquerda-Cima
                new Posicao(linha - 1, coluna + 2), // Direita-Cima
                new Posicao(linha + 1, coluna - 2), // Esquerda-Baixo
                new Posicao(linha + 1, coluna + 2)  // Direita-Baixo
        };

        // Agora, validamos cada um desses 8 destinos
        for (Posicao destino : possiveisDestinos) {

            // 1. O destino está DENTRO do tabuleiro?
            // (Verifica se linha e coluna estão entre 0 e 7)
            if (destino.getLinha() >= 0 && destino.getLinha() <= 7 &&
                    destino.getColuna() >= 0 && destino.getColuna() <= 7) {

                // 2. Se está no tabuleiro, verificamos a peça que está lá
                Peca pecaNoDestino = tabuleiro.getPeca(destino);

                // Se a casa está VAZIA (null) ou contém uma peça INIMIGA,
                // o movimento é válido.
                if (pecaNoDestino == null || pecaNoDestino.getCor() != this.getCor()) {
                    movimentos.add(destino);
                }

                // Se a casa tiver uma peça AMIGA, não fazemos nada (movimento inválido).
            }
        }

        return movimentos;
    }
}