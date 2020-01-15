package Xadrez;

import Tabuleiro.Tabuleiro;
import Tabuleiro.Posicao;
import Xadrez.Pecas.Rei;
import Xadrez.Pecas.Torre;

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

    private void Iniciar() {
        tabuleiro.colocarPeca(new Rei(tabuleiro, Cor.BRANCO), new Posicao(0,1));
        tabuleiro.colocarPeca(new Torre(tabuleiro, Cor.BRANCO), new Posicao(0,2));
    }

}
