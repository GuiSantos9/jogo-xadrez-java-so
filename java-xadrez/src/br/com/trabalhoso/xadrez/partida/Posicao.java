package br.com.trabalhoso.xadrez.partida;

public class Posicao {
    private int linha;
    private int coluna;

    public Posicao(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
    }

    public int getLinha(){
        return this.linha;
    }

    public int getColuna(){
        return this.coluna;
    }

    @Override
    public String toString(){
        return "Posicao{" + "linha=" + linha + ", coluna=" + coluna + '}';
    }

}
