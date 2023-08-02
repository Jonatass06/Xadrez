import java.util.ArrayList;

public class Jogador {

    private String nome, cor, senha;
    private ArrayList<Peca> pecas;
    private static ArrayList<Jogador> jogadores = new ArrayList<>();

    public Jogador(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        this.pecas = new ArrayList<>();
        jogadores.add(this);
    }

    public String getCor() {
        return cor;
    }

    public static Jogador encotrarJogador(String nome, String senha) {
        for (Jogador jogadorFor : jogadores) {
            if (jogadorFor.nome.equals(nome) && jogadorFor.senha.equals(senha)) {
                return jogadorFor;
            }
        }
        return null;
    }

    public String mostrarPecas(){
        String retorno = "";
        for(Peca peca : pecas){
            if(pecas.indexOf(peca) < 10){
                retorno += "[0"+pecas.indexOf(peca)+"] - " + peca.getIcone() + "  |  ";
                if((pecas.indexOf(peca)+1)%3 == 0){
                    retorno += "\n";
                }
            } else{
                retorno += "["+pecas.indexOf(peca)+"] - " + peca.getIcone() + "  |  ";
                if((pecas.indexOf(peca)+1)%3 == 0){
                    retorno += "\n";
                }
            }

        }
        return retorno;
    }

    public String getNome() {
        return nome;
    }

    public boolean moverPeca(Peca peca, Posicao posicao,
                             Tabuleiro tabuleiro, Jogador adversario) {
        Peca pecaAdversaria = posicao.getPeca();
        boolean valida = peca.mover(posicao, tabuleiro);

        if (pecaAdversaria != null && valida) {
            adversario.pecas.remove(pecaAdversaria);
        }
        if(peca instanceof Peao){
            ((Peao) peca).setPrimeiraJogada();
        }
        return valida;
    }


    public ArrayList<Peca> getPecas() {
        return pecas;
    }

    public boolean proporEmpate(Jogador oponente) {
        return true;
    }

    public void desistir() {
    }

    public void setCor(String cor, Tabuleiro tabuleiro) {
        this.cor = cor;
        for (Posicao posicao : tabuleiro.getPosicoes()) {
            if (posicao.getPeca() != null &&
                    posicao.getPeca().getCor().equals(cor)) {
                this.pecas.add(posicao.getPeca());
            }
        }
    }
}

