package cell;

public class IceWall extends Block {
    private boolean isDestructible = true;
    private String description = "Je bloque le chemin, mais je suis destructible par le laser";
    private String name = "Mur de glace";

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
