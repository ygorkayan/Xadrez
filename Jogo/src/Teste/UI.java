package Teste;

import Xadrez.Cor;
import Xadrez.PartidaXadrez;
import Xadrez.PecaXadrez;
import Xadrez.XadrezPosicao;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void limparTela() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public static XadrezPosicao lerPosicaoXadrez(Scanner sc) {
        try {
            String cordenadas = sc.nextLine();
            char coluna = cordenadas.charAt(0);
            int linha = Integer.parseInt(cordenadas.substring(1));
            return new XadrezPosicao(coluna, linha);
        } catch (RuntimeException e) {
            throw new InputMismatchException("Erro ao ler posiçao, entre com valores de a1 a h8");
        }

    }

    public static void mostrarPartida(PartidaXadrez partidaXadrez) {
        mostrarTabuleiro(partidaXadrez.getPeca());
        System.out.println();
        System.out.println("Turno: "+partidaXadrez.getTurno());
        System.out.println("Aguardando jogador: "+partidaXadrez.getJogadorCorreto());
    }

    public static void mostrarTabuleiro(PecaXadrez[][] pecas) {
        for (int linha = 0; linha < pecas.length; linha++) {
            System.out.print((8 - linha) + " ");
            for (int coluna = 0; coluna < pecas.length; coluna++) {
                mostrarPecas(pecas[linha][coluna], false);
            }
            System.out.println();
        }
        System.out.println("   A B C D E F G H ");
    }

    public static void mostrarTabuleiro(PecaXadrez[][] pecas, boolean[][] movimentosPossiveis) {
        for (int linha = 0; linha < pecas.length; linha++) {
            System.out.print((8 - linha) + " ");
            for (int coluna = 0; coluna < pecas.length; coluna++) {
                mostrarPecas(pecas[linha][coluna], movimentosPossiveis[linha][coluna]);
            }
            System.out.println();
        }
        System.out.println("   A B C D E F G H ");
    }

    private static void mostrarPecas(PecaXadrez pecas, boolean pintarFundo) {
        if(pintarFundo) {
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        if (pecas == null) {
            System.out.print(" -"+ ANSI_RESET);
        } else {
            if(pecas.getCor() == Cor.BRANCO) {
                System.out.print(" "+ANSI_WHITE + pecas + ANSI_RESET);
            } else {
                System.out.print(" "+ANSI_YELLOW + pecas + ANSI_RESET);
            }
        }
    }
}

































