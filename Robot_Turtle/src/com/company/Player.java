package com.company;
//package Card;

import card.Card;
import card.Deck;

import java.util.List;

public class Player extends Cell {
    private String color;
    public static Direction direction;
    private Direction currentDirection = direction;   //direction Nord par défaut
    private int score;
    private int passageOrder;
    private List<Card> program;
    private List<Card> handCard;
    private List<Block> blocks;
    private Deck discard;   //défausse
    private Deck deck = new Deck();
    private String name;

    public Player() {
        this.direction = Direction.SOUTH;
    } //constructeur par défaut


    public String getColor() {
        return color;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public int getPassageOrder() {
        return passageOrder;
    }

    public List<Card> getProgram() {
        return program;
    }

    public List<Card> getHandCard() {
        return handCard;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

//    public Card getDeck() {
//        return deck;
//    }

    public String getName() {
        return name;
    }

    private static Card pickCardFromDeck() {
        //à compléter
        return null;     //à modifier
    }
}
