import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class Rei extends Peca {

    private boolean primeiroMovimento;

    public Rei(String cor, Posicao posicao) {
        super(cor, cor.equals("Branco") ? '♚' : '♔', posicao);
        primeiroMovimento = true;
    }

    @Override
    public ArrayList<Posicao> possiveisMovimentos(Tabuleiro tabuleiro, Jogador jogador, Jogador adversario) {

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
                        verificaPeca(posicao, possiveisMovimentos, jogador, tabuleiro, adversario);

                    }
                } else if (validaExtremidade(posicaoNoTabuleiro)) {
                    if (!(indice == posicaoNoTabuleiro - 1 ||
                            indice == posicaoNoTabuleiro - 9 ||
                            indice == posicaoNoTabuleiro + 7)) {
                        verificaPeca(posicao, possiveisMovimentos, jogador, tabuleiro, adversario);
                    }
                } else {
                    verificaPeca(posicao, possiveisMovimentos, jogador, tabuleiro, adversario);
                }
            }
        }
        Posicao posicaoRei = null;
        for (Posicao posicao : tabuleiro.getPosicoes()) {
            if (posicao == this.getPosicao()) {
                posicaoRei = posicao;
            }
        }


        if(adversario != null){
            if (verificaRoque(tabuleiro, +1, +2, jogador, adversario, posicaoRei)) {
                possiveisMovimentos.add(tabuleiro.getPosicoes().get(tabuleiro.getPosicoes()
                        .indexOf(this.getPosicao()) + 2));
            }
            if (verificaRoque(tabuleiro, -1, -3, jogador, adversario, posicaoRei)) {
                possiveisMovimentos.add(tabuleiro.getPosicoes().get(tabuleiro.getPosicoes()
                        .indexOf(this.getPosicao()) - 2));
            }
        }
        return possiveisMovimentos;
    }

    private boolean verificaRoque(Tabuleiro tabuleiro, int mod, int max,
                                  Jogador jogador, Jogador adversario, Posicao posicaoRei) {

        if (tabuleiro.getPosicoes().get(
                tabuleiro.getPosicoes().indexOf(posicaoRei) + max + mod)
                .getPeca() != null &&
                this.primeiroMovimento &&
                tabuleiro.getPosicoes().get(
                        tabuleiro.getPosicoes().indexOf(posicaoRei) + max + mod)
                        .getPeca() instanceof Torre &&
                ((Torre) tabuleiro.getPosicoes().get(
                        tabuleiro.getPosicoes().indexOf(posicaoRei) + max + mod)
                        .getPeca()).getPrimeiroMovimento()
        ) {
            for (int i = tabuleiro.getPosicoes().indexOf(posicaoRei) + mod;
                 i != tabuleiro.getPosicoes().indexOf(posicaoRei) + max + mod;
                 i += mod) {

                if (tabuleiro.getPosicoes().get(i).getPeca() != null) {
                    return false;
                }
                for (Peca peca : adversario.getPecas()) {
                    if (peca.possiveisMovimentos(tabuleiro, jogador, null).contains(tabuleiro.getPosicoes().get(i))) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean mover(Posicao posicao, Tabuleiro tabuleiro) {

        //terminar
        if(tabuleiro.getPosicoes().indexOf(posicao) == tabuleiro.getPosicoes().indexOf(this.getPosicao())-2){

        }
        if(tabuleiro.getPosicoes().indexOf(posicao) == tabuleiro.getPosicoes().indexOf(this.getPosicao())+2){
            Peca peca = tabuleiro.getPosicoes().get(tabuleiro.getPosicoes().indexOf(this.getPosicao())+3).getPeca();
            peca.mover(tabuleiro.getPosicoes().get(tabuleiro.getPosicoes().indexOf(this.getPosicao())+1), tabuleiro);
        }

        //Atribuindo a peça para a nova posição no tabuleiro
        posicao.setPeca(this);
        //Removendo a peça da posição anterior
        this.getPosicao().setPeca(null);
        //Trocando a posicao atual da peca
        this.setPosicao(posicao);
        this.primeiroMovimento = false;
        return true;
    }

    public void setPrimeiroMovimento(boolean primeiroMovimento) {
        this.primeiroMovimento = primeiroMovimento;
    }

    @Override
    public String toString() {
        return "Rei{} " + super.toString();
    }
}
