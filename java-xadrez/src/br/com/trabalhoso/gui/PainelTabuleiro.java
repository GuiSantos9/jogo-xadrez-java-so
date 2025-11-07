package br.com.trabalhoso.gui;

import br.com.trabalhoso.xadrez.partida.PartidaXadrez;
import br.com.trabalhoso.xadrez.partida.Posicao;
import br.com.trabalhoso.xadrez.pecas.*; // Importa todas as suas peças

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

public class PainelTabuleiro extends JPanel implements ActionListener{
    private PartidaXadrez partida;
    private JButton[][] casas;
    private Posicao origemSelecionada;
    private PrintWriter out;

    private final Color corClara = new Color(238, 238, 210);
    private final Color corEscura = new Color(118, 150, 86);
    private final Color corSelecionada = new Color(246, 246, 130, 200);

    public PainelTabuleiro(PartidaXadrez partida, PrintWriter out){
        this.partida = partida;
        this.out = out;
        this.casas = new JButton[8][8];
        this.origemSelecionada = null;

        setLayout(new GridLayout(8, 8));

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                JButton botao = new JButton();
                botao.setOpaque(true);
                botao.setBorderPainted(false);

                botao.setActionCommand(i + "," + j);
                botao.addActionListener(this);

                botao.setFont(new Font("Arial Unicode MS", Font.PLAIN, 40));

                configurarCorCasa(botao, i, j); // Pinta o fundo (claro ou escuro)

                this.casas[i][j] = botao;
                add(botao);

            }
        }

        desenharTabuleiro();

    }

    private void configurarCorCasa(JButton botao, int linha, int col) {
        if ((linha + col) % 2 == 0) {
            botao.setBackground(corClara);
        } else {
            botao.setBackground(corEscura);
        }
    }

    /**
     * Metodo principal de atualização da GUI.
     * Lê o `partida.getTabuleiro()` e atualiza os ícones (texto) nos botões.
     */
    public void desenharTabuleiro() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // Reseta a cor de fundo (para limpar seleções)
                configurarCorCasa(casas[i][j], i, j);

                Peca peca = partida.getTabuleiro().getPeca(new Posicao(i, j));

                if (peca != null) {
                    casas[i][j].setText(getSimboloPeca(peca));
                    // Define a cor da fonte (preto para peças pretas, branco para brancas)
                    casas[i][j].setForeground(peca.getCor() == Cor.PRETA ? Color.BLACK : Color.WHITE);
                } else {
                    casas[i][j].setText(""); // Casa vazia
                }
            }
        }
    }

    /**
     * Converte um objeto Peca em seu símbolo Unicode.
     */
    private String getSimboloPeca(Peca peca) {
        if (peca.getCor() == Cor.BRANCA) {
            if (peca instanceof Rei) return "♔";
            if (peca instanceof Rainha) return "♕";
            if (peca instanceof Torre) return "♖";
            if (peca instanceof Bispo) return "♗";
            if (peca instanceof Cavalo) return "♘";
            if (peca instanceof Peao) return "♙";
        } else {
            if (peca instanceof Rei) return "♚";
            if (peca instanceof Rainha) return "♛";
            if (peca instanceof Torre) return "♜";
            if (peca instanceof Bispo) return "♝";
            if (peca instanceof Cavalo) return "♞";
            if (peca instanceof Peao) return "♟";
        }
        return "";
    }

    /**
     * Este é o metodo que "ouve" todos os cliques nos botões.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] coords = e.getActionCommand().split(",");
        int linha = Integer.parseInt(coords[0]);
        int coluna = Integer.parseInt(coords[1]);
        Posicao destinoClicado = new Posicao(linha, coluna);

        if (origemSelecionada == null) {
            Peca pecaClicada = partida.getTabuleiro().getPeca(destinoClicado);
            if (pecaClicada != null && pecaClicada.getCor() == partida.getJogadorAtual()) {
                origemSelecionada = destinoClicado;
                casas[linha][coluna].setBackground(corSelecionada);
            }
        } else {
            // Tenta realizar a jogada localmente
            boolean sucesso = partida.realizarJogada(origemSelecionada, destinoClicado);

            if (sucesso) {
                // --- A GRANDE MUDANÇA ---
                // Se a jogada foi válida localmente, ENVIE PELA REDE!
                String jogadaFormatada = String.format("%d,%d,%d,%d",
                        origemSelecionada.getLinha(),
                        origemSelecionada.getColuna(),
                        destinoClicado.getLinha(),
                        destinoClicado.getColuna()
                );
                out.println(jogadaFormatada);
                // -----------------------
            } else if (!origemSelecionada.equals(destinoClicado)) {
                JOptionPane.showMessageDialog(this, "Movimento Ilegal!", "Erro de Jogada", JOptionPane.ERROR_MESSAGE);
            }

            origemSelecionada = null;
            desenharTabuleiro(); // Redesenha o tabuleiro local
            verificarStatusDoJogo();
        }
    }

    /**
     * Verifica o estado da partida (se acabou) e mostra um pop-up se for o caso.
     */
    public void verificarStatusDoJogo() {
        // Só faz algo se a partida tiver terminado
        if (!partida.isPartidaEmAndamento()) {

            String mensagem;
            Cor vencedor = partida.getVencedor();

            if (vencedor != null) {
                mensagem = "XEQUE-MATE! \nO jogador " + vencedor + " venceu!";
            } else {
                // (Aqui você poderia adicionar lógica para empate/afogamento)
                mensagem = "O JOGO TERMINOU!";
            }

            // Mostra a mensagem pop-up na tela
            JOptionPane.showMessageDialog(this, mensagem, "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
