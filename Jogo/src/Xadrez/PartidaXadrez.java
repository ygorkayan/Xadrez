package Xadrez;

import Tabuleiro.Taboleiro;

public class PartidaXadrez {
    private Taboleiro taboleiro;

    public PartidaXadrez() {
        taboleiro = new Taboleiro(8,8);
    }

    public PecaXadrez[][] getPeca() {
        PecaXadrez[][] mat = new PecaXadrez[taboleiro.getLinhas()][taboleiro.getColunas()];

        for (int linha = 0; linha < taboleiro.getLinhas(); linha++) {
            for (int coluna = 0; coluna < taboleiro.getColunas(); coluna++) {
                mat[linha][coluna] = (PecaXadrez) taboleiro.peca(linha, coluna);
            }
        }

        return mat;
    }
}
