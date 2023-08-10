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

    public boolean mover(Posicao posicao, Tabuleiro tabuleiro, Jogador adversario) {

                if (posicao.getPeca() != null) {
                    adversario.removerPeca(posicao.getPeca());
                }
                //Atribuindo a peça para a nova posição no tabuleiro
                posicao.setPeca(this);
                //Removendo a peça da posição anterior
                this.posicao.setPeca(null);
                //Trocando a posicao atual da peca
                this.posicao = posicao;
                return true;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public String getCor() {
        return cor;
    }

    public abstract ArrayList<Posicao> possiveisMovimentos(Tabuleiro tabuleiro, Jogador jogador, Jogador adversario);

    public boolean verificaPeca (Posicao posicao, ArrayList<Posicao> possiveisMovimentos, Jogador jogador,
                                 Tabuleiro tabuleiro,  Jogador adversario){
        if(adversario != null && !simularJogada(tabuleiro, jogador, adversario, posicao)) {

            return posicao.getPeca()!=null;
        }
        if (posicao.getPeca() == null) {
            possiveisMovimentos.add(posicao);
            return false;
        } else if (!posicao.getPeca().getCor().equals(this.getCor())) {
            possiveisMovimentos.add(posicao);
        }
        return true;
    }

    public boolean validaExtremidade(int posicaoNoTabuleiro){
        return posicaoNoTabuleiro % 8 == 0;
    }

    public boolean verificaCheque(Jogador jogador, Tabuleiro tabuleiro, Jogador adversario){

        for(Peca peca : adversario.getPecas()){
            if(peca != null){
                for(Posicao posicaoPossivel :  peca.possiveisMovimentos(tabuleiro, jogador, null)){
                    if(posicaoPossivel.getPeca() != null &&
                            posicaoPossivel.getPeca() instanceof Rei &&
                            jogador.getPecas().contains(posicaoPossivel.getPeca())){
                        return true;

                    }
                }
            }
        }
        return false;
    }

    public boolean simularJogada(Tabuleiro tabuleiro, Jogador jogador, Jogador adversario, Posicao posicao ){
        //Valores para a inversçao de jogada
        int antigaPosicao = tabuleiro.getPosicoes().indexOf(this.getPosicao());
        Peca antigaPeca = posicao.getPeca();

        boolean retorno = true;

        if(antigaPeca == null || (antigaPeca != null && adversario.getPecas().contains(antigaPeca))){

            boolean primMov = false;

            //fazer o negocio do peao en passant
            if(this != null) {
                if(this instanceof Rei) {
                    primMov = ((Rei) this).getPrimeiroMovimento();
                }
                if(this instanceof Torre) {
                    primMov = ((Torre) this).getPrimeiroMovimento();
                }
            }

            jogador.moverPeca(this, posicao, tabuleiro, adversario);

            if(verificaCheque(jogador, tabuleiro, adversario)){
                retorno = false;
            }
            //Inverter jogada
            jogador.moverPeca(this, tabuleiro.getPosicoes().get(antigaPosicao), tabuleiro, adversario);

            this.setPrimMov(primMov);
            if(antigaPeca != null){
                posicao.setPeca(antigaPeca);
                if(!adversario.getPecas().contains(antigaPeca)){
                    adversario.addPecas(antigaPeca);
                }
            }
            return retorno;
        }
        return false;
    }

    private void setPrimMov(boolean primMov){
        if(this instanceof Rei){
            ((Rei) this).setPrimeiroMovimento(primMov);
        }
        if(this instanceof Torre){
            ((Torre) this).setPrimeiroMovimento(primMov);
        }
    }


    @Override
    public String toString() {
        return "Peca{" +
                "cor='" + cor + '\'' +
                ", icone=" + icone +
                '}';
    }
}
