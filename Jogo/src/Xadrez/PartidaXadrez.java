package Xadrez;

import Tabuleiro.Tabuleiro;
import Xadrez.Pecas.Rei;


public class PartidaXadrez {
    private Tabuleiro tabuleiro;

    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8,8);
        Iniciar();
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

    private void NovoPosicao(char coluna, int linha, PecaXadrez peca) {
        tabuleiro.colocarPeca(peca, new XadrezPosicao(coluna, linha).dePosisao());
    }

    private void Iniciar() {
        this.NovoPosicao('b', 6, new Rei(tabuleiro, Cor.BRANCO));
        this.NovoPosicao('c', 6, new Rei(tabuleiro, Cor.BRANCO));
    }

}
