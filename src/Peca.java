import java.util.ArrayList;

public abstract class Peca {
    private String cor;
    private Posicao posicao;
    private char icone;

    public Peca (String cor, char icone, Posicao posicao){
        this.cor = cor;
        this.icone = icone;
        this.posicao = posicao;
    }

    public char getIcone() {
        return icone;
    }

    public boolean mover(Posicao posicao, Tabuleiro tabuleiro) {
        ArrayList<Posicao> possiveisPosicoes = possiveisMovimentos(tabuleiro);
        for(Posicao posicaoF : possiveisPosicoes){
            if (posicaoF == posicao){
                //Atribuindo a peça para a nova posição no tabuleiro
                posicao.setPeca(this);
                //Removendo a peça da posição anterior
                this.posicao.setPeca(null);
                //Trocando a posicao atual da peca
                this.posicao = posicao;
                return true;
            }
        }
        return false;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public String getCor() {
        return cor;
    }

    public abstract ArrayList<Posicao> possiveisMovimentos(Tabuleiro tabuleiro);

    public boolean verificaPeca (Posicao posicao, ArrayList<Posicao> possiveisMovimentos){
        if (posicao.getPeca() == null) {
            possiveisMovimentos.add(posicao);
            return false;
        } else if (!posicao.getPeca().getCor().equals(this.getCor())) {
            possiveisMovimentos.add(posicao);
        }
        return true;
    }

//    public abstract char icone() {
//        return 'a';
//    }



    public boolean validaExtremidade(int posicaoNoTabuleiro){
        boolean a =posicaoNoTabuleiro % 8 == 0;
        return posicaoNoTabuleiro % 8 == 0;
    }



    @Override
    public String toString() {
        return "Peca{" +
                "cor='" + cor + '\'' +
                ", icone=" + icone +
                '}';
    }
}
