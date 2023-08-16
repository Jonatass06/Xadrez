import java.util.ArrayList;

public class Cavalo extends Peca {

    public Cavalo(String cor, Posicao posicao) {
        super(cor, cor.equals("Branco") ? '♞' : '♘', posicao);
    }

    @Override
    public ArrayList<Posicao> possiveisMovimentos(Tabuleiro tabuleiro, Jogador jogador, Jogador adversario, boolean simular) {
        ArrayList<Posicao> possiveisMovimentos = new ArrayList<>();

        Posicao posicaoAtual = this.getPosicao();

        int posicaoNoTabuleiro = tabuleiro.getPosicoes().indexOf(posicaoAtual);

        for (Posicao posicao : tabuleiro.getPosicoes()) {
            int indice = tabuleiro.getPosicoes().indexOf(posicao);
            if (tabuleiro.getPosicoes().indexOf(posicao) == posicaoNoTabuleiro - 17 ||
                    tabuleiro.getPosicoes().indexOf(posicao) == posicaoNoTabuleiro - 15 ||
                    tabuleiro.getPosicoes().indexOf(posicao) == posicaoNoTabuleiro - 10 ||
                    tabuleiro.getPosicoes().indexOf(posicao) == posicaoNoTabuleiro - 6 ||
                    tabuleiro.getPosicoes().indexOf(posicao) == posicaoNoTabuleiro + 17 ||
                    tabuleiro.getPosicoes().indexOf(posicao) == posicaoNoTabuleiro + 15 ||
                    tabuleiro.getPosicoes().indexOf(posicao) == posicaoNoTabuleiro + 10 ||
                    tabuleiro.getPosicoes().indexOf(posicao) == posicaoNoTabuleiro + 6) {
                //H
                if (validaExtremidade(posicaoNoTabuleiro + 1)
                ) {
                    if (!(indice == posicaoNoTabuleiro + 17 ||
                            indice == posicaoNoTabuleiro + 10 ||
                            indice == posicaoNoTabuleiro - 6 ||
                            indice == posicaoNoTabuleiro - 15)) {
                        verificaPeca(posicao, possiveisMovimentos, jogador, tabuleiro, adversario, simular);
                    }
                }
                //A
                else if (validaExtremidade(posicaoNoTabuleiro)
                ) {
                    if (!(indice == posicaoNoTabuleiro - 17 ||
                            indice == posicaoNoTabuleiro - 10 ||
                            indice == posicaoNoTabuleiro + 6 ||
                            indice == posicaoNoTabuleiro + 15)) {
                        verificaPeca(posicao, possiveisMovimentos, jogador, tabuleiro, adversario, simular);
                    }
                }
                //B
                else if (validaExtremidade(posicaoNoTabuleiro - 1)) {
                    if (!(indice == posicaoNoTabuleiro - 10 ||
                            indice == posicaoNoTabuleiro + 6)) {
                        verificaPeca(posicao, possiveisMovimentos, jogador, tabuleiro, adversario, simular);
                    }
                }
                //G
                else if (validaExtremidade(posicaoNoTabuleiro + 2)
                ) {
                    if (!(indice == posicaoNoTabuleiro + 10 ||
                            indice == posicaoNoTabuleiro - 6)) {
                        verificaPeca(posicao, possiveisMovimentos, jogador, tabuleiro, adversario, simular);
                    }
                } else {
                    verificaPeca(posicao, possiveisMovimentos, jogador, tabuleiro,adversario, simular);
                }
            }
        }
        return possiveisMovimentos;
    }

    @Override
    public String toString() {
        return super.toString() + " Cavalo";
    }
}
