package Teste;

import Xadrez.PecaXadrez;

public class UI {
    public static void mostrarTabuleiro(PecaXadrez[][] pecas) {
        for (int linha = 0; linha < pecas.length; linha++) {
            System.out.print((8 - linha) + " ");
            for (int coluna = 0; coluna < pecas.length; coluna++) {
                mostrarPecas(pecas[linha][coluna]);
            }
            System.out.println();
        }
        System.out.println("   A B C D E F G H ");
    }

    private static void mostrarPecas(PecaXadrez pecas) {
        if (pecas == null) {
            System.out.print(" -");
        } else {
            System.out.print(" " + pecas);
        }
    }

}

































