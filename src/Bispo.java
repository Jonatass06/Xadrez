import java.util.ArrayList;

public class Bispo extends Peca {

    public Bispo(String cor, Posicao posicao){
        super(cor, cor=="Branco"?'♝':'♗', posicao);
    }

    @Override
    public ArrayList<Posicao> possiveisMovimentos(Tabuleiro tabuleiro) {

        ArrayList<Posicao> possiveisMovimentos = new ArrayList<>();

        int posicaoNoTabuleiro = tabuleiro.getPosicoes().indexOf(this.getPosicao());

        umaDiagonal(7, posicaoNoTabuleiro, tabuleiro, possiveisMovimentos);
        umaDiagonal(-7, posicaoNoTabuleiro, tabuleiro, possiveisMovimentos);
        umaDiagonal(9, posicaoNoTabuleiro, tabuleiro, possiveisMovimentos);
        umaDiagonal(-9, posicaoNoTabuleiro, tabuleiro, possiveisMovimentos);

        return possiveisMovimentos;
    }
    private void umaDiagonal(int soma, int posicaoNoTabuleiro, Tabuleiro tabuleiro,
                             ArrayList<Posicao> possiveisMovimentos){
        for (int i = (validaExtremidade(posicaoNoTabuleiro) ?
                -1 : posicaoNoTabuleiro + soma);
             i >= 0 && i<=63;
             i += soma) {
            if (verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos) ||
                    validaExtremidade(i)) {
                break;
            }
        }
    }

    @Override
    public String toString() {
        return super.toString()+" Bispo";
    }
}
