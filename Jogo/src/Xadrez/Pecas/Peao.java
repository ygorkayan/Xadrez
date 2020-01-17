package Xadrez.Pecas;

import Tabuleiro.Posicao;
import Tabuleiro.Tabuleiro;
import Xadrez.Cor;
import Xadrez.PecaXadrez;

public class Peao extends PecaXadrez {

    public Peao(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        Posicao p = new Posicao(0, 0);

        if (getCor() == Cor.BRANCO) {
            // Movimentos do peao branco que anda so para cima
            p.setValores(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }

            p.setValores(posicao.getLinha() - 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().posicaoExiste(p)
                    && !getTabuleiro().temPeca(p)
                    && getTabuleiro().posicaoExiste(p2)
                    && getTabuleiro().temPeca(p2)
                    && getContarMovimentos() == 0) {
                mat[p.getLinha()][p.getColuna()] = true;
            }

            p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExiste(p) && ePecaOponete(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }

            p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExiste(p) && ePecaOponete(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
        } else {
            // Movimentos do peao preto que anda so para baixo
            p.setValores(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }

            p.setValores(posicao.getLinha() + 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().posicaoExiste(p)
                    && !getTabuleiro().temPeca(p)
                    && getTabuleiro().posicaoExiste(p2)
                    && getTabuleiro().temPeca(p2)
                    && getContarMovimentos() == 0) {
                mat[p.getLinha()][p.getColuna()] = true;
            }

            p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExiste(p) && ePecaOponete(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }

            p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExiste(p) && ePecaOponete(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
        }

        return mat;
    }

    @Override
    public String toString() {
        return "P";
    }
}
