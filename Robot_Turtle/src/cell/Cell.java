package cell;

import java.util.TreeMap;

public abstract class Cell {
    protected String name = "I'm a cell";
    public static TreeMap<Integer, Jewel> positionJewels = new TreeMap<>();
    public static TreeMap<Integer, Block> positionWalls = new TreeMap<>();
    protected boolean check = false;
    public static TreeMap<Integer, Player> positionPlayers = new TreeMap<>();  //<position, Player> pour savoir  s'il y a un joueur à une telle position


    public void setCheck(boolean temp) {
        check = temp;
    }

    public boolean isCheck() {
        return check;
    }

    public String getName() {
        return this.name;
    }

    public int convertPositionToInt(int line, int column) {  // convertit un tableau de position en nombre entier ([2][1] devient 21), utilise pour gérer les collisions
        int caseNum = 10 * line + column;
        return caseNum;
    }

    public boolean isAvailable(int pos) {
        if (positionWalls.containsKey(pos)) {
            return false;
        } else {
            if (positionJewels.containsKey(pos)) {
                return false;
            } else {
                if (positionPlayers.containsKey(pos)) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    public int[][][] posArrayBool(int gridLine, int gridColumn) {
        int tab[][][] = new int[gridLine][gridColumn][3];
        tab[0][0][2] = 0;
        for (int i = 0; i < gridLine; i++) {
            for (int j = 0; j < gridColumn; j++) {
                tab[i][j][1] = 1;
                try {
                    if (positionPlayers.containsKey(convertPositionToInt(i, j))) {
                        tab[i][j][0] = 2;
                    } else {
                        if (positionWalls.get(convertPositionToInt(i, j)).getName().equals("Stone wall")) {   //pour différencier les Stone wall des Ice wall
                            tab[i][j][0] = 1;
                        } else {
                            if (positionWalls.get(convertPositionToInt(i, j)).getName().equals("Ice wall") || positionWalls.get(convertPositionToInt(i, j)).getName().equals("EmptyZone") || positionJewels.containsKey(convertPositionToInt(i, j))) {
                                tab[i][j][0] = 0;
                            }
                        }
                    }
                } catch (NullPointerException e){
                    tab[i][j][0] = 0;
                }
            }
        }
        displayPosArrayBoolDim3Pour0(tab);
        return tab;
    }

    public static void displayPosArrayBoolDim3Pour0(int[][][] tab){
        for (int i = 0; i < tab.length; i++) {    //nb de ligne
            for (int j = 0; j < tab[0].length; j++) {   //nb de colonne
                System.out.print(tab[i][j][0]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void displayPosArrayBoolDim3Pour1(int[][][] tab){
        for (int i = 0; i < tab.length; i++) {    //nb de ligne
            for (int j = 0; j < tab[0].length; j++) {   //nb de colonne
                System.out.print(tab[i][j][1]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }


}
