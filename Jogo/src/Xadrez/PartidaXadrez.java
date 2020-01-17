package Xadrez;

import Tabuleiro.Peca;
import Tabuleiro.Posicao;
import Tabuleiro.Tabuleiro;
import Xadrez.Pecas.Rei;
import Xadrez.Pecas.Torre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class PartidaXadrez {

    List<Peca> pecasNoTabuleiro;
    List<Peca> pecasCapturadas;
    private Tabuleiro tabuleiro;
    private int turno;
    private Cor jogadorCorreto;
    private boolean check;
    private boolean checkMate;

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

    public boolean getCheck() {
        return this.check;
    }

    public boolean getCheckMate() {
        return checkMate;
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

        if (this.verificarCheck(this.jogadorCorreto)) {
            desfazerMovimento(origem, destino, pecaCaputurada);
        }
        check = (verificarCheck(oponete(this.jogadorCorreto))) ? true : false;

        if (verificarCheckMate(oponete(this.jogadorCorreto))) {
            checkMate = true;
        } else {
            proximoTurno();
        }
        return (PecaXadrez) pecaCaputurada;
    }

    private void validarPosicaoDestino(Posicao origem, Posicao destino) {
        if (!tabuleiro.peca(origem).movimentoPossivel(destino)) {
            throw new XadrezException("A peça nao pode se mover para o destino");
        }
    }

    private Peca makeMove(Posicao origem, Posicao destino) {
        PecaXadrez p = (PecaXadrez) tabuleiro.removePeca(origem);
        p.incrementarMovimento();
        Peca pecaCaputurada = tabuleiro.removePeca(destino);
        tabuleiro.colocarPeca(p, destino);

        if (pecaCaputurada != null) {
            pecasNoTabuleiro.remove(pecaCaputurada);
            this.pecasCapturadas.add(pecaCaputurada);
        }

        return pecaCaputurada;
    }

    private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
        PecaXadrez p = (PecaXadrez)tabuleiro.removePeca(destino);
        p.decrementarMovimento();
        tabuleiro.colocarPeca(p, origem);

        if (pecaCapturada != null) {
            tabuleiro.colocarPeca(pecaCapturada, destino);
            this.pecasCapturadas.remove(pecaCapturada);
            this.pecasNoTabuleiro.add(pecaCapturada);
        }
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

    private Cor oponete(Cor cor) {
        return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
    }

    private PecaXadrez rei(Cor cor) {
        List<Peca> lista = pecasNoTabuleiro.stream()
                .filter(peca -> ((PecaXadrez) peca).getCor() == cor)
                .collect(Collectors.toList());
        for (Peca p : lista) {
            if (p instanceof Rei) {
                return (PecaXadrez) p;
            }
        }
        // Caso essa exception aconteca é porque o sistema esta com erro
        // pois tem que ter um rei no tabuleiro
        throw new IllegalStateException("Nao existe o rei da cor " + cor + " no tabuleiro");
    }

    private boolean verificarCheck(Cor cor) {
        Posicao reiPosicao = rei(cor).getXadrezPosicao().dePosisao();
        List<Peca> oponentesPecas = pecasNoTabuleiro.stream()
                .filter(peca -> ((PecaXadrez) peca).getCor() == oponete(cor))
                .collect(Collectors.toList());

        for (Peca p : oponentesPecas) {
            boolean[][] mat = p.movimentosPossiveis();
            if (mat[reiPosicao.getLinha()][reiPosicao.getColuna()]) {
                return true;
            }
        }

        return false;

    }

    private boolean verificarCheckMate(Cor cor) {
        if (!verificarCheck(cor)) {
            return false;
        }

        List<Peca> lista = pecasNoTabuleiro.stream()
                .filter(peca -> ((PecaXadrez) peca).getCor() == cor)
                .collect(Collectors.toList());

        for (Peca p : lista) {
            boolean[][] mat = p.movimentosPossiveis();
            for (int linha = 0; linha < tabuleiro.getLinhas(); linha++) {
                for (int coluna = 0; coluna < tabuleiro.getColunas(); coluna++) {
                    if (mat[linha][coluna]) {
                        Posicao origem = ((PecaXadrez) p).getXadrezPosicao().dePosisao();
                        Posicao destino = new Posicao(linha, coluna);
                        Peca pecaCapturada = makeMove(origem, destino);
                        boolean testarCheck = verificarCheck(cor);
                        desfazerMovimento(origem, destino, pecaCapturada);
                        if (!testarCheck) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    private void Iniciar() {
        NovoPosicao('h', 7, new Torre(tabuleiro, Cor.BRANCO));
        NovoPosicao('d', 1, new Torre(tabuleiro, Cor.BRANCO));
        NovoPosicao('e', 1, new Rei(tabuleiro, Cor.BRANCO));

        NovoPosicao('b', 8, new Torre(tabuleiro, Cor.PRETO));
        NovoPosicao('a', 8, new Rei(tabuleiro, Cor.PRETO));
    }

}
