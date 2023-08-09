import java.util.ArrayList;

public class Peao extends Peca {

    private boolean primeiroMovimento;

    public Peao(String cor, Posicao posicao){
        super(cor, cor.equals("Branco")?'♟':'♙', posicao);
        this.primeiroMovimento = true;
    }

    @Override
    public boolean mover(Posicao posicao, Tabuleiro tabuleiro) {
                //Atribuindo a peça para a nova posição no tabuleiro
                posicao.setPeca(this);
                //Removendo a peça da posição anterior
                this.getPosicao().setPeca(null);
                //Trocando a posicao atual da peca
                this.setPosicao(posicao);
                this.primeiroMovimento = false;
                return true;
    }
    @Override
    public ArrayList<Posicao> possiveisMovimentos(Tabuleiro tabuleiro, Jogador jogador, Jogador adversario) {

        ArrayList<Posicao> possiveisMovimentos = new ArrayList<>();
        Posicao posicaoAtual = this.getPosicao();
        ArrayList<Posicao> posicoesTabuleiro = tabuleiro.getPosicoes();
        int posicaoNoTabuleiro = posicoesTabuleiro.indexOf(posicaoAtual);

        if (this.getCor().equals("Preto")) {
            if (posicoesTabuleiro.get(posicaoNoTabuleiro + 8).getPeca() == null) {
                if(adversario != null){
                    if(simularJogada(tabuleiro, jogador, adversario,
                            posicoesTabuleiro.get(posicaoNoTabuleiro + 8))) {
                        possiveisMovimentos.add(
                                posicoesTabuleiro.get(posicaoNoTabuleiro + 8));
                    }
                } else {
                    possiveisMovimentos.add(
                            posicoesTabuleiro.get(posicaoNoTabuleiro + 8));
                }
                if (this.primeiroMovimento) {
                    if (posicoesTabuleiro.get(posicaoNoTabuleiro + 16).getPeca() == null) {
                        if(adversario != null){
                            if(simularJogada(tabuleiro, jogador, adversario,
                                    posicoesTabuleiro.get(posicaoNoTabuleiro + 16))){
                                possiveisMovimentos.add(
                                        posicoesTabuleiro.get(posicaoNoTabuleiro + 16));
                            }
                        } else{
                            possiveisMovimentos.add(
                                    posicoesTabuleiro.get(posicaoNoTabuleiro + 16));
                        }
                    }
                }
            }

            if(posicoesTabuleiro.get(posicaoNoTabuleiro + 9).getPeca() != null) {
                if (posicoesTabuleiro.get(posicaoNoTabuleiro + 9)
                        .getPeca().getCor().equals("Branco") &&
                        !validaExtremidade(posicaoNoTabuleiro + 1)
                ) {
                    if(adversario != null){
                        if(simularJogada(tabuleiro, jogador, adversario,
                                posicoesTabuleiro.get(posicaoNoTabuleiro + 9))){
                            possiveisMovimentos.add(
                                    posicoesTabuleiro.get(posicaoNoTabuleiro + 9));
                        }
                    }else{
                        possiveisMovimentos.add(
                                posicoesTabuleiro.get(posicaoNoTabuleiro + 9));
                    }
                }
            }

                if(posicoesTabuleiro.get(posicaoNoTabuleiro + 7).getPeca() != null) {
                    if (posicoesTabuleiro.get(posicaoNoTabuleiro + 7)
                            .getPeca().getCor().equals("Branco") &&
                            !validaExtremidade(posicaoNoTabuleiro)) {
                        if(adversario != null){
                            if(simularJogada(tabuleiro, jogador, adversario,
                                    posicoesTabuleiro.get(posicaoNoTabuleiro + 7))){
                                possiveisMovimentos.add(
                                        posicoesTabuleiro.get(posicaoNoTabuleiro + 7));
                            }
                        }else{
                            possiveisMovimentos.add(
                                    posicoesTabuleiro.get(posicaoNoTabuleiro + 7));
                        }
                    }
                }
        } else {
            if (posicoesTabuleiro.get(posicaoNoTabuleiro - 8).getPeca() == null) {
            if(adversario != null){
                if(simularJogada(tabuleiro, jogador, adversario,
                        posicoesTabuleiro.get(posicaoNoTabuleiro - 8))) {
                    possiveisMovimentos.add(
                            posicoesTabuleiro.get(posicaoNoTabuleiro - 8));
                }
            } else {
                possiveisMovimentos.add(
                        posicoesTabuleiro.get(posicaoNoTabuleiro - 8));
            }

            if (this.primeiroMovimento) {
                if (posicoesTabuleiro.get(posicaoNoTabuleiro - 16).getPeca() == null) {
                    if(adversario != null){
                        if(simularJogada(tabuleiro, jogador, adversario,
                                posicoesTabuleiro.get(posicaoNoTabuleiro - 16))){
                            possiveisMovimentos.add(
                                    posicoesTabuleiro.get(posicaoNoTabuleiro - 16));
                        }
                    } else{
                        possiveisMovimentos.add(
                                posicoesTabuleiro.get(posicaoNoTabuleiro - 16));
                    }
                }
            }
        }

            if(posicoesTabuleiro.get(posicaoNoTabuleiro - 9).getPeca() != null) {
                if (posicoesTabuleiro.get(posicaoNoTabuleiro - 9)
                        .getPeca().getCor().equals("Preto") &&
                        !validaExtremidade(posicaoNoTabuleiro + 1)
                ) {
                    if(adversario != null){
                        if(simularJogada(tabuleiro, jogador, adversario,
                                posicoesTabuleiro.get(posicaoNoTabuleiro - 9))){
                            possiveisMovimentos.add(
                                    posicoesTabuleiro.get(posicaoNoTabuleiro - 9));
                        }
                    }else{
                        possiveisMovimentos.add(
                                posicoesTabuleiro.get(posicaoNoTabuleiro - 9));
                    }
                }
            }

            if(posicoesTabuleiro.get(posicaoNoTabuleiro - 7).getPeca() != null) {
                if (posicoesTabuleiro.get(posicaoNoTabuleiro - 7)
                        .getPeca().getCor().equals("Preto") &&
                        !validaExtremidade(posicaoNoTabuleiro)) {
                    if(adversario != null){
                        if(simularJogada(tabuleiro, jogador, adversario,
                                posicoesTabuleiro.get(posicaoNoTabuleiro - 7))){
                            possiveisMovimentos.add(
                                    posicoesTabuleiro.get(posicaoNoTabuleiro - 7));
                        }
                    }else{
                        possiveisMovimentos.add(
                                posicoesTabuleiro.get(posicaoNoTabuleiro - 7));
                    }
                }
            }
        }
        return possiveisMovimentos;
    }

    public void setPrimeiroMovimento(boolean primeiroMovimento) {
        this.primeiroMovimento = primeiroMovimento;
    }

    @Override
    public String toString() {
        return "Peao{" +
                "primeiroMovimento=" + primeiroMovimento +
                "} " + super.toString();
    }
}
