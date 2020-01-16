package Teste;

import Tabuleiro.TabuleiroException;
import Xadrez.PartidaXadrez;
import Xadrez.PecaXadrez;
import Xadrez.XadrezException;
import Xadrez.XadrezPosicao;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PartidaXadrez px = new PartidaXadrez();

        while (true) {
            try {
                UI.limparTela();
                UI.mostrarTabuleiro(px.getPeca());
                System.out.println();
                System.out.print("Origem: ");
                XadrezPosicao origem = UI.lerPosicaoXadrez(sc);

                System.out.print("Destino: ");
                XadrezPosicao destino = UI.lerPosicaoXadrez(sc);

                PecaXadrez pecaCapturada = px.moverPeca(origem, destino);
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
    }
}
