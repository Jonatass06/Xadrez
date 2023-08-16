import java.util.ArrayList;

public class Rainha extends Peca{

    public Rainha(String cor, Posicao posicao){
        super(cor, cor.equals("Branco")?'♛':'♕', posicao);
    }

    @Override
    public ArrayList<Posicao> possiveisMovimentos(Tabuleiro tabuleiro, Jogador jogador, Jogador adversario, boolean simular) {

        ArrayList<Posicao> possiveisMovimentos = new ArrayList<>();

        int posicaoNoTabuleiro = tabuleiro.getPosicoes().indexOf(this.getPosicao());

        andarDiagonal(7, posicaoNoTabuleiro, tabuleiro, simular, possiveisMovimentos, jogador, adversario, 0);
        andarDiagonal(-7, posicaoNoTabuleiro, tabuleiro, simular, possiveisMovimentos, jogador, adversario, 1);
        andarDiagonal(9, posicaoNoTabuleiro, tabuleiro, simular, possiveisMovimentos, jogador, adversario, 1);
        andarDiagonal(-9, posicaoNoTabuleiro, tabuleiro, simular, possiveisMovimentos, jogador, adversario, 0);

        andarReto(posicaoNoTabuleiro, tabuleiro, possiveisMovimentos, jogador, adversario, simular, -1);
        andarReto(posicaoNoTabuleiro, tabuleiro, possiveisMovimentos, jogador, adversario, simular, -8);
        andarReto(posicaoNoTabuleiro, tabuleiro, possiveisMovimentos, jogador, adversario, simular, +1);
        andarReto(posicaoNoTabuleiro, tabuleiro, possiveisMovimentos, jogador, adversario, simular, +8);

        return possiveisMovimentos;
    }


    private void andarReto(int posicaoNoTabuleiro, Tabuleiro tabuleiro, ArrayList<Posicao> possiveisMovimentos,
                            Jogador jogador, Jogador adversario, boolean simular, int mod){

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

    private void andarDiagonal(int soma, int posicaoNoTabuleiro, Tabuleiro tabuleiro, boolean simular,
                             ArrayList<Posicao> possiveisMovimentos, Jogador jogador, Jogador adversario, int modI){
        for (int i = (validaExtremidade(posicaoNoTabuleiro) ?
                -1 : posicaoNoTabuleiro + soma);
             i >= 0 && i<=63;
             i += soma) {
            if (verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos, jogador, tabuleiro, adversario, simular) ||
                    validaExtremidade(i+modI)) {
                break;
            }
        }
    }

    @Override
    public String toString() {
        return super.toString()+" Rainha";
    }
}
