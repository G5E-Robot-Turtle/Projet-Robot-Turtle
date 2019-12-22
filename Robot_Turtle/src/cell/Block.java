package cell;

public abstract class Block extends Cell {
    private boolean isDestructible;

    public String getDescription() {
        return "I'm a block";
    }

    public String getName() {
        return "Block";
    }
}
