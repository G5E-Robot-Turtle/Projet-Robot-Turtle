package cell;

public abstract class Block extends Cell {
    private boolean isDestructible;

    public String getDescritpion() {
        return "I'm a block";
    }

    public String getName() {
        return "Block";
    }
}
