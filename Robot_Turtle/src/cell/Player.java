package cell;
//package Card;

import card.*;
import com.company.Grid;
import java.util.*;

public class Player extends Cell {
    private String color;
    private Direction direction = Direction.SOUTH;
    private Direction currentDirection = direction;   //direction SUD par défaut
    private int score;
    private int passageOrder;
    private ArrayDeque<Card> program = new ArrayDeque<>();
    private List<Card> handCard = new ArrayList<>();
    private List<Block> blocks;
    private ArrayDeque<Card> discard = new ArrayDeque<>();   //défausse
    private Deck deck = new Deck();
    private String name = "Turtle";
    private Scanner scanner = new Scanner(System.in);
    private int[] position = {0, 1, 0, 1};   //position[0] = ligne, position[1] = colonne, ici par défaut le joueur est à la ligne 0 et à la colonne 1
    //position[2] = ligne de départ, position[3] = colonne de départ, utilise lorsque la tortue se prend un laser par exemple
    private int[] previousPosition = new int[2];  //enregistre la position précédente de la tortue, utile pour mettre à jour uniquement les cellules qui ont été modifiées
    private boolean hasWon = false;
    public static TreeMap<Integer, Player> positionPlayers = new TreeMap<>();  //<position, Player> pour savoir  s'il y a un joueur à une telle position
    //Integer en clé car TreeMap n'accepte pas les tableaux en clé, ni Player
    //mettre en private ? static pour enregistrer/synchroniser la position de tous les joueurs créés afin de gérer les collisions

    public Player() {
        int caseNum = convertPositionToInt(position[0], position[1]);
        this.positionPlayers.put(caseNum, this);   //enregistrer le joueur et sa position dans la variable static
    } //constructeur par défaut

    public Player(String name, String color, int passageOrder, int initialPosition[]) {
        this.name = name;

        this.color = color;
        this.passageOrder = passageOrder;
        this.position = initialPosition;
        int caseNum = convertPositionToInt(initialPosition[0], initialPosition[1]);  // convertit un tableau de position en nombre entier
        positionPlayers.put(caseNum, this);  //enregistrer le joueur et sa position dans la variable static
    }

