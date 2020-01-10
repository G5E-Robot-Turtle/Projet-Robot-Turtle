package cell;

import java.util.TreeMap;

public abstract class Cell {
    protected Player player;
    protected Block block;
    protected Jewel jewel;
    protected Empty empty;
    protected String name = "I'm a cell";
    public static TreeMap<Integer, Jewel> positionJewels = new TreeMap<>();
    public static TreeMap<Integer, Block> positionWalls = new TreeMap<>();
    protected boolean check = false;
    public static TreeMap<Integer, Player> positionPlayers = new TreeMap<>();  //<position, Player> pour savoir  s'il y a un joueur à une telle position

    public void isEmpty() { // à enlever ?

    }

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
}
