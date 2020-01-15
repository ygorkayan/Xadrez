package Tabuleiro;

public class Taboleiro {

    private int linhas;
    private int colunas;
    private Peca[][] pecas;

    public Taboleiro(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new Peca[linhas][colunas];
    }

    public int getLinhas() {
        return linhas;
    }

    public void setLinhas(int linhas) {
        this.linhas = linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public void setColunas(int colunas) {
        this.colunas = colunas;
    }

    public Peca peca(int linha, int coluna) {
        return this.pecas[linha][coluna];
    }

    public Peca peca(Posicao posicao) {
        return this.pecas[posicao.getLinha()][posicao.getColuna()];
    }

}
