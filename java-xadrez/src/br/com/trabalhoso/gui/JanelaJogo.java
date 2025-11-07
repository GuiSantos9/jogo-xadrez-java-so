package br.com.trabalhoso.gui;

import br.com.trabalhoso.xadrez.partida.PartidaXadrez;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.io.PrintWriter; // IMPORTAR

public class JanelaJogo extends JFrame {

    private PartidaXadrez partida;
    private PainelTabuleiro painelTabuleiro;

    /**
     * CONSTRUTOR MODIFICADO
     * Agora recebe a partida e o canal de rede.
     */
    public JanelaJogo(PartidaXadrez partida, PrintWriter out) {
        this.partida = partida;

        // Passa a partida E o canal de rede para o painel
        this.painelTabuleiro = new PainelTabuleiro(this.partida, out);

        this.setTitle("Jogo de Xadrez em Java");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(painelTabuleiro, BorderLayout.CENTER);
        this.setSize(700, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    /**
     * Getter para o Painel (necessário para o Ouvinte de rede)
     */
    public PainelTabuleiro getPainelTabuleiro() {
        return this.painelTabuleiro;
    }

    /**
     * O metodo main() NAO É MAIS USADO AQUI.
     * Removemos o main() daqui, pois o Servidor e o Cliente
     * agora têm seus próprios main().
     */
    // public static void main(String[] args) { ... } // REMOVER
}
