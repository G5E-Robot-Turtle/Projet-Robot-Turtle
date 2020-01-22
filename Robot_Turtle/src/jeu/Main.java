package jeu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("\tWelcome to Robot Turtle !");
        Scanner scanner = new Scanner(System.in);
        int nbPlayers;
        System.out.println("Choose the number of players between 2 and 4 :");
        do {
            try {
                nbPlayers = scanner.nextInt();
                if (nbPlayers > 4 || nbPlayers < 2) {
                    System.out.println("Error, number of players incorrect, please try again");
                }
            } catch (InputMismatchException e) {
                System.out.println("Saisie invalide");
                nbPlayers = 0;
            }
        } while (nbPlayers > 4 || nbPlayers < 2);

        Game game = new Game(nbPlayers);
        game.startGame();
    }

}
