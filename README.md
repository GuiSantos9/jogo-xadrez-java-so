# jogo-xadrez-java-so

♟️ Jogo de Xadrez Multiplayer em Java
Este é um projeto acadêmico desenvolvido para a disciplina de Engenharia de Software, com o objetivo de criar um jogo de xadrez funcional para dois jogadores em rede. O projeto aplica conceitos fundamentais de Programação Orientada a Objetos, interfaces gráficas com Java Swing e comunicação cliente-servidor utilizando Sockets da biblioteca java.net.

## ✨ Funcionalidades

Lógica de Xadrez Completa: Implementação de todas as regras de movimento para as peças (Rei, Rainha, Torre, Bispo, Cavalo e Peão).

Interface Gráfica Intuitiva: Tabuleiro visual e interação baseada em cliques, desenvolvida com a biblioteca Java Swing.

Modo Multiplayer em Rede: Permite que dois jogadores se conectem e joguem em máquinas diferentes na mesma rede.

Comunicação Cliente-Servidor: Arquitetura de rede onde um jogador atua como servidor e o outro como cliente, com comunicação gerenciada por Sockets TCP.

🛠️ Tecnologias Utilizadas
Linguagem: Java

Interface Gráfica: Java Swing

Comunicação em Rede: Java Sockets (java.net)

🏗️ Arquitetura do Projeto
O projeto foi estruturado seguindo os princípios de separação de responsabilidades, com uma arquitetura que se assemelha ao padrão MVC (Model-View-Controller) e uma topologia de rede Cliente-Servidor.

Model (/partida, /pecas): Contém toda a lógica do jogo. O Tabuleiro, as Pecas e as regras de movimento são completamente independentes da interface gráfica e da rede.

View (/gui): Responsável por toda a apresentação visual. Renderiza o tabuleiro, as peças e captura as interações do usuário (cliques nos botões).

Controller/Rede (/rede): Faz a ponte entre a lógica, a interface e a comunicação em rede. A classe Servidor gerencia as conexões e o fluxo de mensagens, enquanto a lógica do Cliente envia os movimentos do jogador e recebe as atualizações do oponente.

🚀 Como Executar
Pré-requisitos
Java Development Kit (JDK) 8 ou superior instalado e configurado.

Passos para Execução
Clone o repositório:

Bash

git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
Compile o projeto: Se estiver usando uma IDE (como IntelliJ, Eclipse ou VS Code com extensões Java), basta importar o projeto e executar as classes principais. Para compilar via linha de comando, navegue até a pasta src e execute:

Bash

javac */*.java */*/*.java
Inicie o Servidor: O primeiro jogador deve iniciar o servidor. Em um terminal, execute:

Bash

java br.com.seujogo.rede.Servidor
O servidor iniciará e aguardará a conexão de dois jogadores. A janela do jogo do primeiro jogador também será aberta.

Inicie os Clientes:

Jogador 1 (que abriu o servidor): Sua aplicação já está rodando.

Jogador 2: Em outra máquina (ou no mesmo PC, para teste), execute a aplicação cliente. Se for no mesmo PC, o IP a ser conectado será 127.0.0.1 (localhost).

Bash

java br.com.seujogo.gui.JanelaJogo
룰 Como Jogar
Um jogador executa o programa como Servidor.

O segundo jogador executa como Cliente e se conecta ao endereço IP do servidor.

Assim que ambos estiverem conectados, o jogo começa. As peças brancas jogam primeiro.

Para mover uma peça, clique primeiro na peça que deseja mover e, em seguida, clique na casa de destino.

🔮 Melhorias Futuras
[ ] Implementar regras especiais: Roque, En Passant e Promoção de Peão.

[ ] Adicionar um sistema de chat para comunicação entre os jogadores.

[ ] Melhorar o tratamento de desconexões e o feedback para o usuário.

[ ] Criar um menu inicial para escolher entre ser Servidor ou Cliente de forma mais amigável.

[ ] Adicionar a funcionalidade de salvar e carregar jogos.
