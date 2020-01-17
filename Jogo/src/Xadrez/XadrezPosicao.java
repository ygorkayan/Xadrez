package Xadrez;

import Tabuleiro.Posicao;

public class XadrezPosicao {
    private char coluna;
    private int linha;

    public XadrezPosicao(char coluna, int linha) {
        if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
            throw new XadrezException("Erro ao iniciar XadrezPosissao, valores valido sao de a1 a h8");
        }
        this.coluna = coluna;
        this.linha = linha;

    }

    protected static XadrezPosicao ParaPosicao(Posicao posicao) {
        return new XadrezPosicao((char) ('a' - posicao.getColuna()), 8 - posicao.getLinha());
    }

    public char getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }

    protected Posicao dePosisao() {
        return new Posicao(8 - this.linha, coluna - 'a');
    }

    @Override
    public String toString() {
        return "" + coluna + linha;
    }
}
