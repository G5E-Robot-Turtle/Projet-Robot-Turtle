package com.company;

import cell.*;

public class Grid {
    private Cell[][] grid = new Cell[8][8];


    public Grid() {                               //plateau 8*8
        initGrid();
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public void initGrid() {  //à compléter
        Player player1 = new Player();             //test

        grid[0][0]=player1;


        System.out.println(grid[0][0]);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                grid[i][j] = player1;
                System.out.print(grid[i][j] +"  ");
            }
            System.out.println();
        }

    }

    public void displayGrid(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(grid[i][j]+"  ");
            }
            System.out.println();
        }
    }

    //    public Position getPlayerPosition(Player player){ }    //on garde cette méthode ?
    public void addCellObject(Position position) {    //return une Position ?, à compléter
    }

    public void makeEmpty(Position position) {   //à compléter

    }


}
