package cell;

public class StoneWall {
    private boolean isDestructible = false;
    private String description = "Je bloque le chemin, rien ne peut me détruire";
    private String name = "Mur de pierre";

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
