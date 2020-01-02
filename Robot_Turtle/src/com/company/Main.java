package com.company;

import card.*;
import cell.*;

public class Main {

    public static void main(String[] args) {

        //3 joueurs
//        Player player1 = new Player("Player 1", "Green", 1, new int[]{0, 0, 0, 0});
//        Player player2 = new Player("Player 2", "Red", 2, new int[]{0, 3, 0, 3});
//        Player player3 = new Player("Player 3", "Blue", 3, new int[]{0, 6, 0, 6});

/*
        //4 joueurs
        Player player1 = new Player("Player 1", "Green", 1, new int[]{0, 0, 0, 0});
        Player player2 = new Player("Player 2", "Red", 2, new int[]{0, 2, 0, 2});
        Player player3 = new Player("Player 3", "Blue", 3, new int[]{0, 5, 0, 5});
        Player player4 = new Player("Player 4", "Purple", 4, new int[]{0, 7, 0, 7});
        Grid grid = new Grid();
        grid.initGrid(player1, player2, player3, player4);
        player1.pickCardFromDeck();
//        player1.addToProgram();
//        player1.showProgram();
        player1.addToProgram();
        player1.play();
        player1.showHandCard();
        player1.showDiscard();
//        player1.showProgram();

        grid.updateGrid(8, 8, player1);
        System.out.println();
//        grid.updateGrid(8, 8, player2);
        System.out.println(Player.positionPlayers);
        System.out.println(Player.positionJewels);

        player1.pickCardFromDeck();  //test pour atteindre le joyau
        player1.showHandCard();
        player1.addToProgram();
        player1.manageHandCard();
        player1.executeProgram();
        grid.updateGrid(8, 8, player1);

        player1.pickCardFromDeck();
        player1.showHandCard();
        player1.addToProgram();
        player1.manageHandCard();
        player1.executeProgram();
        grid.updateGrid(8, 8, player1);

        player1.pickCardFromDeck();
        player1.showHandCard();
        player1.addToProgram();
        player1.manageHandCard();
        player1.executeProgram();
        grid.updateGrid(8, 8, player1);

        player1.pickCardFromDeck();
        player1.showHandCard();
        player1.addToProgram();
        player1.manageHandCard();
        player1.executeProgram();
        grid.updateGrid(8, 8, player1);
        System.out.println(Player.positionPlayers);
*/
        Game game = new Game(3);
        game.startGame();
    }

}
