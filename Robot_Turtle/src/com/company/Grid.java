package com.company;

import cell.*;

public class Grid {
    private int line = 8;
    private int column = 8;
    private Cell[][] grid = new Cell[line][column];


    public Grid() {                               //plateau 8*8
        initGrid();
    }


    public Cell[][] getGrid() {
        return grid;
    }

    public void initGrid() {  //  pour 2 joueur, à enlever pour plus tard éventuellement
        Player player1 = new Player();             //test
        Player player2 = new Player("Turtle", "red", Direction.SOUTH, 2);
        Jewel jewel = new Jewel();
        Empty empty = new Empty();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                grid[i][j] = empty;
            }
        }
        grid[0][1] = player1;
        grid[0][5] = player2;
        grid[7][3] = jewel;
        displayGrid(8, 8);
    }

    public void initGrid(int nbJoueur) {  //  pour 2 à 4  joueurs
        Player player1 = new Player();
        Player player2 = new Player("Turtle", "red", Direction.SOUTH, 2);
        Jewel jewel = new Jewel();
        Empty empty = new Empty();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                grid[i][j] = empty;                 //une soixantaine de empty n'est pas nécessaire ?
            }
        }
        switch (nbJoueur) {
            case 2:
                grid[0][1] = player1;
                grid[0][5] = player2;
                grid[7][3] = jewel;
                //placer des murs en colonne 7 ligne 0 à 7
                displayGrid(8, 8);
                break;
            case 3:
                Player player3 = new Player("Turtle", "purple", Direction.SOUTH, 3);
                //créer jewel 2 et 3
                grid[0][0] = player1;
                grid[0][3] = player2;
                //faire pour player3
                grid[7][0] = jewel;
                //faire pour les deux autres jewel
                //placer des murs en colonne 7 ligne 0 à 7
                break;
            case 4:
                //remplir
                break;
            default:
                System.out.println("Nombre de joueurs invalide !");
        }
    }


    //    public Position getPlayerPosition(Player player){ }    //on garde cette méthode ?
    public void addCellObject(Position position) {    //return une Position ?, à compléter
    }

    public void makeEmpty(Position position) {   //à compléter

    }

    public void displayGrid(int line, int column) {
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(this.grid[i][j].getName() + "  ");
            }
            System.out.println();   //retour à la ligne
        }
    }


}
