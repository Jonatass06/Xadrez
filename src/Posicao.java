public class Posicao {
        private Peca peca;

        public Peca getPeca() {
                return peca;
        }

        public void setPeca(Peca peca) {
                this.peca = peca;
        }

        @Override
        public String toString() {
                return "Posicao{" +
                        "peca=" + peca +
                        '}';
        }
}
