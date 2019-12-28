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
        Player player2 = new Player("Turtle", "red", 2, new int[]{0, 5, 0, 5});
        Jewel jewel = new Jewel();
        Empty empty = new Empty();

        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                grid[i][j] = empty;
            }
        }
        grid[player1.getPositionX()][player1.getPositionY()] = player1;
        grid[player2.getPositionX()][player2.getPositionY()] = player2;
        grid[7][3] = jewel;    //Jewel ne bougera pas, donc pas besoin de mettre des attributs positions dans Jewel ?
//        displayGrid(line, column);
    }

    public void initGrid(int nbJoueur) {  //  pour 2 à 4  joueurs
        Player player1 = new Player();
        Player player2 = new Player("Turtle", "red", 2, new int[]{0, 5, 0, 5});   //les deux premières valeurs pour la position actuelle
        //les deux dernières valeurs pour stocker la position initiale
        Jewel jewel = new Jewel();
        Empty empty = new Empty();
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                grid[i][j] = empty;                 //une soixantaine de empty n'est pas nécessaire ?
            }
        }
        switch (nbJoueur) {
            case 2:
                grid[player1.getPositionX()][player1.getPositionY()] = player1;
                grid[player2.getPositionX()][player2.getPositionY()] = player2;
                grid[7][3] = jewel;
                //placer des murs en colonne 7 ligne 0 à 7
                displayGrid(line, column);
                break;
            case 3:
                Player player3 = new Player("Turtle", "purple", 3, new int[]{7, 0, 7, 0});
                //créer jewel 2 et 3
                player1.setPosition(new int[]{0, 0, 0, 0});
                player2.setPosition(new int[]{0, 3, 0, 3});
                grid[player1.getPositionX()][player1.getPositionY()] = player1;
                grid[player2.getPositionX()][player2.getPositionY()] = player2;
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
//        displayGrid(line, column);
    }


    //    public Position getPlayerPosition(Player player){ }    //on garde cette méthode ?
    public void addCellObject(Position position) {    //return une Position ?, à compléter
    }

    public void makeEmpty(int x, int y) {
        grid[x][y] = new Empty();   //peut faire un problème d'affichage lors du passage en graphique ? c'est un nouvel objet Empty ici
    }

    public void displayGrid(int line, int column) {
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(this.grid[i][j].getName() + "  ");
            }
            System.out.println();   //retour à la ligne
        }
    }

    public void updateGrid(int line, int column, Player player1) {
        makeEmpty(player1.getPreviousPositionX(), player1.getPreviousPositionY());
        grid[player1.getPositionX()][player1.getPositionY()] = player1;

        displayGrid(line, column);
    }


}
