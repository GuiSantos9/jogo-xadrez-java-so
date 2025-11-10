package br.com.trabalhoso.xadrez.partida;

// Certifique-se de que estas importações estão corretas para o seu projeto
import br.com.trabalhoso.xadrez.pecas.Cor;
import br.com.trabalhoso.xadrez.pecas.Peca;
import br.com.trabalhoso.xadrez.pecas.Rei;
import java.util.ArrayList;
import java.util.List;

public class PartidaXadrez {

    private Tabuleiro tabuleiro;
    private Cor jogadorAtual;
    private boolean partidaEmAndamento;
    private Cor vencedor;

    /**
     * Construtor da Partida.
     * Inicializa o tabuleiro e define o primeiro jogador.
     */
    public PartidaXadrez() {
        this.tabuleiro = new Tabuleiro();
        this.jogadorAtual = Cor.BRANCA;
        this.partidaEmAndamento = true;
    }

    // --- Getters ---
    public Tabuleiro getTabuleiro() { return this.tabuleiro; }
    public Cor getJogadorAtual() { return this.jogadorAtual; }
    public boolean isPartidaEmAndamento() { return this.partidaEmAndamento; }
    public Cor getVencedor() { return this.vencedor; }

    // --- Lógica Principal do Jogo ---

    /**
     * Tenta realizar uma jogada no tabuleiro. (Versão FINAL CORRIGIDA)
     */
    public boolean realizarJogada(Posicao origem, Posicao destino) {

        if (!this.partidaEmAndamento) {
            System.out.println("A partida já terminou!");
            return false;
        }

        // --- 1. Validação da Origem ---
        Peca pecaOrigem = tabuleiro.getPeca(origem);
        if (pecaOrigem == null) {
            System.out.println("Erro: Não há peça na posição de origem.");
            return false;
        }
        if (pecaOrigem.getCor() != this.jogadorAtual) {
            System.out.println("Erro: Esta peça não é sua.");
            return false;
        }

        // --- 2. Validação do Destino ---
        List<Posicao> movimentosLegais = pecaOrigem.calcularMovimentosPossiveis(tabuleiro);
        if (!validarDestinoNaLista(movimentosLegais, destino)) {
            System.out.println("Erro: Movimento ilegal para esta peça.");
            return false;
        }

        // --- 3. Validação de Cheque (Simulação) ---
        Peca pecaCapturada = tabuleiro.simularMovimento(origem, destino);
        boolean ficariaEmCheque = estaEmCheque(this.jogadorAtual);
        tabuleiro.desfazerMovimento(origem, destino, pecaCapturada);
        if (ficariaEmCheque) {
            System.out.println("Erro: Este movimento deixa o seu rei em cheque!");
            return false;
        }

        // --- 4. Execução da Jogada ---
        tabuleiro.moverPeca(origem, destino);
        trocarTurno();

        // --- 5. Verificação de Cheque e XEQUE-MATE ---
        if (estaEmCheque(this.jogadorAtual)) {
            System.out.println("CHEQUE!");

            if (verificarChequeMate(this.jogadorAtual)) {
                System.out.println("XEQUE-MATE!");
                this.vencedor = getCorOponente(this.jogadorAtual); // Define o vencedor
                this.partidaEmAndamento = false; // Trava o jogo
            }
        }

        return true; // Jogada realizada com sucesso
    }

    /**
     * Troca o turno do jogador.
     */
    private void trocarTurno() {
        if (this.jogadorAtual == Cor.BRANCA) {
            this.jogadorAtual = Cor.PRETA;
        } else {
            this.jogadorAtual = Cor.BRANCA;
        }
    }

    /**
     * Metodo auxiliar para verificar se o destino está na lista de movimentos.
     */
    private boolean validarDestinoNaLista(List<Posicao> movimentos, Posicao destino) {
        for (Posicao p : movimentos) {
            if (p.getLinha() == destino.getLinha() && p.getColuna() == destino.getColuna()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo auxiliar para obter a cor do oponente.
     */
    private Cor getCorOponente(Cor cor) {
        return (cor == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
    }

    /**
     * Verifica se o Rei de uma determinada cor está em cheque.
     */
    public boolean estaEmCheque(Cor corDoRei) {
        Posicao posRei = tabuleiro.getPosicaoDoRei(corDoRei);
        if (posRei == null) {
            return false;
        }
        Cor corOponente = getCorOponente(corDoRei);
        List<Peca> pecasOponentes = tabuleiro.getTodasPecas(corOponente);

        for (Peca oponente : pecasOponentes) {
            List<Posicao> movimentosPossiveis = oponente.calcularMovimentosPossiveis(tabuleiro);
            for (Posicao mov : movimentosPossiveis) {
                if (mov.getLinha() == posRei.getLinha() && mov.getColuna() == posRei.getColuna()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Verifica se o jogador de uma determinada cor está em Xeque-Mate.
     */
    private boolean verificarChequeMate(Cor corDoJogador) {

        if (!estaEmCheque(corDoJogador)) {
            return false;
        }

        List<Peca> todasMinhasPecas = tabuleiro.getTodasPecas(corDoJogador);

        for (Peca peca : todasMinhasPecas) {
            Posicao origem = tabuleiro.getPosicaoDaPeca(peca);
            // Evita NullPointerException se a peça não for encontrada (improvável)
            if (origem == null) continue;

            List<Posicao> movimentosPotenciais = peca.calcularMovimentosPossiveis(tabuleiro);

            for (Posicao destino : movimentosPotenciais) {
                Peca pecaCapturada = tabuleiro.simularMovimento(origem, destino);
                boolean aindaEmCheque = estaEmCheque(corDoJogador);
                tabuleiro.desfazerMovimento(origem, destino, pecaCapturada);

                if (!aindaEmCheque) {
                    return false; // Encontrou uma jogada de fuga
                }
            }
        }
        return true; // Nenhuma jogada de fuga encontrada
    }
}