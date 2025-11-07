package br.com.trabalhoso.rede;

import br.com.trabalhoso.gui.JanelaJogo;
import br.com.trabalhoso.xadrez.partida.PartidaXadrez;
import br.com.trabalhoso.xadrez.partida.Posicao;
import br.com.trabalhoso.gui.JanelaJogo;

import javax.swing.SwingUtilities;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private PartidaXadrez partida;
    private JanelaJogo janela;
    private PrintWriter out;
    private BufferedReader in;

    public Servidor(){
        this.partida = new PartidaXadrez();
    }

    public void iniciar(){
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Servidor iniciado. Aguardando conexão do Jogador 2...");

            // 1. Espera o Cliente se conectar.
            // O metodo accept() "trava" o programa até que alguém se conecte.
            Socket clienteSocket = serverSocket.accept();
            System.out.println("Jogador 2 (Cliente) conectado!");

            // 2. Configura os canais de comunicação
            this.out = new PrintWriter(clienteSocket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));

            // 3. Inicia a Interface Gráfica
            // Precisamos passar o "canal de envio" (out) para a GUI,
            // para que o PainelTabuleiro possa enviar mensagens.
            this.janela = new JanelaJogo(partida, out);
            this.janela.setVisible(true);

            // 4. Inicia o "Ouvinte"
            //Cria uma nova Thread para ficar ouvindo as jogadas do oponente
            Thread ouvinte = new Thread(this::ouvirCliente);
            ouvinte.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este método roda em uma Thread separada.
     * Fica em um loop infinito lendo as mensagens do oponente.
     */
    private void ouvirCliente() {
        try {
            String jogadaDoOponente;
            while ((jogadaDoOponente = in.readLine()) != null) {
                System.out.println("Servidor recebeu: " + jogadaDoOponente);

                // Processa a jogada (ex: "6,4,4,4")
                String[] coords = jogadaDoOponente.split(",");
                Posicao origem = new Posicao(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
                Posicao destino = new Posicao(Integer.parseInt(coords[2]), Integer.parseInt(coords[3]));

                // Realiza a jogada no "cérebro" local
                partida.realizarJogada(origem, destino);

                // ATUALIZA A GUI
                // Importante: Modificações na GUI do Swing DEVE ser feitas
                // na Thread de Eventos (EDT), e não na nossa thread ouvinte.
                SwingUtilities.invokeLater(() -> {
                    janela.getPainelTabuleiro().desenharTabuleiro();
                    janela.getPainelTabuleiro().verificarStatusDoJogo();
                });
            }
        } catch (IOException e) {
            System.out.println("Cliente desconectou.");
        }
    }

    /**
     * Ponto de entrada do Servidor (Jogador 1).
     */
    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.iniciar();
    }
}


