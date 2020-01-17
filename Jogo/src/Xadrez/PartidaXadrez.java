package Xadrez;

import Tabuleiro.Peca;
import Tabuleiro.Posicao;
import Tabuleiro.Tabuleiro;
import Xadrez.Pecas.Rei;
import Xadrez.Pecas.Torre;

import java.util.ArrayList;
import java.util.List;


public class PartidaXadrez {

    List<Peca> pecasNoTabuleiro;
    List<Peca> pecasCapturadas;
    private Tabuleiro tabuleiro;
    private int turno;
    private Cor jogadorCorreto;

    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8, 8);
        turno = 1;
        jogadorCorreto = Cor.BRANCO;
        pecasNoTabuleiro = new ArrayList<>();
        pecasCapturadas = new ArrayList<>();

        Iniciar();
    }

    public int getTurno() {
        return turno;
    }

    public Cor getJogadorCorreto() {
        return jogadorCorreto;
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

    public boolean[][] movimentosPossiveis(XadrezPosicao posicaoOrigem) {
        Posicao posicao = posicaoOrigem.dePosisao();
        validarPosicaoOrigem(posicao);
        return tabuleiro.peca(posicao).movimentosPossiveis();
    }

    public PecaXadrez moverPeca(XadrezPosicao posicaoOrigem, XadrezPosicao posicaoDestino) {
        Posicao origem = posicaoOrigem.dePosisao();
        Posicao destino = posicaoDestino.dePosisao();

        validarPosicaoOrigem(origem);
        validarPosicaoDestino(origem, destino);
        Peca pecaCaputurada = makeMove(origem, destino);
        proximoTurno();
        return (PecaXadrez) pecaCaputurada;
    }

    private void validarPosicaoDestino(Posicao origem, Posicao destino) {
        if (!tabuleiro.peca(origem).movimentoPossivel(destino)) {
            throw new XadrezException("A peça nao pode se mover para o destino");
        }
    }

    private Peca makeMove(Posicao origem, Posicao destino) {
        Peca p = tabuleiro.removePeca(origem);
        Peca pecaCaputurada = tabuleiro.removePeca(destino);
        tabuleiro.colocarPeca(p, destino);

        if (pecaCaputurada != null) {
            pecasNoTabuleiro.remove(pecaCaputurada);
            this.pecasCapturadas.add(pecaCaputurada);
        }

        return pecaCaputurada;
    }

    private void validarPosicaoOrigem(Posicao posicao) {
        if (!tabuleiro.posicaoExiste(posicao)) {
            throw new XadrezException("Nao existe peca na posiçao de origem");
        }
        if (jogadorCorreto != ((PecaXadrez) tabuleiro.peca(posicao)).getCor()) {
            throw new XadrezException("A pessa escolhida nao é sua");
        }
        if (!tabuleiro.peca(posicao).ePossivelMover()) {
            throw new XadrezException("Nao existe movimento para a peça escolhida");
        }
    }

    private void NovoPosicao(char coluna, int linha, PecaXadrez peca) {
        tabuleiro.colocarPeca(peca, new XadrezPosicao(coluna, linha).dePosisao());
        pecasNoTabuleiro.add(peca);
    }

    private void proximoTurno() {
        turno++;
        jogadorCorreto = (jogadorCorreto == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
    }

    private void Iniciar() {
        NovoPosicao('c', 1, new Torre(tabuleiro, Cor.BRANCO));
        NovoPosicao('c', 2, new Torre(tabuleiro, Cor.BRANCO));
        NovoPosicao('d', 2, new Torre(tabuleiro, Cor.BRANCO));
        NovoPosicao('e', 2, new Torre(tabuleiro, Cor.BRANCO));
        NovoPosicao('e', 1, new Torre(tabuleiro, Cor.BRANCO));
        NovoPosicao('d', 1, new Rei(tabuleiro, Cor.BRANCO));

        NovoPosicao('c', 7, new Torre(tabuleiro, Cor.PRETO));
        NovoPosicao('c', 8, new Torre(tabuleiro, Cor.PRETO));
        NovoPosicao('d', 7, new Torre(tabuleiro, Cor.PRETO));
        NovoPosicao('e', 7, new Torre(tabuleiro, Cor.PRETO));
        NovoPosicao('e', 8, new Torre(tabuleiro, Cor.PRETO));
        NovoPosicao('d', 8, new Rei(tabuleiro, Cor.PRETO));
    }

}