    public boolean getHasWon() {
        return this.hasWon;
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
            if (!deck.isEmpty()) {
                handCard.add(deck.pick());
            } else {   //le deck est vide
                if (!discard.isEmpty()) {
                    deck.shuffle(discard);   //on récupère la défausse et la mélange
                } else {           //deck et défausse vides, tout est dans le programme
                    System.out.println("You have " + handCard.size() + " hand cards and there isn't anymore card,\nyou need to execute your program.");
                    break;   //sortir de la boucle
                }

            }

        }
//        showHandCard();
    }

    public void showHandCard() {
        System.out.println("--  Hand Card : " + handCard.size() + " --");
        for (int i = 0; i < handCard.size(); i++) {
            System.out.print(i + ". " + handCard.get(i).getName() + "\t");
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
                int choice = scanner.nextInt();       //il faut controler si l'utilisateur saisie un décimal
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
                manageHandCard();
                break;
            case 2:
                //build a wall
                break;
            case 3:
                executeProgram();
                break;
        }
    }

    public void manageHandCard() {
        int choice;
        if (!handCard.isEmpty()) {
            System.out.println("Do you want to discards your hand cards before picking new ones ? (1 : Yes ; 0 : No)");
            do
            {                                //défausser sa main et piocher 5 cartes ou piocher jusqu'à avoir 5 cartes
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
        } else {   //main vide
            pickCardFromDeck();
        }

    }

    public void executeProgram() {
        while (!program.isEmpty() && !hasWon) {
            discard.addLast(program.peekFirst());
            String actualCard = program.removeFirst().getClass().getName();
//            System.out.println(program.removeFirst().getClass().getName());   //on obtient le nom de la Classe (ex: card.BlueCard)
            if (actualCard.equals("card.BlueCard")) {
                System.out.println("Go go go !");
                goAhead();
            } else if (actualCard.equals("card.PurpleCard")) {
                this.currentDirection = direction.changeDirClock();
                this.direction = this.currentDirection;  //mettre à jour la direction pour la prochaine fois
                System.out.println("New direction : " + this.currentDirection);
            } else if (actualCard.equals("card.YellowCard")) {
                this.currentDirection = direction.changeDirAntiClock();
                this.direction = this.currentDirection;   //mettre à jour la direction pour la prochaine fois
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
                    if (checkTurtle(getPositionY() + 1, getPositionX())) {   //il faut gérer le cas s'il y a un mur avec un else if par exemple
                        System.out.println("The two turtles go back to their initial position.");
                        goToInitialPosition(positionPlayers.get(convertPositionToInt(getPositionY() + 1, getPositionX()))); //la tortue cognée va aussi dans sa position initiale, important de faire "rentrer" la tortue cognée d'abord, sinon bug (getPosition.. change)
                        goToInitialPosition(this);
                    } else {       //on peut avancer
                        communicateNewPosition(this, true, false, 1);
                    }
                } else {        //la tortue "sort du plateau"
                    if (!this.hasWon) {     //si le joueur n'a pas gagné avant de sortir du plateau (vers la fin si son programme le permet d'atteindre le joyau, mais le fait "sortir" après
                        // il retourne à la position initiale
                        goToInitialPosition(this);
                    }
                }

                break;
            case EAST:
                if (getPositionX() + 1 < gridColumn) {
                    if (checkTurtle(getPositionY(), getPositionX() + 1)) {   //il faut gérer le cas s'il y a un mur avec un else if par exemple
                        System.out.println("The two turtles go back to their initial position.");
                        goToInitialPosition(positionPlayers.get(convertPositionToInt(getPositionY(), getPositionX() + 1))); //la tortue cognée va aussi dans sa position initiale, important de faire "rentrer" la tortue cognée d'abord, sinon bug (getPosition.. change)
                        goToInitialPosition(this);
                    } else {            //on peut avancer
                        communicateNewPosition(this, false, true, 1);
                    }
                } else {              //la tortue "sort du plateau"
                    if (!this.hasWon) {     //si le joueur n'a pas gagné avant de sortir du plateau
                        goToInitialPosition(this);
                    }
                }
                break;
            case NORTH:
                if (getPositionY() - 1 > -1) {
                    if (checkTurtle(getPositionY() - 1, getPositionX())) {   //il faut gérer le cas s'il y a un mur avec un else if par exemple
                        System.out.println("The two turtles go back to their initial position.");
                        goToInitialPosition(positionPlayers.get(convertPositionToInt(getPositionY() - 1, getPositionX()))); //la tortue cognée va aussi dans sa position initiale, important de faire "rentrer" la tortue cognée d'abord, sinon bug (getPosition.. change)
                        goToInitialPosition(this);
                    } else {       //on peut avancer
                        communicateNewPosition(this, true, false, -1);
                    }
                } else {        //la tortue "sort du plateau"
                    if (!this.hasWon) {     //si le joueur n'a pas gagné avant de sortir du plateau, les joyaux ne sont pas en haut de la grille, mais bon au cas où si on modifie la place des joyaux
                        goToInitialPosition(this);
                    }
                }
                break;
            case WEST:
                if (getPositionX() - 1 > -1) {
                    if (checkTurtle(getPositionY(), getPositionX() - 1)) {   //il faut gérer le cas s'il y a un mur avec un else if par exemple
                        System.out.println("The two turtles go back to their initial position.");
                        goToInitialPosition(positionPlayers.get(convertPositionToInt(getPositionY(), getPositionX() - 1))); //la tortue cognée va aussi dans sa position initiale, important de faire "rentrer" la tortue cognée d'abord, sinon bug (getPosition.. change)
                        goToInitialPosition(this);
                    } else {            //on peut avancer
                        communicateNewPosition(this, false, true, -1);
                    }
                } else {        //la tortue "sort du plateau"
                    if (!this.hasWon) {     //si le joueur n'a pas gagné avant de sortir du plateau
                        goToInitialPosition(this);
                    }
                }
                break;
        }
    }

    private void goToInitialPosition(Player player) {
        System.out.println("Outch ! =( ");
        positionPlayers.remove(convertPositionToInt(player.getPositionY(), player.getPositionX()));
        updatePreviousPosition(player);
        player.setPositionY(player.getPosition()[2]);
        player.setPositionX(player.getPosition()[3]);
        player.currentDirection = Direction.SOUTH; //prendre la direction initiale
        positionPlayers.put(convertPositionToInt(player.getPositionY(), player.getPositionX()), player);
    }

    //faire une variable static treemap? qui enregistre la position des joueurs pour savoir gérer les collisions
    // une autre variable static pour entregistrer la position tes murs ?

    public boolean checkTurtle(int line, int column) {
        return positionPlayers.containsKey(convertPositionToInt(line, column));
    }


    public void updatePreviousPosition(Player player) {
        player.setPreviousPositionY(player.getPositionY());
        player.setPreviousPositionX(player.getPositionX());
    }

    public void communicateNewPosition(Player player, boolean Ymove, boolean Xmove, int value) {    //ne permet pas de bouger sur Y et X en même temps
        positionPlayers.remove(convertPositionToInt(player.getPositionY(), player.getPositionX()));
        updatePreviousPosition(player);
        if (Ymove) {
            player.setPositionY(getPositionY() + value);
        } else if (Xmove) {
            player.setPositionX(getPositionX() + value);
        }
        positionPlayers.put(convertPositionToInt(player.getPositionY(), player.getPositionX()), player);
        checkIfWin(player);
    }

    public void checkIfWin(Player player) {
        if (positionJewels.containsKey(convertPositionToInt(player.getPositionY(), player.getPositionX()))) {
            System.out.println("Congratulations ! " + player.getName() + " is qualified !");
            player.hasWon = true;
            positionPlayers.remove(convertPositionToInt(player.getPositionY(), player.getPositionX())); //pour éviter que les tortues retournent à leur case départ si deux tortues vont sur le même joyau
        }
    }
    public void setWall(Grid grid) {
        Scanner scan = new Scanner(System.in);
        int wallType;
        int x;
        int y;
        StoneWall stoneWall = new StoneWall();
        IceWall iceWall = new IceWall();
        do {
            System.out.println("Which type of wall do you want to insert?");
            System.out.println("1 : An icewall");
            System.out.println("2 : A stonewall");
            wallType = scan.nextInt();
        } while (wallType != 1 && wallType != 2);
        do {
            System.out.print("In which line do you want to insert this wall?");
            x = scan.nextInt();
        } while ((x < 1) || (x > grid.getColumn()));
        do {
            System.out.print("In which column do you want to insert this wall?");
            y = scan.nextInt();
        } while ((y < 1) || (y > grid.getLine()));
        if (wallType == 1) {
            grid.setCellGrid(x, y, iceWall);
        } else {
            grid.setCellGrid(x, y, stoneWall);
        }
    }
}

