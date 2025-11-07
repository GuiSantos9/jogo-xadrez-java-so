package br.com.trabalhoso.xadrez.partida;

// Certifique-se de que estas importações estão corretas para o seu projeto
import br.com.trabalhoso.xadrez.pecas.Cor;
import br.com.trabalhoso.xadrez.pecas.Peca;
import br.com.trabalhoso.xadrez.pecas.Rei; // Importação necessária para o Tabuleiro

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
        this.tabuleiro = new Tabuleiro(); // O Tabuleiro já se inicia com as peças
        this.jogadorAtual = Cor.BRANCA;  // Brancas sempre começam
        this.partidaEmAndamento = true;
    }

    // --- Getters (para a Interface Gráfica poder ler o estado) ---

    public Tabuleiro getTabuleiro() {
        return this.tabuleiro;
    }

    public Cor getJogadorAtual() {
        return this.jogadorAtual;
    }

    public boolean isPartidaEmAndamento() {
        return this.partidaEmAndamento;
    }

    public Cor getVencedor() {
        return this.vencedor;
    }

    // --- Lógica Principal do Jogo ---

    /**
     * Tenta realizar uma jogada no tabuleiro.
     * Contém toda a lógica de validação, incluindo a verificação de cheque.
     * Retorna 'true' se a jogada foi bem-sucedida, 'false' se foi ilegal.
     */
    public boolean realizarJogada(Posicao origem, Posicao destino) {

        // --- ADIÇÃO 1: Impede jogadas se a partida já acabou ---
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

        // --- 3. Validação de Cheque (A NOVA LÓGICA!) ---
        Peca pecaCapturada = tabuleiro.simularMovimento(origem, destino);
        boolean ficariaEmCheque = estaEmCheque(this.jogadorAtual);
        tabuleiro.desfazerMovimento(origem, destino, pecaCapturada);
        if (ficariaEmCheque) {
            System.out.println("Erro: Este movimento deixa o seu rei em cheque!");
            return false;
        }

        // --- 4. Execução da Jogada (se passou em todas as validações) ---
        tabuleiro.moverPeca(origem, destino);
        trocarTurno();

        // --- 5. Verificação de Cheque e XEQUE-MATE (ADIÇÃO 2) ---
        if (estaEmCheque(this.jogadorAtual)) {
            System.out.println("CHEQUE!");

            // Chama a verificação de xeque-mate
            if (verificarChequeMate(this.jogadorAtual)) {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("!!!   XEQUE-MATE    !!!");
                System.out.println("Vencedor: " + getCorOponente(this.jogadorAtual));
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");

                // Trava o jogo
                this.partidaEmAndamento = false;
            }
        }

        // --- 5. Verificação de Cheque e XEQUE-MATE ---
        if (estaEmCheque(this.jogadorAtual)) {
            System.out.println("CHEQUE!");

            if (verificarChequeMate(this.jogadorAtual)) {
                // --- MODIFICAÇÃO AQUI ---
                System.out.println("XEQUE-MATE!");
                this.vencedor = getCorOponente(this.jogadorAtual); // Define o vencedor
                this.partidaEmAndamento = false; // Trava o jogo
                // --- FIM DA MODIFICAÇÃO ---
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
     * @param corDoRei A cor do Rei a ser verificado.
     * @return true se o Rei estiver em cheque, false caso contrário.
     */
    public boolean estaEmCheque(Cor corDoRei) {
        // 1. Encontrar a posição do Rei que queremos verificar
        Posicao posRei = tabuleiro.getPosicaoDoRei(corDoRei);
        if (posRei == null) {
            return false;
        }

        // 2. Pegar a cor do oponente
        Cor corOponente = getCorOponente(corDoRei);

        // 3. Pegar TODAS as peças do oponente
        List<Peca> pecasOponentes = tabuleiro.getTodasPecas(corOponente);

        // 4. Iterar sobre cada peça oponente
        for (Peca oponente : pecasOponentes) {

            // 5. Calcular TODOS os movimentos possíveis dessa peça oponente
            List<Posicao> movimentosPossiveis = oponente.calcularMovimentosPossiveis(tabuleiro);

            // 6. Verificar se algum desses movimentos "cai" na casa do Rei
            for (Posicao mov : movimentosPossiveis) {
                if (mov.getLinha() == posRei.getLinha() && mov.getColuna() == posRei.getColuna()) {
                    return true;
                }
            }
        }

        // 7. Se checamos todas as peças e nenhum movimento ataca o rei...
        return false;
    }

    /**
     * Verifica se o jogador de uma determinada cor está em Xeque-Mate.
     * @param corDoJogador A cor do jogador a ser verificado.
     * @return true se for xeque-mate, false caso contrário.
     */
    private boolean verificarChequeMate(Cor corDoJogador) {

        // 1. A primeira regra: não é xeque-mate se o jogador NÃO ESTÁ em cheque.
        if (!estaEmCheque(corDoJogador)) {
            return false;
        }

        // 2. Pega todas as peças do jogador que está em cheque.
        List<Peca> todasMinhasPecas = tabuleiro.getTodasPecas(corDoJogador);

        // 3. Itera sobre cada peça desse jogador.
        for (Peca peca : todasMinhasPecas) {

            // 4. Pega todos os movimentos "brutos" (potenciais) dessa peça.
            Posicao origem = tabuleiro.getPosicaoDaPeca(peca);
            List<Posicao> movimentosPotenciais = peca.calcularMovimentosPossiveis(tabuleiro);

            // 5. Itera sobre cada movimento potencial.
            for (Posicao destino : movimentosPotenciais) {

                // 6. SIMULA a jogada:
                Peca pecaCapturada = tabuleiro.simularMovimento(origem, destino);

                // 7. VERIFICA se o rei AINDA está em cheque.
                boolean aindaEmCheque = estaEmCheque(corDoJogador);

                // 8. DESFAZ a simulação.
                tabuleiro.desfazerMovimento(origem, destino, pecaCapturada);

                // 9. A JOGADA DE FUGA!
                if (!aindaEmCheque) {
                    // Encontramos pelo menos UMA jogada que tira o rei do cheque.
                    // Portanto, NÃO é xeque-mate.
                    return false;
                }
            }
        }

        // 10. Se o loop terminar (testou todas as peças e todos os movimentos)
        // e NENHUMA jogada de fuga foi encontrada...
        // ...então é XEQUE-MATE.
        return true;
    }

}