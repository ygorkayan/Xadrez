package Xadrez;

import Tabuleiro.Peca;
import Tabuleiro.Tabuleiro;
import Tabuleiro.Posicao;

public abstract class PecaXadrez extends Peca {

    public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro);
        this.cor = cor;
    }

    private Cor cor;

    public Cor getCor() {
        return cor;
    }

    protected boolean ePecaOponete(Posicao posicao) {
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return p != null && p.getCor() != cor;
    }
}
