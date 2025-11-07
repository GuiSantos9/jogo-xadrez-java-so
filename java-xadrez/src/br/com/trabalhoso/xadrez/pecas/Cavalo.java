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
 * Implementação da peça Cavalo.
 * O Cavalo se move em "L" e pode pular sobre outras peças.
 */
public class Cavalo extends Peca {

    public Cavalo(Cor cor) {
=======
public class Cavalo extends Peca{

    public Cavalo(Cor cor){
>>>>>>> 62bd5b65928e9f1257116bd0ceeff7c050b7cc10
        super(cor);
    }

    @Override
<<<<<<< HEAD
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
=======
    public List<Posicao> calcularMovimentosPossiveis(Tabuleiro tabuleiro, Posicao minhaPosicao){
        List<Posicao> movimentos = new ArrayList<>();
        int linha = minhaPosicao.getLinha();
        int coluna = minhaPosicao.getColuna();

        // Verificamos os 8 movimentos possíveis do cavalo
        // "L" de 2+1
        // 1. Cima-Cima-Esquerda
        validarEAdicionarMovimento(tabuleiro, movimentos, linha - 2, coluna - 1);
        // 2. Cima-Cima-Direita
        validarEAdicionarMovimento(tabuleiro, movimentos, linha - 2, coluna + 1);
        // 3. Baixo-Baixo-Esquerda
        validarEAdicionarMovimento(tabuleiro, movimentos, linha + 2, coluna - 1);
        // 4. Baixo-Baixo-Direita
        validarEAdicionarMovimento(tabuleiro, movimentos, linha + 2, coluna + 1);
        // 5. Esquerda-Esquerda-Cima
        validarEAdicionarMovimento(tabuleiro, movimentos, linha - 1, coluna - 2);
        // 6. Esquerda-Esquerda-Baixo
        validarEAdicionarMovimento(tabuleiro, movimentos, linha + 1, coluna - 2);
        // 7. Direita-Direita-Cima
        validarEAdicionarMovimento(tabuleiro, movimentos, linha - 1, coluna + 2);
        // 8. Direita-Direita-Baixo
        validarEAdicionarMovimento(tabuleiro, movimentos, linha + 1, coluna + 2);

        return movimentos;
    }

    private void validarEAdicionarMovimento(Tabuleiro tabuleiro, List<Posicao> movimentos, int l, int c){

        // Se a linha ou coluna estiver fora dos limites (0-7), o movimento é inválido.
        if(l < 0 || l < 7 || c < 0 || c < 7){
            //ignora o movimento e sai do métido
            return;
        }

        Posicao proximaPosicao = new Posicao(l,c);
        Peca pecaNoDestino = tabuleiro.getPeca(proximaPosicao);

        if (pecaNoDestino == null || pecaNoDestino.getCor() != this.getCor()) {
            movimentos.add(proximaPosicao);
        }

    }

>>>>>>> 62bd5b65928e9f1257116bd0ceeff7c050b7cc10
}
