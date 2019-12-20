package cell;

public class Jewel extends Cell {
    private String description = "Je suis un Joyau, la tortue de bonne couleur qui me trouve se qualifie pour la victoire !";
    private String name = "Joyau";
    private String color;
    private boolean turtleIsArrived;

    public Jewel() {
    }

    public void plawerWin() {

    }

    public boolean isSurrouded() {
        return true;
    }
}
