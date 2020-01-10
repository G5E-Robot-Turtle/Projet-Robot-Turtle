package jeu;

import cell.*;

import java.util.List;

public class Grid {
    private int line = 8;
    private int column = 8;
    private Cell[][] grid = new Cell[line][column];


    public Grid() {                               //plateau 8*8

    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public void initGrid(Player player1, Player player2) {  //  pour 2 joueurs
        Jewel jewel = new Jewel();
        Empty empty = new Empty();
        StoneWall stoneWall = new StoneWall();
        int pos;
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                if (j == 7) {
                    grid[i][j] = stoneWall;
                    pos = 10 * i + j;
                    player1.positionWalls.put(pos, stoneWall);
                } else {
                    grid[i][j] = empty;
                }
            }
        }
        placePlayerInGrid(player1);
        placePlayerInGrid(player2);
        placeJewelInGrid(jewel);
    }

    public void placePlayerInGrid(Player player) {
        this.grid[player.getPositionY()][player.getPositionX()] = player;
    }

    public void placeJewelInGrid(Jewel jewel) {
        this.grid[jewel.getPositionY()][jewel.getPositionX()] = jewel;
    }

    public void initGrid(Player player1, Player player2, Player player3) {  //  pour 3 joueurs
        Jewel jewel1 = new Jewel(new int[]{7, 0});
        Jewel jewel2 = new Jewel(new int[]{7, 3});
        Jewel jewel3 = new Jewel(new int[]{7, 6});
        Empty empty = new Empty();
        StoneWall stoneWall = new StoneWall();
        int pos;
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                if (j == 7) {
                    grid[i][j] = stoneWall;
                    pos = 10 * i + j;
                    player1.positionWalls.put(pos, stoneWall);
                } else {
                    grid[i][j] = empty;
                }
            }
        }
        placePlayerInGrid(player1);
        placePlayerInGrid(player2);
        placePlayerInGrid(player3);
        placeJewelInGrid(jewel1);
        placeJewelInGrid(jewel2);
        placeJewelInGrid(jewel3);
    }

    public void initGrid(Player player1, Player player2, Player player3, Player player4) {  //  pour 4 joueurs
        Jewel jewel1 = new Jewel(new int[]{7, 1});
        Jewel jewel2 = new Jewel(new int[]{7, 6});
        Empty empty = new Empty();
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                grid[i][j] = empty;
            }
        }
        placePlayerInGrid(player1);
        placePlayerInGrid(player2);
        placePlayerInGrid(player3);
        placePlayerInGrid(player4);
        placeJewelInGrid(jewel1);
        placeJewelInGrid(jewel2);
    }


/*    public void initGrid(int nbJoueur) {  //  pour 2 à 4  joueurs   //non utilisé finalement, peut être que plus tard on le modifiera
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
                grid[player1.getPositionY()][player1.getPositionX()] = player1;     //attention, getPositionY donne bien la ligne et getPositionX la colonne !
                grid[player2.getPositionY()][player2.getPositionX()] = player2;
                grid[7][3] = jewel;
                //placer des murs en colonne 7 ligne 0 à 7
                displayGrid(line, column);
                break;
            case 3:
                Player player3 = new Player("Turtle", "purple", 3, new int[]{0, 6, 0, 6});
                //créer jewel 2 et 3
                player1.setPosition(new int[]{0, 0, 0, 0});
                player2.setPosition(new int[]{0, 3, 0, 3});
                grid[player1.getPositionY()][player1.getPositionX()] = player1;
                grid[player2.getPositionY()][player2.getPositionX()] = player2;
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
    }*/


    //    public Position getPlayerPosition(Player player){ }    //on garde cette méthode ?
    public void addCellObject(Position position) {    //return une Position ?, à compléter
    }

    public void makeEmpty(int line, int column, Player player) {
        Empty empty = new Empty();
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j].getName().equals(player.getName())) {
                    grid[i][j] = empty;   //peut faire un problème d'affichage lors du passage en graphique ? c'est un nouvel objet Empty ici
                }
            }
        }
    }

    public void displayGrid(int line, int column) {
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(this.grid[i][j].getName() + "  ");
            }
            System.out.println();   //retour à la ligne
        }
    }

    public void updateGrid(int line, int column, Player player1) {   //actualise la position que d'un seul joueur, une limite quand il y a collision avec un autre joueur
        makeEmpty(line, column, player1);
        if (!grid[player1.getPositionY()][player1.getPositionX()].getName().equals("Jewel")) {   //ne pas effacer Jewel
            grid[player1.getPositionY()][player1.getPositionX()] = player1;
        }
    }

    public void updateGridPlayers(int line, int column, List<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            makeEmpty(line, column, players.get(i));
            if (!grid[players.get(i).getPositionY()][players.get(i).getPositionX()].getName().equals("Jewel")) {   //ne pas effacer Jewel
                grid[players.get(i).getPositionY()][players.get(i).getPositionX()] = players.get(i);
            }
        }
    }

    public void setCellGrid(int X, int Y, Cell cell) {
        this.grid[X][Y] = cell;
    }
}
