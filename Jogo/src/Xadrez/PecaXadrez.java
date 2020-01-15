package Xadrez;

import Tabuleiro.Peca;
import Tabuleiro.Taboleiro;

public class PecaXadrez extends Peca {

    public PecaXadrez(Taboleiro taboleiro, Cor cor) {
        super(taboleiro);
        this.cor = cor;
    }

    private Cor cor;

    public Cor getCor() {
        return cor;
    }
}
