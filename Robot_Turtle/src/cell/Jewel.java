package cell;

public class Jewel extends Cell {
    private String description = "I am a Jewel, the turtles that find me will be qualified for the victory !";
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
