package Teste;

import Tabuleiro.TabuleiroException;
import Xadrez.PartidaXadrez;
import Xadrez.PecaXadrez;
import Xadrez.XadrezException;
import Xadrez.XadrezPosicao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PartidaXadrez partidaXadrez = new PartidaXadrez();
        List<PecaXadrez> capturada = new ArrayList<>();

        while (!partidaXadrez.getCheckMate()) {
            try {
                UI.limparTela();
                UI.mostrarPartida(partidaXadrez, capturada);
                System.out.println();
                System.out.print("Origem: ");
                XadrezPosicao origem = UI.lerPosicaoXadrez(sc);

                boolean[][] movimentosPossiveis = partidaXadrez.movimentosPossiveis(origem);
                UI.limparTela();
                UI.mostrarTabuleiro(partidaXadrez.getPeca(), movimentosPossiveis);

                System.out.println();
                System.out.print("Destino: ");
                XadrezPosicao destino = UI.lerPosicaoXadrez(sc);

                PecaXadrez pecaCapturada = partidaXadrez.moverPeca(origem, destino);

                if (pecaCapturada != null) {
                    capturada.add(pecaCapturada);
                }

            } catch (XadrezException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            } catch (TabuleiroException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }

        }

        UI.limparTela();
        UI.mostrarPartida(partidaXadrez, capturada);

    }
}
