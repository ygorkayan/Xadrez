package Xadrez;

import Tabuleiro.Tabuleiro;

public class PartidaXadrez {
    private Tabuleiro tabuleiro;

    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8,8);
    }

    public PecaXadrez[][] getPeca() {
        PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];

        for (int linha = 0; linha < tabuleiro.getLinhas(); linha++) {
            for (int coluna = 0; coluna < tabuleiro.getColunas(); coluna++) {
                mat[linha][coluna] = (PecaXadrez) tabuleiro.peca(linha, coluna);
            }
        }

        return mat;
    }
}
