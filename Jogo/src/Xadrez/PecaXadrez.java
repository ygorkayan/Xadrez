package Xadrez;

import Tabuleiro.Peca;
import Tabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca {

    public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro);
        this.cor = cor;
    }

    private Cor cor;

    public Cor getCor() {
        return cor;
    }
}
