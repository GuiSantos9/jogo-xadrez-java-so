package br.com.trabalhoso.rede;

import br.com.trabalhoso.gui.JanelaJogo;
import br.com.trabalhoso.xadrez.partida.PartidaXadrez;
import br.com.trabalhoso.xadrez.partida.Posicao;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

    private PartidaXadrez partida;
    private JanelaJogo janela;
    private PrintWriter out; // Canal para ENVIAR mensagens ao Servidor
    private BufferedReader in; // Canal para RECEBER mensagens do Servidor

    public Cliente() {
        this.partida = new PartidaXadrez();
    }

    public void iniciar(String ipServidor) {
        try {
            // 1. Tenta se conectar ao Servidor
            Socket socket = new Socket(ipServidor, 12345);
            System.out.println("Conectado ao Servidor!");

            // 2. Configura os canais de comunicação
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 3. Inicia a Interface Gráfica
            this.janela = new JanelaJogo(partida, out);
            this.janela.setVisible(true);

            // 4. Inicia o "Ouvinte"
            Thread ouvinte = new Thread(this::ouvirServidor);
            ouvinte.start();

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possível conectar ao servidor: " + ipServidor);
        }
    }

    /**
     * Este metodo roda em uma Thread separada.
     * Fica em um loop infinito lendo as mensagens do oponente.
     */
    private void ouvirServidor() {
        try {
            String jogadaDoOponente;
            while ((jogadaDoOponente = in.readLine()) != null) {
                System.out.println("Cliente recebeu: " + jogadaDoOponente);

                // Processa a jogada (ex: "1,4,3,4")
                String[] coords = jogadaDoOponente.split(",");
                Posicao origem = new Posicao(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
                Posicao destino = new Posicao(Integer.parseInt(coords[2]), Integer.parseInt(coords[3]));

                // Realiza a jogada no "cérebro" local
                partida.realizarJogada(origem, destino);

                // ATUALIZA A GUI (de forma segura)
                SwingUtilities.invokeLater(() -> {
                    janela.getPainelTabuleiro().desenharTabuleiro();
                    janela.getPainelTabuleiro().verificarStatusDoJogo();
                });
            }
        } catch (IOException e) {
            System.out.println("Servidor desconectou.");
        }
    }

    /**
     * Ponto de entrada do Cliente (Jogador 2).
     */
    public static void main(String[] args) {
        // Pede o IP do servidor. Para testar no mesmo PC, use "localhost"
        String ip = JOptionPane.showInputDialog("Digite o IP do Servidor:", "localhost");
        if (ip != null && !ip.isEmpty()) {
            Cliente cliente = new Cliente();
            cliente.iniciar(ip);
        }
    }
}
