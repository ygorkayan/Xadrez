package Teste;

import Xadrez.PartidaXadrez;
import Xadrez.PecaXadrez;
import Xadrez.XadrezPosicao;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PartidaXadrez px = new PartidaXadrez();

        while (true) {
            UI.mostrarTabuleiro(px.getPeca());

            System.out.println();
            System.out.print("Origem: ");
            XadrezPosicao origem = UI.lerPosicaoXadrez(sc);

            System.out.print("Destino: ");
            XadrezPosicao destino = UI.lerPosicaoXadrez(sc);

            PecaXadrez pecaCapturada = px.moverPeca(origem, destino);
        }
    }
}
