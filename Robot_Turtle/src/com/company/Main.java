package com.company;

import card.*;
import cell.*;

public class Main {

    public static void main(String[] args) {
        //test pour voir
        Player player1 = new Player("PremierJoueur", "Vert", Direction.NORTH, 1);
//        Grid plateau = new Grid();
        player1.pickCardFromDeck();
//        player1.addToProgram();
//        player1.showProgram();
        player1.play();
        System.out.println("maintenant on a");
        player1.showHandCard();
        player1.showDiscard();
        player1.showProgram();
    }

}
