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

    public void playerWin() {

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

    private int playerEncounter = 0;
    int[][][] tableau = posArrayBool(8,8);

    public void setIsSurrounded() {
        for (int i = 0; i < tableau.length; i++) {
            for (int j = 0; j < tableau[i].length; j++) {
                tableau[i][j][1] = 1;
            }
        }
        playerEncounter = 0;
    }

    public void isSurrouded(int i, int j) {
        int quoi = tableau[i][j][0];
        int alreadyCheck = tableau[i][j][1];
        if (alreadyCheck == 1 || quoi != 2) {
            if (quoi == 1) {
                playerEncounter += 1;
            }
            isSurrouded(i+1,j);
            isSurrouded(i-1,j);
            isSurrouded(i,j+1);
            isSurrouded(i,j-1);
        }
    }

    public static boolean isSuRrouded(int i, int j, int[][][] tableau, int playerEncounter) {
        int quoi = tableau[i][j][0];
        int alreadyCheck = tableau[i][j][1];
        if (alreadyCheck == 1 && quoi != 1) {
            if (quoi == 2) {
                playerEncounter += 1;
                System.out.println("réussite");
            }
            tableau[i][j][1] = 0;
            if (i != 7) {
                isSuRrouded(i + 1, j, tableau, playerEncounter);
            }
            if (i != 0) {
                isSuRrouded(i - 1, j, tableau, playerEncounter);
            }
            if (j != 7) {
                isSuRrouded(i, j + 1, tableau, playerEncounter);
            }
            if (j != 0) {
                isSuRrouded(i, j - 1, tableau, playerEncounter);
            }
        }
        if (playerEncounter == positionPlayers.size()) {
            return true;
        }
        return false;
    }

    public boolean surround() {
        if (playerEncounter == positionPlayers.size()) {
            return true;
        }
        else {
            return false;
        }
    }

    //fin de vérif encerclement

    public String getName() {
        return this.name;
    }
}
