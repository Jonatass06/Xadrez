import java.util.ArrayList;

public class Rei extends Peca {

    private boolean primeiroMovimento;

    public Rei(String cor, Posicao posicao) {
        super(cor, cor.equals("Branco") ? '♚' : '♔', posicao);

    }

    @Override
    public ArrayList<Posicao> possiveisMovimentos(Tabuleiro tabuleiro) {

        Posicao posicaoAtual = this.getPosicao();
        ArrayList<Posicao> possiveisMovimentos = new ArrayList<>();
        int posicaoNoTabuleiro = tabuleiro.getPosicoes().indexOf(posicaoAtual);

        for (Posicao posicao : tabuleiro.getPosicoes()) {
            int indice = tabuleiro.getPosicoes().indexOf(posicao);
            if (indice == posicaoNoTabuleiro - 9 ||
                    indice == posicaoNoTabuleiro - 8 ||
                    indice == posicaoNoTabuleiro - 7 ||
                    indice == posicaoNoTabuleiro - 1 ||
                    indice == posicaoNoTabuleiro + 1 ||
                    indice == posicaoNoTabuleiro + 7 ||
                    indice == posicaoNoTabuleiro + 8 ||
                    indice == posicaoNoTabuleiro + 9) {
                if (validaExtremidade(posicaoNoTabuleiro + 1)) {
                    if (!(indice == posicaoNoTabuleiro + 1 ||
                            indice == posicaoNoTabuleiro + 9 ||
                            indice == posicaoNoTabuleiro - 7)) {
                        verificaPeca(posicao, possiveisMovimentos);

                    }
                } else if (validaExtremidade(posicaoNoTabuleiro)) {
                    if (!(indice == posicaoNoTabuleiro - 1 ||
                            indice == posicaoNoTabuleiro - 9 ||
                            indice == posicaoNoTabuleiro + 7)) {
                        verificaPeca(posicao, possiveisMovimentos);
                    }
                } else {
                    verificaPeca(posicao, possiveisMovimentos);
                }
            }
        }
        return possiveisMovimentos;
    }


    @Override
    public String toString() {
        return "Rei{} " + super.toString();
    }
}
