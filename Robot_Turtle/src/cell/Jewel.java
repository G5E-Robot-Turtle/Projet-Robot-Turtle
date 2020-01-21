package cell;

public class Jewel extends Cell {
    private String description = "I am a Jewel, the turtles that find me will be qualified for the victory !";
    private String name = "Jewel";
    private String color;
    private boolean turtleIsArrived;
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

    public int[] getPosition() {
        return position;
    }

    public int getPositionY() {
        return position[0];
    }

    public int getPositionX() {
        return position[1];
    }

    //Partie pour vérifier l'encerclement :

    public static boolean isSuRrouded(int i, int j, int[][][] tableau, int playerEncounter) {
            if (tableau[i][j][0] == 2) {
                playerEncounter += 1;
                System.out.println("réussite");
                System.out.println(playerEncounter);
            }
            if (playerEncounter == positionPlayers.size()) {
                System.out.println("WUT");
                return true;
            }
            tableau[i][j][1] = 0;
            if (i < 7 && tableau[i+1][j][1] == 1 && tableau[i+1][j][0] != 1) {
                return isSuRrouded(i + 1, j, tableau, playerEncounter);
            }
            if (j < 7 && tableau[i][j+1][1] == 1 && tableau[i][j+1][0] != 1) {
                return isSuRrouded(i, j + 1, tableau, playerEncounter);
            }
            if (i > 0 && tableau[i-1][j][1] == 1 && tableau[i-1][j][0] != 1) {
                return isSuRrouded(i - 1, j, tableau, playerEncounter);
            }

            if (j > 0 && tableau[i][j-1][1] == 1 && tableau[i][j-1][0] != 1) {
                return isSuRrouded(i, j - 1, tableau, playerEncounter);
            }
        return false;
    }
    //fin de vérif encerclement

    public String getName() {
        return this.name;
    }
}
