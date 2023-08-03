import java.util.ArrayList;

public class Tabuleiro {

    private static ArrayList<Posicao> posicoes = new ArrayList<>() ;

    public Tabuleiro(){
        for (int i = 0; i < 64; i++){
            posicoes.add(new Posicao());

            if(i >= 8 && i <= 15){
                posicoes.get(i).setPeca(new Peao("Preto", posicoes.get(i)));
            }
            if(i >= 48 && i <= 55){
                posicoes.get(i).setPeca(new Peao("Branco", posicoes.get(i)));
            }
            if(i == 0 || i == 7){
                posicoes.get(i).setPeca(new Torre("Preto", posicoes.get(i)));
            }
            if(i == 56 || i == 63){
                posicoes.get(i).setPeca(new Torre("Branco", posicoes.get(i)));
            }
            if(i == 1 || i == 6){
                posicoes.get(i).setPeca(new Cavalo("Preto", posicoes.get(i)));
            }
            if(i == 57 || i == 62){
                posicoes.get(i).setPeca(new Cavalo("Branco", posicoes.get(i)));
            }
            if(i == 2 || i == 5){
                posicoes.get(i).setPeca(new Bispo("Preto", posicoes.get(i)));
            }
            if(i == 58 || i == 61){
                posicoes.get(i).setPeca(new Bispo("Branco", posicoes.get(i)));
            }
            if(i == 3 ){
                posicoes.get(i).setPeca(new Rainha("Preto", posicoes.get(i)));
            }
            if(i == 59 ){
                posicoes.get(i).setPeca(new Rainha("Branco", posicoes.get(i)));
            }
            if(i == 4 ){
                posicoes.get(i).setPeca(new Rei("Preto", posicoes.get(i)));
            }
            if(i == 60 ){
                posicoes.get(i).setPeca(new Rei("Branco", posicoes.get(i)));
            }
            //testes tira depois
            if(i == 30){
                posicoes.get(i).setPeca(new Rainha("Branco", posicoes.get(i)));
            }
            if(i == 20){
                posicoes.get(i).setPeca(new Rainha("Branco", posicoes.get(i)));
            }
        }
    }

    public static void removerPeca(Posicao posicao){}

    public static ArrayList<Posicao> getPosicoes() {
        return posicoes;
    }

    @Override
    public String toString() {
        String retorno ="";
        for(Posicao posicao : posicoes){

            if(posicao.getPeca() != null){
                retorno += "["+posicao.getPeca().getIcone() + "]";
            }else{
                retorno += "[ ]";
            }

            if((posicoes.indexOf(posicao)+1) % 8 == 0){

                retorno +="\n";
            }
        }
        return retorno;
    }

    public String mostrarPossiveisMovimentos(ArrayList<Posicao> possiveisMovimentos){

        String corVermelha = "\u001B[31m";
        String corPadrao = "\u001B[0m";

        String retorno ="";
        for(Posicao posicao : posicoes){

            if(posicao.getPeca() == null){
                if(possiveisMovimentos.contains(posicao)){
                    if(posicoes.indexOf(posicao) < 10){
                        retorno += "[0"+posicoes.indexOf(posicao) +"]";
                    } else{
                        retorno += "["+posicoes.indexOf(posicao) +"]";
                    }
                }else{
                    retorno += "[  ]";
                }
            } else{
                if(possiveisMovimentos.contains(posicao)){
                    retorno += "["+ corVermelha + posicoes.indexOf(posicao) + corPadrao + "]";

                }else{
                    retorno += "[ "+ posicao.getPeca().getIcone() +  "]";

                }
            }

            if((posicoes.indexOf(posicao)+1) % 8 == 0){
                retorno +="\n";
            }
        }

        for(Posicao posicao:  possiveisMovimentos){
            if(posicao.getPeca()!= null){
                retorno += corVermelha + "["+posicoes.indexOf(posicao)+"] - " + posicao.getPeca().getIcone() + " | ";
            }
        }

        return retorno + corPadrao;
    }

    public String mostrarPecasJogador(ArrayList<Peca> pecas) {
        String retorno ="";
        for(Posicao posicao : posicoes){

            if(posicao.getPeca() == null){
                retorno += "[  ]";
            } else{
                if(pecas.contains(posicao.getPeca())){
                    if(pecas.indexOf(posicao.getPeca()) < 10){
                        retorno += "[0"+pecas.indexOf(posicao.getPeca())+"]";
                    } else{
                        retorno += "["+pecas.indexOf(posicao.getPeca())+"]";

                    }
                }else{
                    retorno += "[ "+ posicao.getPeca().getIcone() +  "]";
                }
            }
            if((posicoes.indexOf(posicao)+1) % 8 == 0){
                retorno +="\n";
            }
        }
        return retorno;
    }

    public boolean verificaCheque(Jogador jogador){

        ArrayList<Posicao>  possiveisMovimentos = new ArrayList<>();
        for(Posicao posicao : this.posicoes){
            if(posicao.getPeca() != null){
                possiveisMovimentos.addAll(posicao.getPeca().possiveisMovimentos(this));
            }
        }

        for(Posicao posicao : possiveisMovimentos){
            if(posicao.getPeca() instanceof Rei &&
                !jogador.getPecas().contains(posicao.getPeca())
            ){
                System.out.println(true);
                return true;
            }
        }

        return false;
    }
}
