import java.util.ArrayList;

public class Peao extends Peca {

    private int movimento;

    public Peao(String cor, Posicao posicao) {
        super(cor, cor.equals("Branco") ? '♟' : '♙', posicao);
        this.movimento = 0;
    }

    @Override
    public boolean mover(Posicao posicao, Tabuleiro tabuleiro, Jogador adversario) {

        int antigaPosicao = tabuleiro.getPosicoes().indexOf(this.getPosicao());
        int novaPosicao = tabuleiro.getPosicoes().indexOf(posicao);
        Peca antigaPeca = posicao.getPeca();

        //Atribuindo a peça para a nova posição no tabuleiro
        posicao.setPeca(this);
        //Removendo a peça da posição anterior
        this.getPosicao().setPeca(null);
        //Trocando a posicao atual da peca
        this.setPosicao(posicao);
        this.movimento++;

        if (adversario != null) {
            if (verficaEnPassant(antigaPeca, antigaPosicao, novaPosicao)) {
                if (novaPosicao > antigaPosicao) {
                    adversario.removerPeca(tabuleiro.getPosicoes().get(novaPosicao - 8).getPeca());
                    tabuleiro.getPosicoes().get(novaPosicao - 8).setPeca(null);
                } else {
                    adversario.removerPeca(tabuleiro.getPosicoes().get(novaPosicao + 8).getPeca());
                    tabuleiro.getPosicoes().get(novaPosicao + 8).setPeca(null);
                }
            }
        }
        return true;
    }

    @Override
    public ArrayList<Posicao> possiveisMovimentos(Tabuleiro tabuleiro, Jogador jogador, Jogador adversario, boolean simular) {

        ArrayList<Posicao> possiveisMovimentos = new ArrayList<>();
        Posicao posicaoAtual = this.getPosicao();
        int posicaoNoTabuleiro = tabuleiro.getPosicoes().indexOf(posicaoAtual);

        if (this.getCor().equals("Preto")) {
            andarPeao("Preto", 1, possiveisMovimentos, tabuleiro, posicaoNoTabuleiro, jogador, adversario, simular);
        } else {
            andarPeao("Branco", -1, possiveisMovimentos, tabuleiro, posicaoNoTabuleiro, jogador, adversario, simular);
        }
        return possiveisMovimentos;
    }

    private void andarPeao(String cor, int mod, ArrayList<Posicao> possiveisMovimentos, Tabuleiro tabuleiro,
                           int posicaoNoTabuleiro, Jogador j1, Jogador j2, boolean simular) {

        ArrayList<Posicao> posicoesTabuleiro = tabuleiro.getPosicoes();

        if (posicoesTabuleiro.get(posicaoNoTabuleiro + (8 * mod)).getPeca() == null) {
            if (!simular|| simularJogada(tabuleiro, j1, j2, posicoesTabuleiro.get(posicaoNoTabuleiro + (8 * mod)))) {
                possiveisMovimentos.add(posicoesTabuleiro.get(posicaoNoTabuleiro + (8 * mod)));
            }
            if (this.movimento == 0) {
                if (posicoesTabuleiro.get(posicaoNoTabuleiro + (16 * mod)).getPeca() == null) {
                    if (!simular || simularJogada(tabuleiro, j1, j2, posicoesTabuleiro.get(posicaoNoTabuleiro + (16 * mod)))) {
                        possiveisMovimentos.add(posicoesTabuleiro.get(posicaoNoTabuleiro + (16 * mod)));
                    }
                }
            }
        }


        if (posicoesTabuleiro.get(posicaoNoTabuleiro + (9 * mod)).getPeca() != null) {
            if (!posicoesTabuleiro.get(posicaoNoTabuleiro + (9 * mod))
                    .getPeca().getCor().equals(cor) &&
                    !validaExtremidade(posicaoNoTabuleiro + (cor.equals("Preto") ? 1 : 0))
            ) {
                if (!simular || simularJogada(tabuleiro, j1, j2, posicoesTabuleiro.get(posicaoNoTabuleiro + (9 * mod)))) {
                    possiveisMovimentos.add(posicoesTabuleiro.get(posicaoNoTabuleiro + (9 * mod)));
                }
            }
        }

        if (posicoesTabuleiro.get(posicaoNoTabuleiro + (7 * mod)).getPeca() != null) {
            if (!posicoesTabuleiro.get(posicaoNoTabuleiro + (7 * mod))
                    .getPeca().getCor().equals(cor) &&
                    !validaExtremidade(posicaoNoTabuleiro + (cor.equals("Branco") ? 1 : 0))) {
                if (!simular || simularJogada(tabuleiro, j1, j2, posicoesTabuleiro.get(posicaoNoTabuleiro + (7 * mod)))) {
                    possiveisMovimentos.add(posicoesTabuleiro.get(posicaoNoTabuleiro + (7 * mod)));
                }
            }
        }

        //en passant
        if (tabuleiro.getPosicoes().indexOf(this.getPosicao()) <= 39 &&
                tabuleiro.getPosicoes().indexOf(this.getPosicao()) >= 24) {

            Posicao posicaoMaisUm = tabuleiro.getPosicoes().get(tabuleiro.getPosicoes().indexOf(this.getPosicao()) + 1);
            Posicao posicaoMenosUm = tabuleiro.getPosicoes().get(tabuleiro.getPosicoes().indexOf(this.getPosicao()) - 1);

            if (!validaExtremidade(posicaoNoTabuleiro + 1) &&
                    posicaoMaisUm.getPeca() instanceof Peao &&
                    ((Peao) posicaoMaisUm.getPeca()).getMov() == 1) {
                int indicePosicao = tabuleiro.getPosicoes().indexOf(this.getPosicao()) + (cor.equals("Branco") ? -7 : 9);
                if (!simular || simularJogada(tabuleiro, j1, j2, tabuleiro.getPosicoes().get(indicePosicao))) {
                    possiveisMovimentos.add(tabuleiro.getPosicoes().get(indicePosicao));
                }
            }
            if (!validaExtremidade(posicaoNoTabuleiro) &&
                    posicaoMenosUm.getPeca() instanceof Peao &&
                    ((Peao) posicaoMenosUm.getPeca()).getMov() == 1) {
                int indicePosicao = tabuleiro.getPosicoes().indexOf(this.getPosicao()) + (cor.equals("Branco") ? -9 : 7);
                if (!simular || simularJogada(tabuleiro, j1, j2, tabuleiro.getPosicoes().get(indicePosicao))) {
                    possiveisMovimentos.add(tabuleiro.getPosicoes(). get(indicePosicao));
                }
            }
        }
    }

