package com.company;

import card.*;
import cell.*;

public class Main {

    public static void main(String[] args) {
        //2 joueurs
//        Player player1 = new Player("Player 1", "Green", 1, new int[]{0, 1, 0, 1});
//        Player player2 = new Player("Player 2", "Red", 1, new int[]{0, 5, 0, 5});

        //3 joueurs
//        Player player1 = new Player("Player 1", "Green", 1, new int[]{0, 0, 0, 0});
//        Player player2 = new Player("Player 2", "Red", 1, new int[]{0, 3, 0, 3});
//        Player player3 = new Player("Player 3", "Blue", 1, new int[]{0, 6, 0, 6});

        //4 joueurs

        Player player1 = new Player("Player 1", "Green", 1, new int[]{0, 0, 0, 0});
        Player player2 = new Player("Player 2", "Red", 1, new int[]{0, 2, 0, 2});
        Player player3 = new Player("Player 3", "Blue", 1, new int[]{0, 5, 0, 5});
        Player player4 = new Player("Player 4", "Purple", 1, new int[]{0, 7, 0, 7});
        Grid grid = new Grid();
        grid.initGrid(player1, player2, player3, player4);
//        grid[player1.getPositionY()][player1.getPositionX()]
        player1.pickCardFromDeck();
//        player1.addToProgram();
//        player1.showProgram();
        player1.addToProgram();
        player1.play();
        player1.showHandCard();
        player1.showDiscard();
        player1.showProgram();
//        grid.displayGrid(8,8);
        grid.updateGrid(8, 8, player1);
        System.out.println();
        grid.updateGrid(8,8,player2);
        System.out.println(Player.positionPlayers);
        System.out.println(Player.positionJewels);

    }

}
