package cell;

public class Jewel extends Cell {
    private String description = "I am a Jewel, the turtles that find me will be qualified for the victory !";
    private String name = "Jewel";
    private int[] position = {7, 3};

    public Jewel() {
        int caseNum = convertPositionToInt(position[0], position[1]);
        positionJewels.put(caseNum, this);  //enregistrer le joyau et sa position dans la variable static
    }

    public Jewel(int[] position) {
        this.position = position;
        int caseNum = convertPositionToInt(position[0], position[1]);
        positionJewels.put(caseNum, this);  //enregistrer le joyau et sa position dans la variable static
    }

    public int getPositionY() {
        return position[0];
    }

    public int getPositionX() {
        return position[1];
    }

    public String getName() {
        return this.name;
    }
}
