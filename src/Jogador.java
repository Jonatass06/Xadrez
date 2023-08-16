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

    public String mostrarPecas(Tabuleiro tabuleiro, Jogador adversario) {
        int count = 0;
        String retorno = "";
        for (Peca peca : pecas) {
            if (peca.possiveisMovimentos(tabuleiro, this, adversario, true).size() > 0) {
                count++;
                if (pecas.indexOf(peca) < 10) {
                    retorno += "[0" + pecas.indexOf(peca) + "] - " + peca.getIcone() + "  |  ";
                } else {
                    retorno += "[" + pecas.indexOf(peca) + "] - " + peca.getIcone() + "  |  ";
                }
                if (count % 3 == 0) {
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
        if (posicao.getPeca() != null) {
            adversario.removerPeca(posicao.getPeca());
        }
        peca.mover(posicao, tabuleiro, adversario);
        return true;
    }

    public void addPecas(Peca peca) {
        this.pecas.add(peca);
    }

    public ArrayList<Peca> getPecasComMov(Tabuleiro tabuleiro, Jogador adversario) {
        ArrayList<Peca> pecasComMov = new ArrayList<>();
        for (Peca peca : pecas) {
            if (peca.possiveisMovimentos(tabuleiro, this, adversario, true).size() > 0) {
                pecasComMov.add(peca);
            }
        }
        return pecasComMov;
    }

    public ArrayList<Peca> getPecas() {

        return this.pecas;
    }

    public boolean proporEmpate(Jogador oponente) {
        return true;
    }

    public void desistir() {
    }

    public void removerPeca(Peca peca) {
        this.pecas.remove(peca);
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