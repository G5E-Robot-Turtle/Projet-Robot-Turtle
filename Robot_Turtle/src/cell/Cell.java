package cell;

import java.util.TreeMap;

public abstract class Cell {
    protected Player player;
    protected Block block;
    protected Jewel jewel;
    protected Empty empty;
    protected String name = "I'm a cell";
    public static TreeMap<Integer, Jewel> positionJewels = new TreeMap<>();

    public void isEmpty() { // à enlever ?

    }

    public String getName() {
        return this.name;
    }

    public int convertPositionToInt(int line, int column) {  // convertit un tableau de position en nombre entier ([2][1] devient 21), utilise pour gérer les collisions
        int caseNum = 10 * line + column;
        return caseNum;
    }
}