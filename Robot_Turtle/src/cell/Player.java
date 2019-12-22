package cell;
//package Card;

import card.*;

import java.util.List;

public class Player extends Cell {
    private String color;
    private Direction direction;
    private Direction currentDirection = direction;   //direction Nord par défaut
    private int score;
    private int passageOrder;
    private List<Card> program;
    private List<Card> handCard;
    private List<Block> blocks;
    private Deck discard;   //défausse
    private Deck deck = new Deck();
    private String name = "Turtle";

    public Player() {
        this.direction = Direction.SOUTH;
    } //constructeur par défaut

    public Player( String name, String color, Direction currentDirection, int passageOrder) {
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
    public void setCurrentDirection(Direction newDirection){
        this.direction=newDirection; //à vérifier
    }
    public int getPassageOrder() {
        return this.passageOrder;
    }

    public List<Card> getProgram() {
        return this.program;
    }

    public List<Card> getHandCard() {
        return this.handCard;
    }

    public List<Block> getBlocks() {
        return this.blocks;
    }

//    public Card getDeck() {
//        return deck;
//    }

    public String getName() {
        return this.name;
    }

    private static Card pickCardFromDeck() {
        //à compléter
        return null;     //à modifier
    }
}
