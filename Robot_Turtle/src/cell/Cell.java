package cell;

public abstract class Cell {
    protected Player player;
    protected Block block;
    protected Jewel jewel;
    protected Empty empty;
    protected String name="I'm a cell";
    public void isEmpty() {

    }
    public String getName(){
        return this.name;
    }
}