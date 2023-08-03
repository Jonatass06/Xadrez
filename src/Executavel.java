import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Executavel {
    static Scanner sc = new Scanner(System.in);
    static Scanner sc1 = new Scanner(System.in);
    static Jogador logado = null;

    public static void main(String[] args) {
        boolean finalizar = true;
        while (finalizar) {
            if (logado == null) {
                finalizar = menuInicial();
            } else {
                menuJogador();
            }
        }
    }

    public static void menuJogador() {
        int opcao;
        do {
            System.out.println("""
                    --------------------
                    -       Menu       -
                    -                  -
                    - [1] Jogar        -
                    - [2] Sair         -
                    -                  -
                    --------------------                    
                    """);
            opcao = sc.nextInt();
            switch (opcao) {
                case 1 -> iniciarJogo();
                case 2 -> logado = null;
                default -> System.out.println("Valor Inválido!");
            }
        } while (opcao != 2);
    }

    public static void iniciarJogo() {

        Jogador j2 = new Jogador("Wilson", "wilson");
        Tabuleiro tabuleiro = new Tabuleiro();

        Random sort = new Random(2);
        if (sort.nextInt() == 1) {
            logado.setCor("Branco", tabuleiro);
            j2.setCor("Preto", tabuleiro);
        } else {
            logado.setCor("Preto", tabuleiro);
            j2.setCor("Branco", tabuleiro);
        }

        boolean sair = false;
        for (int i = 0; !sair; i++) {
            if (i % 2 == 0) {
                sair = logado.getCor().equals("Branco") ?
                        joga(logado, tabuleiro, j2) : joga(j2, tabuleiro, logado);
            } else {
                sair = logado.getCor().equals("Branco") ?
                        joga(j2, tabuleiro, logado) : joga(logado, tabuleiro, j2);
            }
        }
    }

    public static boolean joga(Jogador jogadorJogando, Tabuleiro tabuleiro, Jogador adversario) {
        System.out.println(tabuleiro);

        int opcao;
        ArrayList<Posicao> possiveisPosicoes;

        do {
            System.out.println(tabuleiro.mostrarPecasJogador(jogadorJogando.getPecas()));
            System.out.println("Qual peça você deseja usar: ");
            System.out.println(jogadorJogando.mostrarPecas());
            opcao = sc.nextInt();

            if (opcao > jogadorJogando.getPecas().size() - 1 || opcao < 0) {
                System.out.println("Valor invalido!");
            } else{
                possiveisPosicoes = jogadorJogando.getPecas().get(opcao).possiveisMovimentos(tabuleiro);
                if(possiveisPosicoes.size() == 0){
                    System.out.println("Essa peça não pode fazer nenhum movimento!");
                    opcao = -1;
                }else{
                    System.out.println(tabuleiro.mostrarPossiveisMovimentos(possiveisPosicoes));
                    if(!escolherJogada(tabuleiro, possiveisPosicoes, jogadorJogando,
                            jogadorJogando.getPecas().get(opcao), adversario)){
                        opcao = -1;
                    }
                }
            }
        } while (opcao > jogadorJogando.getPecas().size() - 1 || opcao < 0);
        return validarVitoria(adversario);
    }

    public static boolean escolherJogada(Tabuleiro tabuleiro, ArrayList<Posicao> posicoes,
                                      Jogador jogadorJogando, Peca peca, Jogador adversario){
        int opcao = 0;

        do {
            System.out.println("Para onde você deseja ir? (-1 Cancel)");
            opcao = sc.nextInt();
            if(opcao == -1){
                return false;
            }
            if(posicoes.contains(tabuleiro.getPosicoes().get(opcao))){

                return verificarJogada(jogadorJogando, peca, opcao, tabuleiro, adversario);

            } else{
                System.out.println("Jogada Inválida!");
            }
        }while(!posicoes.contains(tabuleiro.getPosicoes().get(opcao)));
        return true;

    }

    public static boolean verificarJogada(Jogador jogadorJogando, Peca peca, int opcao,
                                       Tabuleiro tabuleiro, Jogador adversario){

        //Valores para a inversçao de jogada
        int antigaPosicao = tabuleiro.getPosicoes().indexOf(peca.getPosicao());
        Peca antigaPeca = null;
        if(tabuleiro.getPosicoes().get(opcao).getPeca() != null){
            antigaPeca = tabuleiro.getPosicoes().get(opcao).getPeca();
        }

        //Jogada
        jogadorJogando.moverPeca(peca, tabuleiro.getPosicoes().get(opcao), tabuleiro, adversario);

        //Inversao de Jogada
        if(tabuleiro.verificaCheque(adversario)){
            jogadorJogando.moverPeca(peca, tabuleiro.getPosicoes().get(antigaPosicao), tabuleiro, adversario);
            tabuleiro.getPosicoes().get(opcao).setPeca(antigaPeca);
            adversario.addPecas(antigaPeca);
            System.out.println("Você não pode deixar seu rei em risco!");
            return false;
        } else{
            if(peca instanceof Peao){
                if(peca.getCor().equals("Preto") && opcao > 55){
                    promoverPeao(tabuleiro.getPosicoes().get(opcao),tabuleiro, jogadorJogando);
                }
                else if(peca.getCor().equals("Branco") && opcao < 8){
                    promoverPeao(tabuleiro.getPosicoes().get(opcao),tabuleiro, jogadorJogando);
                }
            }
            return true;
        }
    }


    public static boolean menuInicial() {
        int opcao;
        do {
            System.out.println("""
                    ----------------------
                    -        Menu        -
                    -                    -
                    -  [1] Login         -
                    -  [2] Cadastro      -
                    -  [3] Finalizar     -
                    -                    -
                    ----------------------
                    """);
            opcao = sc.nextInt();
            if (opcao > 3 || opcao < 1) {
                System.out.println("Valor invalido!");
            } else if (opcao == 1) {
                login();
                return true;
            } else if (opcao == 2) {
                cadastrar();
                return true;
            }
        } while (opcao != 3);
        return false;
    }

    public static void cadastrar() {
        String nome, senha;
        boolean sair = false;
        while (!sair) {
            System.out.print("Digite seu nome: ");
            nome = sc1.nextLine();
            System.out.print("Digite sua senha: ");
            senha = sc.next();
            if (senha.equals("") || nome.equals("")) {
                System.out.println("Voce precisa inserir valores validos!");
            } else {
                logado = new Jogador(nome, senha);
                sair = true;
            }
        }
    }

    public static void login() {
        String nome, senha;
        boolean sair = false;
        while (!sair) {
            System.out.println("Digite seu nome: ");
            nome = sc1.nextLine();
            System.out.println("Digite sua senha: ");
            senha = sc.next();

            Jogador procura = Jogador.encotrarJogador(senha, nome);
            if (procura == null) {
                System.out.println("""
                        ----------------------------
                        - Nome ou senha invalidos! -
                        -                          -
                        -  [1] Tentar novamente    -
                        -     [2] Desistir         -
                        -                          -
                        ----------------------------""");
                int opcao = sc.nextInt();
                sair = opcao == 2 ? true : false;
                if (opcao != 2 && opcao != 1) {
                    System.out.println("Valor inserido invalido!");
                }
            } else {
                logado = procura;
            }

        }
    }

    public static boolean validarVitoria(Jogador adversario) {
        for (Peca peca : adversario.getPecas()) {
            if (peca instanceof Rei) {
                return false;
            }
        }
        System.out.println("Fim de Jogo, " + adversario.getNome() + "Você infelizmente perdeu!");
        return true;
    }

    public static void promoverPeao(Posicao posicao, Tabuleiro tabuleiro, Jogador jogador){
        int opcao;
        do {
            System.out.println("""
                    _____________________________
                    | Você quer que o peão seja |
                    | promovido para qual peça? |
                    | [0] - Rainha              |
                    | [1] - Torre               |
                    | [2] - Bispo               |
                    | [3] - Cavalo              |
                    -----------------------------
                    """);
            opcao = sc.nextInt();
            if(opcao < 0 || opcao > 3){
                System.out.println("Valor Inválido!");
            }
        }
        while (opcao < 0 || opcao > 3);


        Peca peca;

        switch (opcao){
            case 0 -> peca = new Rainha(posicao.getPeca().getCor(), posicao);
            case 1 -> peca = new Torre(posicao.getPeca().getCor(), posicao);
            case 2 -> peca = new Bispo(posicao.getPeca().getCor(), posicao);
            default -> peca = new Cavalo(posicao.getPeca().getCor(), posicao);
        }
        posicao.setPeca(peca);
        jogador.getPecas().remove(posicao.getPeca());
        jogador.getPecas().add(peca);
    }
}
//ver cheque mate
//roque
//en passant
