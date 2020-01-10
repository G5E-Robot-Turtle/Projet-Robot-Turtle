package jeu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the number of players between 2 and 4 :");
        int nbPlayers = scanner.nextInt();
        Game game = new Game(nbPlayers);
        game.startGame();
    }

}
