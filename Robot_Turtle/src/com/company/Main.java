package com.company;

import card.*;
import cell.*;

public class Main {

    public static void main(String[] args) {
        //test pour voir
        Player player1 = new Player("Player 1", "Green", 1, new int[]{0, 1, 0, 1});
        Player player2 = new Player("Player 2", "Red", 1, new int[]{0, 5, 0, 5});
        Grid grid = new Grid();

//        grid[player1.setPositionX()]
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
    }

}