    @Override
    public boolean simularJogada(Tabuleiro tabuleiro, Jogador jogador, Jogador adversario, Posicao posicao) {
        //Valores para a inversçao de jogada

        int antigaPosicao = tabuleiro.getPosicoes().indexOf(this.getPosicao());
        int novaPosicao = tabuleiro.getPosicoes().indexOf(posicao);
        Peca antigaPeca = posicao.getPeca();

        boolean retorno = true;

        if (antigaPeca == null || adversario.getPecas().contains(antigaPeca)) {

            Peca antigaPecaEnPassant = null;

            if (verficaEnPassant(antigaPeca, antigaPosicao, novaPosicao)) {
                if (novaPosicao > antigaPosicao) {
                    antigaPecaEnPassant = tabuleiro.getPosicoes().get(novaPosicao - 8).getPeca();
                } else {
                    antigaPecaEnPassant = tabuleiro.getPosicoes().get(novaPosicao + 8).getPeca();
                }
            }

            jogador.moverPeca(this, posicao, tabuleiro, adversario);
            this.decMov();

            if (verificaCheque(jogador, tabuleiro, adversario)) {
                retorno = false;
            }

            if (antigaPecaEnPassant != null) {
                if (novaPosicao > antigaPosicao) {
                    tabuleiro.getPosicoes().get(novaPosicao - 8).setPeca(antigaPecaEnPassant);
                } else {
                    tabuleiro.getPosicoes().get(novaPosicao + 8).setPeca(antigaPecaEnPassant);
                }
                if (!adversario.getPecas().contains(antigaPecaEnPassant)) {
                    adversario.addPecas(antigaPecaEnPassant);
                }
            }

            //Inverter jogada
            jogador.moverPeca(this, tabuleiro.getPosicoes().get(antigaPosicao), tabuleiro, null);
            this.decMov();


            posicao.setPeca(antigaPeca);
            if (antigaPeca != null && !adversario.getPecas().contains(antigaPeca)) {
                adversario.addPecas(antigaPeca);
            }

            return retorno;
        }
        return false;
    }

    private boolean verficaEnPassant(Peca antigaPeca, int antigaPosicao, int novaPosicao) {
        return (antigaPeca == null &&
                (antigaPosicao - 7 == novaPosicao ||
                        antigaPosicao - 9 == novaPosicao ||
                        antigaPosicao + 7 == novaPosicao ||
                        antigaPosicao + 9 == novaPosicao));
    }


    public int getMov() {
        return movimento;
    }

    public void decMov() {
        this.movimento--;
    }

    public void incMov() {
        this.movimento++;
    }

    @Override
    public String toString() {
        return "Peao{" +
                "primeiroMovimento=" + movimento +
                "} " + super.toString();
    }
}
