package cell;

public class Jewel extends Cell {
    private String description = "Je suis un Joyau, la tortue de bonne couleur qui me trouve se qualifie pour la victoire !";
    private String name = "Jewel";
    private String color;
    private boolean turtleIsArrived;

    public Jewel() {
    }

    public void playerWin() {

    }

    public boolean isSurrouded() {
        return true;
    }

    public String getName() {
        return this.name;
    }
}
