package cell;
//package Card;

import card.*;

import java.util.*;

public class Player extends Cell {
    private String color;
    private Direction direction;
    private Direction currentDirection = direction;   //direction Nord par défaut
    private int score;
    private int passageOrder;
    private ArrayDeque program = new ArrayDeque();
    private List<Card> handCard = new ArrayList<>();
    private List<Block> blocks;
    private ArrayDeque discard = new ArrayDeque();   //défausse
    private Deck deck = new Deck();
    private String name = "Turtle";
    private Scanner scanner = new Scanner(System.in);

    public Player() {
        this.direction = Direction.SOUTH;
    } //constructeur par défaut

    public Player(String name, String color, Direction currentDirection, int passageOrder) {
        this.color = color;
        this.currentDirection = currentDirection;
        this.passageOrder = passageOrder;
        this.name = name;
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
        showHandCard();
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

    public void addToDiscard(){
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
            if(!(choiceMin - 1 < choice && choice < choiceMax + 1)){
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
                //execute the program
                break;
            /*default:
                System.out.println("Error, please try a correct choice");
                break;*/
        }

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
}
