package Tabuleiro;

public class Tabuleiro {

    private int linhas;
    private int colunas;
    private Peca[][] pecas;

    public Tabuleiro(int linhas, int colunas) {
        if(linhas < 1 || colunas <1) {
            throw new TabuleiroException("Erro ao criar tabuleiro, tem que ter pelo menos 1 linha e 1 coluna");
        }
        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new Peca[linhas][colunas];
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public Peca peca(int linha, int coluna) {
        if(!posicaoExiste(linha,coluna)) {
            throw new TabuleiroException("Essa posiçao nao existe");
        }
        return this.pecas[linha][coluna];
    }

    public Peca peca(Posicao posicao) {
        if(!posicaoExiste(posicao)) {
            throw new TabuleiroException("Essa posiçao nao existe");
        }
        return this.pecas[posicao.getLinha()][posicao.getColuna()];
    }

    public void colocarPeca(Peca peca, Posicao posicao) {
        if(temPeca(posicao)) {
            throw new TabuleiroException("Ja tem peça nessa posiçao " + posicao);
        }
        this.pecas[posicao.getLinha()][posicao.getColuna()] = peca;
        peca.posicao = posicao;
    }

    private boolean posicaoExiste(int linha, int colunas) {
        return linha >= 0 && linha < this.linhas && colunas >= 0 && colunas < this.colunas;
    }

    public boolean posicaoExiste(Posicao posicao) {
        return posicaoExiste(posicao.getLinha(), posicao.getColuna());
    }

    public boolean temPeca(Posicao posicao) {
        if(!posicaoExiste(posicao)) {
            throw new TabuleiroException("Essa posiçao nao existe");
        }
        return peca(posicao) != null;
    }

}
