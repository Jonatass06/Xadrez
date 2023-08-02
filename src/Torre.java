import java.util.ArrayList;

public class Torre extends Peca {

    private boolean primeiroMovimento;

    public Torre(String cor, Posicao posicao){
        super(cor, cor.equals("Branco")?'♜':'♖', posicao);
    }

    @Override
    public ArrayList<Posicao> possiveisMovimentos(Tabuleiro tabuleiro) {

        ArrayList<Posicao> possiveisMovimentos = new ArrayList<>();

        int posicaoNoTabuleiro = tabuleiro.getPosicoes().indexOf(this.getPosicao());

        for (int i = posicaoNoTabuleiro + 8;
             i < tabuleiro.getPosicoes().size();
             i += 8) {

            if (verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos)) {
                break;
            }
        }
        for (int i = posicaoNoTabuleiro - 8;
             i >= 0;
             i -= 8) {
            if (verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos)) {
                break;
            }
        }
        for (int i = (validaExtremidade(posicaoNoTabuleiro + 1) ?
                64 : posicaoNoTabuleiro + 1);
             i < tabuleiro.getPosicoes().size();
             i++) {
            if (verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos) ||
                    validaExtremidade(i + 1)) {
                break;
            }
        }
        for (int i = (validaExtremidade(posicaoNoTabuleiro) ?
                -1 : posicaoNoTabuleiro - 1);
             i >= 0;
             i--) {
            if (verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos) ||
                    validaExtremidade(i)) {
                break;
            }
        }
        return possiveisMovimentos;

    }

    private void vertical(){

    }

    @Override
    public String toString() {
        return "Torre{" +
                "primeiroMovimento=" + primeiroMovimento +
                "} " + super.toString();
    }
}
