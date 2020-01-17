package Xadrez;

import Tabuleiro.Peca;
import Tabuleiro.Posicao;
import Tabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca {

    private Cor cor;
    private int contarMovimentos;

    public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro);
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }

    public void incrementarMovimento() {
        this.contarMovimentos ++;
    }

    public void decrementarMovimento() {
        this.contarMovimentos --;
    }

    public int getContarMovimentos() {
        return contarMovimentos;
    }

    public XadrezPosicao getXadrezPosicao() {
        return XadrezPosicao.ParaPosicao(posicao);
    }

    protected boolean ePecaOponete(Posicao posicao) {
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return p != null && p.getCor() != cor;
    }
}
