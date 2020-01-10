package jeu;

import java.util.Scanner;

import ui.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("\tWelcome to Robot Turtle !");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input 1 for a graphic game\nInput 0 for a console game");
        int choice;
        int nbPlayers;
        do {
            choice = scanner.nextInt();
            System.out.println("Choose the number of players between 2 and 4 :");
            do {
                nbPlayers = scanner.nextInt();
                if(nbPlayers > 4 || nbPlayers < 2){
                    System.out.println("Error, number of players incorrect, please try again");
                }
            } while (nbPlayers > 4 || nbPlayers < 2);

            if (choice == 0) {
                Game game = new Game(nbPlayers);
                game.startGame();
            } else if (choice == 1) {
                Game game = new Game(true, nbPlayers);
            } else if (choice < 0 || choice > 1) {
                System.out.println("Error, choice incorrect, please try again");
            }
        } while (choice < 0 || choice > 1);


    }

}
