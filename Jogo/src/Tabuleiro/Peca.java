package Tabuleiro;

public abstract class Peca {

    protected Posicao posicao;
    private Tabuleiro tabuleiro;

    public Peca(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    protected Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public  abstract boolean[][] movimentosPossiveis();

    public boolean movimentoPossivel(Posicao posicao) {
        return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];
    }

    public boolean ePossivelMover() {
        boolean[][] mat = movimentosPossiveis();
        for (int linha = 0; linha < mat.length; linha++) {
            for (int coluna = 0; coluna < mat.length; coluna++) {
                if(mat[linha][coluna]) {
                    return true;
                }
            }
        }
        return false;
    }

}






























