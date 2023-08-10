import javax.management.BadStringOperationException;
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
        System.out.println(tabuleiro.mostrarTabuleiro(jogadorJogando, adversario));
        if(validarFimDeJogo(tabuleiro, jogadorJogando, adversario)){
            return true;
        }

        for(Peca peca : jogadorJogando.getPecas()){
            if(peca instanceof Peao && ((Peao) peca).getMov() == 1){
                ((Peao) peca).incMov();
            }
        }

        int opcao;
        ArrayList<Posicao> possiveisPosicoes;

        do {
            System.out.println(tabuleiro.mostrarPecasJogador(jogadorJogando, adversario));
            System.out.println("Qual peça você deseja usar: ");
            System.out.println(jogadorJogando.mostrarPecas(tabuleiro, adversario));
            opcao = sc.nextInt();

            if (opcao > jogadorJogando.getPecas().size() - 1 || opcao < 0 ||
            jogadorJogando.getPecas().get(opcao)
                    .possiveisMovimentos(tabuleiro, jogadorJogando, adversario).size() == 0) {
                System.out.println("Valor invalido!");
            } else{
                possiveisPosicoes = jogadorJogando.getPecas().get(opcao).
                        possiveisMovimentos(tabuleiro, jogadorJogando, adversario);
                if(possiveisPosicoes.size() == 0){
                    System.out.println("Essa peça não pode fazer nenhum movimento!");
                    opcao = -1;
                }else{
                    System.out.println(tabuleiro.mostrarPossiveisMovimentos(jogadorJogando.getPecas().get(opcao), jogadorJogando, adversario));
                    if(!escolherJogada(tabuleiro, possiveisPosicoes, jogadorJogando,
                            jogadorJogando.getPecas().get(opcao), adversario)){
                        opcao = -1;
                    }
                }
            }
        } while (opcao > jogadorJogando.getPecas().size() - 1 || opcao < 0 ||
                jogadorJogando.getPecas().get(opcao)
                        .possiveisMovimentos(tabuleiro, jogadorJogando, adversario).size() == 0);
        return false;
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

        jogadorJogando.moverPeca(peca, tabuleiro.getPosicoes().get(opcao), tabuleiro, adversario);
            if(peca instanceof Peao){
                if(peca.getCor().equals("Preto") && opcao > 55){
                    promoverPeao(tabuleiro.getPosicoes().get(opcao),tabuleiro, jogadorJogando);
                }
                else if(peca.getCor().equals("Branco") && opcao < 8){
                    promoverPeao(tabuleiro.getPosicoes().get(opcao),tabuleiro, jogadorJogando);
                }
            }
            return true;
//        }
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

    public static boolean validarFimDeJogo(Tabuleiro tabuleiro, Jogador jogador, Jogador adversario) {

        for(Peca peca : jogador.getPecas()){
            for(Posicao posicao : peca.possiveisMovimentos(tabuleiro, jogador, adversario)){
                if(posicao.getPeca() != null && posicao.getPeca() instanceof Rei &&
                    adversario.getPecas().contains(posicao.getPeca()) &&
                        !verificaSeTemMov(tabuleiro, adversario, jogador)
                ){
                    System.out.println("Parabéns " + jogador.getNome() + "! Você venceu o jogo!");
                    return true;
                }
            }
        }
        for(Peca peca : adversario.getPecas()){
            for(Posicao posicao : peca.possiveisMovimentos(tabuleiro, adversario, jogador)){
                if(posicao.getPeca() != null && posicao.getPeca() instanceof Rei &&
                        adversario.getPecas().contains(posicao.getPeca()) &&
                        !verificaSeTemMov(tabuleiro, jogador, adversario)
                ){
                    System.out.println("Parabéns " + adversario.getNome() + "! Você venceu o jogo!");
                    return true;
                }
            }
        }
        if(!verificaSeTemMov(tabuleiro, jogador, adversario)){
            System.out.println("O Rei foi afogado! Declaro empate!");
            return true;
        }
        if(jogador.getPecas().size() == 1 && adversario.getPecas().size() == 1){
            System.out.println("Parece que sobram apenas os Reis, declaro empate!");
            return true;
        }

        return false;
    }

    public static boolean verificaSeTemMov(Tabuleiro tabuleiro, Jogador jogador, Jogador adversario){

        ArrayList<Posicao> possiveisPosicoes = new ArrayList<>();

        for(Peca peca : jogador.getPecas()){
            possiveisPosicoes.addAll(peca.possiveisMovimentos(tabuleiro, jogador, adversario));
        }
        return possiveisPosicoes.size() > 0;
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
        jogador.removerPeca(posicao.getPeca());
        jogador.getPecas().add(peca);
    }
}

//pecas sumindo
//outros problemas