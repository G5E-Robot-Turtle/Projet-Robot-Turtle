package cell;

public class StoneWall extends Block {
    private boolean isDestructible = false;
    private String description = "I block the way, the laser doesn't destroy me";
    private String name = "Stone wall";

    public String getDescription() {
        return this.description;
    }

    public StoneWall() {
    }

    public String getName() {
        return this.name;
    }

    public boolean isDestructible() {
        return this.isDestructible;
    }
}
