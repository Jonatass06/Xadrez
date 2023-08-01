import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Executavel {
    static Scanner sc = new Scanner(System.in);
    static Scanner sc1 = new Scanner(System.in);
    static Jogador logado = null;

    public static void main(String[] args) {
        boolean finalizar = false;
        while(!finalizar){
            if(logado == null){
                finalizar = menuInicial();
            } else{
                menuJogador();
            }
        }

        Jogador jogador1 = new Jogador("Jonas", "123");
        Jogador jogador2 = new Jogador("Davi", "321");
        Tabuleiro tabuleiro = new Tabuleiro();
        System.out.println(tabuleiro);

        jogador1.setCor("Branco", tabuleiro);
        jogador2.setCor("Preto", tabuleiro);

        System.out.println(jogador1.getPecas());
        int escolherPeca = sc.nextInt();
        Peca peca = jogador1.getPecas().get(escolherPeca);
        System.out.println(peca);

        ArrayList<Posicao> posicoes = peca.possiveisMovimentos(tabuleiro);
        System.out.println(tabuleiro.mostrarPossiveisMovimentos(posicoes));
        int escolhaPosicao = sc.nextInt();
        Posicao posicao = posicoes.get(escolhaPosicao);

        jogador1.moverPeca(peca, posicao, tabuleiro, jogador2);
        System.out.println(validarVitoria(jogador2));

    }



    private static void menuJogador() {
        int opcao;
        do{
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
            switch(opcao){
                case 1 -> iniciarJogo();
                case 2 -> logado = null;
                default -> System.out.println("Valor Inválido!");
            }
        }while(opcao != 2);
    }

    private static void iniciarJogo() {

        Jogador j2 = new Jogador("Wilson", "wilson");
        Tabuleiro tabuleiro = new Tabuleiro();

        Random sort = new Random(2);
        if(sort.nextInt()==1){
            logado.setCor("Branco", tabuleiro);
            j2.setCor("Preto", tabuleiro);
        }else{
            logado.setCor("Preto", tabuleiro);
            j2.setCor("Branco", tabuleiro);
        }

        boolean sair = false;
        for(int i = 0; !sair; i++){
            if(i%2 == 0){
                sair = logado.getCor().equals("Branco")?
                        joga(j2, tabuleiro):joga(logado, tabuleiro);
            }else{
                sair = logado.getCor().equals("Branco")?
                        joga(logado, tabuleiro):joga(j2, tabuleiro);
            }
        }
    }

    private static boolean joga(Jogador jogadorJogando, Tabuleiro tabuleiro){
        System.out.println(tabuleiro);
        int i = 0;
        System.out.println("------- Suas peças -------");
        for (Peca peca: jogadorJogando.getPecas()) {
            System.out.println("- ["+i+"] - " + peca.getIcone() + "-");
        }
        //fazer essa parte
        return true;
    }

    private static boolean menuInicial() {
        int opcao;
        do{
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
            if(opcao > 3 || opcao < 1){
                System.out.println("Valor invalido!");
            } else if(opcao == 1){
                login();
                return true;
            } else if (opcao == 2){
                cadastrar();
                return true;
            }
        }while(opcao != 3);
        return false;
    }

    private static void cadastrar(){
        String nome, senha;
        boolean sair = false;
        while (!sair) {
            System.out.print("Digite seu nome: ");
            nome = sc1.nextLine();
            System.out.print("Digite sua senha: ");
            senha = sc.next();
            if(senha.equals("") || nome.equals("")){
                System.out.println("Voce precisa inserir valores validos!");
            } else{
                logado = new Jogador(nome, senha);
                sair = true;
            }
        }
    }

    private static void login(){
        String nome, senha;
        boolean sair = false;
        while(!sair){
            System.out.println("Digite seu nome: ");
            nome = sc1.nextLine();
            System.out.println("Digite sua senha: ");
            senha = sc.next();

            Jogador procura = Jogador.encotrarJogador(senha, nome);
            if(procura == null){
                System.out.println("""
                        ----------------------------
                        - Nome ou senha invalidos! -
                        -                          -
                        -  [1] Tentar novamente    -
                        -     [2] Desistir         -
                        -                          -
                        ----------------------------""");
                int opcao = sc.nextInt();
                sair = opcao == 2? true : false;
                if(opcao!=2 && opcao != 1){
                    System.out.println("Valor inserido invalido!");
                }
            } else{
                logado = procura;
            }

        }
    }

    private static boolean validarVitoria(Jogador adversario){
        for(Peca peca:adversario.getPecas()){
            if(peca instanceof Rei){
                return false;
            }
        }
        return true;
    }

}
//ver cheque
//roque
//en passant
//promoção

//partida