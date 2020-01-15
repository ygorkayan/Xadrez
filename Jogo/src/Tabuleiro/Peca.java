package Tabuleiro;

public class Peca {

    protected Posicao posicao;
    private Taboleiro taboleiro;

    public Peca(Taboleiro taboleiro) {
        this.taboleiro = taboleiro;
    }

    protected Taboleiro getTaboleiro() {
        return taboleiro;
    }
}
