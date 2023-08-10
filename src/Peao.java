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

        if(adversario != null){
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
    public ArrayList<Posicao> possiveisMovimentos(Tabuleiro tabuleiro, Jogador jogador, Jogador adversario) {

        ArrayList<Posicao> possiveisMovimentos = new ArrayList<>();
        Posicao posicaoAtual = this.getPosicao();
        ArrayList<Posicao> posicoesTabuleiro = tabuleiro.getPosicoes();
        int posicaoNoTabuleiro = posicoesTabuleiro.indexOf(posicaoAtual);

        if (this.getCor().equals("Preto")) {
            if (posicoesTabuleiro.get(posicaoNoTabuleiro + 8).getPeca() == null) {
                if (adversario != null) {
                    if (simularJogada(tabuleiro, jogador, adversario,
                            posicoesTabuleiro.get(posicaoNoTabuleiro + 8))) {
                        possiveisMovimentos.add(
                                posicoesTabuleiro.get(posicaoNoTabuleiro + 8));
                    }
                } else {
                    possiveisMovimentos.add(
                            posicoesTabuleiro.get(posicaoNoTabuleiro + 8));
                }
                if (this.movimento == 0) {
                    if (posicoesTabuleiro.get(posicaoNoTabuleiro + 16).getPeca() == null) {
                        if (adversario != null) {
                            if (simularJogada(tabuleiro, jogador, adversario,
                                    posicoesTabuleiro.get(posicaoNoTabuleiro + 16))) {
                                possiveisMovimentos.add(
                                        posicoesTabuleiro.get(posicaoNoTabuleiro + 16));
                            }
                        } else {
                            possiveisMovimentos.add(
                                    posicoesTabuleiro.get(posicaoNoTabuleiro + 16));
                        }
                    }
                }
            }

            if (posicoesTabuleiro.get(posicaoNoTabuleiro + 9).getPeca() != null) {
                if (posicoesTabuleiro.get(posicaoNoTabuleiro + 9)
                        .getPeca().getCor().equals("Branco") &&
                        !validaExtremidade(posicaoNoTabuleiro + 1)
                ) {
                    if (adversario != null) {
                        if (simularJogada(tabuleiro, jogador, adversario,
                                posicoesTabuleiro.get(posicaoNoTabuleiro + 9))) {
                            possiveisMovimentos.add(
                                    posicoesTabuleiro.get(posicaoNoTabuleiro + 9));
                        }
                    } else {
                        possiveisMovimentos.add(
                                posicoesTabuleiro.get(posicaoNoTabuleiro + 9));
                    }
                }
            }

            if (posicoesTabuleiro.get(posicaoNoTabuleiro + 7).getPeca() != null) {
                if (posicoesTabuleiro.get(posicaoNoTabuleiro + 7)
                        .getPeca().getCor().equals("Branco") &&
                        !validaExtremidade(posicaoNoTabuleiro)) {
                    if (adversario != null) {
                        if (simularJogada(tabuleiro, jogador, adversario,
                                posicoesTabuleiro.get(posicaoNoTabuleiro + 7))) {
                            possiveisMovimentos.add(
                                    posicoesTabuleiro.get(posicaoNoTabuleiro + 7));
                        }
                    } else {
                        possiveisMovimentos.add(
                                posicoesTabuleiro.get(posicaoNoTabuleiro + 7));
                    }
                }
            }
            if (tabuleiro.getPosicoes().indexOf(this.getPosicao()) <= 39 &&
                    tabuleiro.getPosicoes().indexOf(this.getPosicao()) >= 33) {
                Posicao posicaoMaisUm = tabuleiro.getPosicoes().get(tabuleiro.getPosicoes().indexOf(this.getPosicao()) + 1);
                Posicao posicaoMenosUm = tabuleiro.getPosicoes().get(tabuleiro.getPosicoes().indexOf(this.getPosicao()) - 1);

                if (!validaExtremidade(posicaoNoTabuleiro + 1) &&
                        posicaoMaisUm.getPeca() != null &&
                        posicaoMaisUm.getPeca() instanceof Peao &&
                        ((Peao) posicaoMaisUm.getPeca()).getMov() == 1) {
                    if (adversario != null) {
                        if (simularJogada(tabuleiro, jogador, adversario, tabuleiro.getPosicoes().
                                get(tabuleiro.getPosicoes().indexOf(this.getPosicao()) + 9))) {
                            possiveisMovimentos.add(tabuleiro.getPosicoes().
                                    get(tabuleiro.getPosicoes().indexOf(this.getPosicao()) + 9));
                        }
                    } else {
                        possiveisMovimentos.add(tabuleiro.getPosicoes().
                                get(tabuleiro.getPosicoes().indexOf(this.getPosicao()) + 9));
                    }
                }
                if (!validaExtremidade(posicaoNoTabuleiro) &&
                        posicaoMenosUm.getPeca() != null &&
                        posicaoMenosUm.getPeca() instanceof Peao &&
                        ((Peao) posicaoMenosUm.getPeca()).getMov() == 1) {
                    if (adversario != null) {
                        if (simularJogada(tabuleiro, jogador, adversario, tabuleiro.getPosicoes().
                                get(tabuleiro.getPosicoes().indexOf(this.getPosicao()) + 7))) {
                            possiveisMovimentos.add(tabuleiro.getPosicoes().
                                    get(tabuleiro.getPosicoes().indexOf(this.getPosicao()) + 7));
                        }
                    } else {
                        possiveisMovimentos.add(tabuleiro.getPosicoes().
                                get(tabuleiro.getPosicoes().indexOf(this.getPosicao()) + 7));
                    }
                }
            }
        } else {
            if (posicoesTabuleiro.get(posicaoNoTabuleiro - 8).getPeca() == null) {
                if (adversario != null) {
                    if (simularJogada(tabuleiro, jogador, adversario,
                            posicoesTabuleiro.get(posicaoNoTabuleiro - 8))) {
                        possiveisMovimentos.add(
                                posicoesTabuleiro.get(posicaoNoTabuleiro - 8));
                    }
                } else {
                    possiveisMovimentos.add(
                            posicoesTabuleiro.get(posicaoNoTabuleiro - 8));
                }

                if (this.movimento == 0) {
                    if (posicoesTabuleiro.get(posicaoNoTabuleiro - 16).getPeca() == null) {
                        if (adversario != null) {
                            if (simularJogada(tabuleiro, jogador, adversario,
                                    posicoesTabuleiro.get(posicaoNoTabuleiro - 16))) {
                                possiveisMovimentos.add(
                                        posicoesTabuleiro.get(posicaoNoTabuleiro - 16));
                            }
                        } else {
                            possiveisMovimentos.add(
                                    posicoesTabuleiro.get(posicaoNoTabuleiro - 16));
                        }
                    }
                }
            }

            if (posicoesTabuleiro.get(posicaoNoTabuleiro - 9).getPeca() != null) {
                if (posicoesTabuleiro.get(posicaoNoTabuleiro - 9)
                        .getPeca().getCor().equals("Preto") &&
                        !validaExtremidade(posicaoNoTabuleiro)
                ) {
                    if (adversario != null) {
                        if (simularJogada(tabuleiro, jogador, adversario,
                                posicoesTabuleiro.get(posicaoNoTabuleiro - 9))) {
                            possiveisMovimentos.add(
                                    posicoesTabuleiro.get(posicaoNoTabuleiro - 9));
                        }
                    } else {
                        possiveisMovimentos.add(
                                posicoesTabuleiro.get(posicaoNoTabuleiro - 9));
                    }
                }
            }

            if (posicoesTabuleiro.get(posicaoNoTabuleiro - 7).getPeca() != null) {
                if (posicoesTabuleiro.get(posicaoNoTabuleiro - 7)
                        .getPeca().getCor().equals("Preto") &&
                        !validaExtremidade(posicaoNoTabuleiro + 1)) {
                    if (adversario != null) {
                        if (simularJogada(tabuleiro, jogador, adversario,
                                posicoesTabuleiro.get(posicaoNoTabuleiro - 7))) {
                            possiveisMovimentos.add(
                                    posicoesTabuleiro.get(posicaoNoTabuleiro - 7));
                        }
                    } else {
                        possiveisMovimentos.add(
                                posicoesTabuleiro.get(posicaoNoTabuleiro - 7));
                    }
                }
            }
            if (tabuleiro.getPosicoes().indexOf(this.getPosicao()) <= 32 &&
                    tabuleiro.getPosicoes().indexOf(this.getPosicao()) >= 24) {
                Posicao posicaoMaisUm = tabuleiro.getPosicoes().get(tabuleiro.getPosicoes().indexOf(this.getPosicao()) + 1);
                Posicao posicaoMenosUm = tabuleiro.getPosicoes().get(tabuleiro.getPosicoes().indexOf(this.getPosicao()) - 1);

                if (!validaExtremidade(posicaoNoTabuleiro + 1) &&
                        posicaoMaisUm.getPeca() != null &&
                        posicaoMaisUm.getPeca() instanceof Peao &&
                        ((Peao) posicaoMaisUm.getPeca()).getMov() == 1) {
                    if (adversario != null) {
                        if (simularJogada(
                                tabuleiro, jogador, adversario, tabuleiro.getPosicoes().
                                        get(tabuleiro.getPosicoes().indexOf(this.getPosicao()) - 7)
                        )) {

                            possiveisMovimentos.add(tabuleiro.getPosicoes().
                                    get(tabuleiro.getPosicoes().indexOf(this.getPosicao()) - 7));
                        }
                    } else {
                        possiveisMovimentos.add(tabuleiro.getPosicoes().
                                get(tabuleiro.getPosicoes().indexOf(this.getPosicao()) - 7));
                    }
                }
                if (!validaExtremidade(posicaoNoTabuleiro) &&
                        posicaoMenosUm.getPeca() != null &&
                        posicaoMenosUm.getPeca() instanceof Peao &&
                        ((Peao) posicaoMenosUm.getPeca()).getMov() == 1) {
                    if (adversario != null) {
                        if (simularJogada(
                                tabuleiro, jogador, adversario, tabuleiro.getPosicoes().
                                        get(tabuleiro.getPosicoes().indexOf(this.getPosicao()) - 9)
                        )) {
                            possiveisMovimentos.add(tabuleiro.getPosicoes().
                                    get(tabuleiro.getPosicoes().indexOf(this.getPosicao()) - 9));
                        }
                    } else {possiveisMovimentos.add(tabuleiro.getPosicoes().
                                get(tabuleiro.getPosicoes().indexOf(this.getPosicao()) - 9));
                    }
                }
            }
        }
        return possiveisMovimentos;
    }

    @Override
    public boolean simularJogada(Tabuleiro tabuleiro, Jogador jogador, Jogador adversario, Posicao posicao) {
        //Valores para a inversçao de jogada

        int antigaPosicao = tabuleiro.getPosicoes().indexOf(this.getPosicao());
        int novaPosicao = tabuleiro.getPosicoes().indexOf(posicao);
        Peca antigaPeca = posicao.getPeca();

        boolean retorno = true;

        if (antigaPeca == null || (antigaPeca != null && adversario.getPecas().contains(antigaPeca))) {

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
                if(!adversario.getPecas().contains(antigaPecaEnPassant)){
                    adversario.addPecas(antigaPecaEnPassant);
                }
            }

            //Inverter jogada
            jogador.moverPeca(this, tabuleiro.getPosicoes().get(antigaPosicao), tabuleiro, null);
            this.decMov();

            if (antigaPeca != null) {
                posicao.setPeca(antigaPeca);
                if(!adversario.getPecas().contains(antigaPeca)){
                    adversario.addPecas(antigaPeca);
                }
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
