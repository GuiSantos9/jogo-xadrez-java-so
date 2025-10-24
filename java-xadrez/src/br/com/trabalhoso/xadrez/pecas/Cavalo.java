package br.com.trabalhoso.xadrez.pecas;
import br.com.trabalhoso.xadrez.partida.Posicao;
import br.com.trabalhoso.xadrez.partida.Tabuleiro;
import java.util.ArrayList;
import java.util.List;

//Herda as características da Peça
public class Cavalo extends Peca{

    public Cavalo(Cor cor){
        super(cor);
    }

    @Override
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

}
