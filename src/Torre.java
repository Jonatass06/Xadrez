import java.util.ArrayList;

public class Torre extends Peca {

    private boolean primeiroMovimento;

    public Torre(String cor, Posicao posicao) {
        super(cor, cor.equals("Branco") ? '♜' : '♖', posicao);
        primeiroMovimento = true;
    }

    @Override
    public ArrayList<Posicao> possiveisMovimentos(Tabuleiro tabuleiro, Jogador jogador, Jogador adversario, boolean simular) {

        ArrayList<Posicao> possiveisMovimentos = new ArrayList<>();

        int posicaoNoTabuleiro = tabuleiro.getPosicoes().indexOf(this.getPosicao());

        andarTorre(posicaoNoTabuleiro, tabuleiro, possiveisMovimentos, jogador, adversario, simular, -1);
        andarTorre(posicaoNoTabuleiro, tabuleiro, possiveisMovimentos, jogador, adversario, simular, -8);
        andarTorre(posicaoNoTabuleiro, tabuleiro, possiveisMovimentos, jogador, adversario, simular, +1);
        andarTorre(posicaoNoTabuleiro, tabuleiro, possiveisMovimentos, jogador, adversario, simular, +8);
        return possiveisMovimentos;

    }

    private void andarTorre(int posicaoNoTabuleiro, Tabuleiro tabuleiro, ArrayList<Posicao> possiveisMovimentos,
                            Jogador jogador, Jogador adversario, boolean simular, int mod) {
        if(mod == 1 || mod == -1){
            for (int i = (validaExtremidade(posicaoNoTabuleiro + (mod == 1 ? 1 : 0)) ?
                    -1 : posicaoNoTabuleiro + mod);
                 i >= 0 && i <= 63;
                 i += mod) {

                if (verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos, jogador, tabuleiro, adversario, simular) ||
                        validaExtremidade(i + (mod == 1 ? 1 : 0)) ) {
                    break;
                }
            }
        } else{
            for (int i = (posicaoNoTabuleiro + mod < 0 ||  posicaoNoTabuleiro + mod > 63?
                    -1 : posicaoNoTabuleiro + mod);
                 i >= 0 && i <= 63;
                 i += mod) {

                if (verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos, jogador, tabuleiro, adversario, simular)) {
                    break;
                }
            }
        }
    }


    public void setPrimeiroMovimento(boolean primeiroMovimento) {
        this.primeiroMovimento = primeiroMovimento;
    }

    public boolean getPrimeiroMovimento() {
        return primeiroMovimento;
    }

    @Override
    public boolean mover(Posicao posicao, Tabuleiro tabuleiro, Jogador adversario) {
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
    public String toString() {
        return "Torre{" +
                "primeiroMovimento=" + primeiroMovimento +
                "} " + super.toString();
    }
}
