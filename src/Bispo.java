import java.util.ArrayList;

public class Bispo extends Peca {

    public Bispo(String cor, Posicao posicao){
        super(cor, cor.equals("Branco")?'♝':'♗', posicao);
    }

    @Override
    public ArrayList<Posicao> possiveisMovimentos(Tabuleiro tabuleiro, Jogador jogador,
                                                  Jogador adversario) {

        ArrayList<Posicao> possiveisMovimentos = new ArrayList<>();

        int posicaoNoTabuleiro = tabuleiro.getPosicoes().indexOf(this.getPosicao());

        umaDiagonal(7, posicaoNoTabuleiro, tabuleiro, possiveisMovimentos, jogador, adversario, 0);
        umaDiagonal(-7, posicaoNoTabuleiro, tabuleiro, possiveisMovimentos, jogador, adversario, 1);
        umaDiagonal(9, posicaoNoTabuleiro, tabuleiro, possiveisMovimentos, jogador, adversario, 1);
        umaDiagonal(-9, posicaoNoTabuleiro, tabuleiro, possiveisMovimentos, jogador, adversario, 0);

        return possiveisMovimentos;
    }

    private void umaDiagonal(int soma, int posicaoNoTabuleiro, Tabuleiro tabuleiro,
                             ArrayList<Posicao> possiveisMovimentos, Jogador jogador, Jogador adversario, int modI){
        for (int i = (validaExtremidade(posicaoNoTabuleiro) ?
                -1 : posicaoNoTabuleiro + soma);
             i >= 0 && i<=63;
             i += soma) {
            if (verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos, jogador, tabuleiro, adversario ) ||
                    validaExtremidade(i+modI)) {
                break;
            }
        }
    }

    @Override
    public String toString() {
        return super.toString()+" Bispo";
    }
}
