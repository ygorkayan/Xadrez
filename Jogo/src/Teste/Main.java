package Teste;

import Xadrez.PartidaXadrez;

public class Main {
    public static void main(String[] args) {
        PartidaXadrez px = new PartidaXadrez();
        UI.mostrarTabuleiro(px.getPeca());
    }
}
