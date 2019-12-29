package cell;

public class IceWall extends Block {
    private boolean isDestructible = true;
    private String description = "I block the way, but I'm destructible by the laser";
    private String name = "Ice wall";

    public String getDescription() {
        return description;
    }

    public IceWall() {
    }

    public String getName() {
        return this.name;
    }

    public boolean isDestructible() {
        return isDestructible;
    }
}
