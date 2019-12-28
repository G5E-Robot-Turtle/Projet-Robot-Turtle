package com.company;

import card.*;
import cell.*;

public class Main {

    public static void main(String[] args) {
        //test pour voir
        Player player1 = new Player("Player 1", "Green", 1, new int[]{0, 1, 0, 1});
        Player player2 = new Player("Player 2", "Red", 1, new int[]{0, 5, 0, 5});
        Grid grid = new Grid();

        player1.pickCardFromDeck();
//        player1.addToProgram();
//        player1.showProgram();
        player1.addToProgram();
        player1.play();
        System.out.println("maintenant on a");
        player1.showHandCard();
        player1.showDiscard();
        player1.showProgram();
        player1.executeProgram();
//        grid.displayGrid(8,8);
        grid.updateGrid(8, 8, player1);
    }

}
