package cell;
//package Card;

import card.*;

import java.util.*;

public class Player extends Cell {
    private String color;
    private Direction direction = Direction.SOUTH;
    private Direction currentDirection = direction;   //direction SUD par défaut
    private int score;
    private int passageOrder;
    private ArrayDeque program = new ArrayDeque();
    private List<Card> handCard = new ArrayList<>();
    private List<Block> blocks;
    private ArrayDeque discard = new ArrayDeque();   //défausse
    private Deck deck = new Deck();
    private String name = "Turtle";
    private Scanner scanner = new Scanner(System.in);
    private int[] position = {0, 1, 0, 1};   //position[0] = ligne, position[1] = colonne, ici par défaut le joueur est à la ligne 0 et à la colonne 1
    //position[2] = ligne de départ, position[3] = colonne de départ, utilise lorsque la tortue se prend un laser par exemple
    private int[] previousPosition = {0, 1};  //enregistre la position précédente de la tortue, utile pour mettre à jour uniquement les cellules qui ont été modifiées

    public Player() {

    } //constructeur par défaut

    public Player(String name, String color, int passageOrder, int initialPosition[]) {
        this.name = name;
        this.color = color;
        this.passageOrder = passageOrder;
        this.position = initialPosition;
    }


    public int[] getPosition() {
        return this.position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public int getPositionY() {
        return this.position[0];
    }         //attention aux confusions !

    public void setPositionY(int line) {
        this.position[0] = line;
    }

    public int getPositionX() {
        return this.position[1];
    }         //attention aux confusions !

    public void setPositionX(int column) {
        this.position[1] = column;
    }

    public int[] getPreviousPosition() {
        return this.previousPosition;
    }

    public void setPreviousPosition(int[] previousPosition) {
        this.previousPosition = previousPosition;
    }

    public int getPreviousPositionY() {
        return this.previousPosition[0];
    }

    public void setPreviousPositionY(int line) {
        this.previousPosition[0] = line;
    }

    public int getPreviousPositionX() {
        return this.previousPosition[1];
    }

    public void setPreviousPositionX(int column) {
        this.previousPosition[1] = column;
    }


    public String getColor() {
        return color;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction newDirection) {
        this.direction = newDirection; //à vérifier
    }

    public int getPassageOrder() {
        return this.passageOrder;
    }

    public ArrayDeque getProgram() {
        return this.program;
    }

    public List<Card> getHandCard() {
        return this.handCard;
    }

    public List<Block> getBlocks() {
        return this.blocks;
    }

    public String getName() {
        return this.name;
    }

    public void pickCardFromDeck() {
        int maximumHandCard = 5;
        while (handCard.size() < maximumHandCard) {
            handCard.add(deck.pick());
        }
//        showHandCard();
    }

    public void showHandCard() {
        System.out.println("--  Hand Card : " + handCard.size() + " --");
        for (int i = 0; i < handCard.size(); i++) {
            System.out.print(i + ". " + handCard.get(i) + "\t");
        }
        System.out.println();
    }

    public void showDiscard() {
        System.out.println("--  Discard Card : " + discard.size() + " --");
        Iterator<Card> iterator = discard.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "\t");
        }
        System.out.println();
    }

    public void showProgram() {
        System.out.println("--  Program Card : " + program.size() + " --");
        Iterator<Card> iterator = program.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "\t");
        }
        System.out.println();
    }

    public void addToProgram() {
        if (handCard.isEmpty()) {
            System.out.println("You don't have any cards to add to your program");
        } else {
            System.out.println("Which card do you want to add to your program ? (-1 to Stop)");
            while (!handCard.isEmpty()) {
                showHandCard();
                int choice = scanner.nextInt();
                if (choice > -1 && choice < handCard.size()) {
                    program.add(handCard.remove(choice));
                } else if (choice == -1) {
                    break;
                } else {
                    System.out.println("Incorrect index, please try again");
                }
            }
        }
    }

    public void addToDiscard() {
        while (handCard.size() > 0) {
            for (int i = 0; i < handCard.size(); i++) {
                discard.addLast(handCard.remove(0));   //"déplacer" le premier élément de handCard à chaque fois
            }
        }
        showHandCard();
    }

    public void play() {
        System.out.println("What do you want to do ?\n1. Complete the program\n2. Build a wall\n3. Execute the program.");
        int choiceMin = 1;
        int choiceMax = 3;
        int choice;
        do {
            choice = scanner.nextInt();
            if (!(choiceMin - 1 < choice && choice < choiceMax + 1)) {
                System.out.println("Error, please try a correct choice");
            }
        } while (!(choiceMin - 1 < choice && choice < choiceMax + 1));
        switch (choice) {
            case 1:
                addToProgram();
                break;
            case 2:
                //build a wall
                break;
            case 3:
                executeProgram();
                break;
        }
        manageHandCard();

    }

    public void manageHandCard() {
        int choice;
        System.out.println("Do you want to discards your cards before picking new ones ? (1 : Yes ; 0 : No)");
        do {                                //défausser sa main et piocher 5 cartes ou piocher jusqu'à avoir 5 cartes
            choice = scanner.nextInt();
            if (choice == 1) {
                addToDiscard();
                if (handCard.isEmpty()) {  //on peut enlever le if
                    pickCardFromDeck();
                }
            } else if (choice == 0) {
                pickCardFromDeck();
            } else {
                System.out.println("Error, please input a correct choice");
            }
        } while (!(choice == 0 || choice == 1));
    }

    public void executeProgram() {
        while (!program.isEmpty()) {
            String actualCard = program.removeFirst().getClass().getName();
//            System.out.println(program.removeFirst().getClass().getName());   //on obtient le nom de la Classe (ex: card.BlueCard)
            if (actualCard.equals("card.BlueCard")) {
                System.out.println("Youhou, on avance !");
                goAhead();
            } else if (actualCard.equals("card.PurpleCard")) {
                this.currentDirection = direction.changeDirClock();
                System.out.println("New direction : " + this.currentDirection);
            } else if (actualCard.equals("card.YellowCard")) {
                this.currentDirection = direction.changeDirAntiClock();
                System.out.println("New direction : " + this.currentDirection);
            } else if (actualCard.equals("card.LaserCard")) {
                System.out.println("BOOM !");
                //à remplir
            }
        }

    }

    private void goAhead() {
        int gridLine = 8;
        int gridColumn = 8;
        switch (this.currentDirection) {
            case SOUTH:
                if (getPositionY() + 1 < gridLine) {
                    //il faut traiter le cas est-ce qu'il y a un mur ou une tortue, en créant une nouvelle fonction
                    setPositionY(getPositionY() + 1);
                } else {        //la tortue "sort du plateau"
                    goToInitialPosition();
                }

                break;
            case EAST:
                if (getPositionX() + 1 < gridColumn) {
                    //il faut traiter le cas est-ce qu'il y a un mur ou une tortue, en créant une nouvelle fonction
                    setPositionX(getPositionX() + 1);
                } else {              //la tortue "sort du plateau"
                    goToInitialPosition();
                }
                break;
            case NORTH:
                if (getPositionY() - 1 > -1) {
                    //il faut traiter le cas est-ce qu'il y a un mur ou une tortue, en créant une nouvelle fonction
                    setPositionY(getPositionY() - 1);
                } else {        //la tortue "sort du plateau"
                    goToInitialPosition();
                }
                break;
            case WEST:
                if (getPositionX() - 1 > -1) {
                    //il faut traiter le cas est-ce qu'il y a un mur ou une tortue, en créant une nouvelle fonction
                    setPositionX(getPositionX() - 1);
                } else {        //la tortue "sort du plateau"
                    goToInitialPosition();
                }
                break;
        }
    }

    private void goToInitialPosition() {
        System.out.println("Outch ! =( ");
        setPositionY(position[2]);
        setPositionX(position[3]);
        this.currentDirection = Direction.SOUTH; //prendre la direction initiale
    }
    public void checkTurtle(int line, int column){

    }


}
