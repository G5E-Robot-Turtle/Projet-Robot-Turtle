package jeu;

import  java.util.Scanner;

import ui.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("\tWelcome to Robot Turtle !");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input 1 for a graphic game\nInput 0 for a console game");
        int choice;
        do {
            choice = scanner.nextInt();
            System.out.println("Choose the number of players between 2 and 4 :");
            int nbPlayers = scanner.nextInt();
            if (choice == 0) {
                Game game = new Game(nbPlayers);
                game.startGame();
            } else if (choice == 1) {
                Game game = new Game(true, nbPlayers);
            }
        } while (choice < 0 && choice > 1);



    }

}
