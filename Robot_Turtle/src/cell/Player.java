package cell;

import card.Card;
import card.Deck;

import java.util.*;

public class Player extends Cell {
    private String color;
    private Direction direction = Direction.SOUTH;
    private Direction currentDirection = direction;   //direction SUD par défaut
    private int nbIce = 2;
    private int nbStone = 3;
    private int passageOrder;
    private ArrayDeque<Card> program = new ArrayDeque<>();
    private List<Card> handCard = new ArrayList<>();
    private ArrayDeque<Card> discard = new ArrayDeque<>();   //défausse
    private Deck deck = new Deck();
    private String name = "Turtle";
    private Scanner scanner = new Scanner(System.in);
    private int[] position = {0, 1, 0, 1};   //position[0] = ligne, position[1] = colonne, ici par défaut le joueur est à la ligne 0 et à la colonne 1
    //position[2] = ligne de départ, position[3] = colonne de départ, utilise lorsque la tortue se prend un laser par exemple
    private boolean hasWon = false;

    public Player() {
        int caseNum = convertPositionToInt(position[0], position[1]);
        this.positionPlayers.put(caseNum, this);   //enregistrer le joueur et sa position dans la variable static
    } //constructeur par défaut

    public Player(String name, String color, int passageOrder, int[] initialPosition) {
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

    private int[] getPosition() {
        return this.position;
    }

    public int getPositionY() {
        return this.position[0];
    }         //attention aux confusions !

    public int getPositionX() {
        return this.position[1];
    }

    private void setPositionY(int line) {
        this.position[0] = line;
    }

    private void setPositionX(int column) {
        this.position[1] = column;
    }

    public int getPassageOrder() {
        return this.passageOrder;
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
    }

    private void showHandCard() {
        System.out.println("--  Hand Card : " + handCard.size() + " --");
        for (int i = 0; i < handCard.size(); i++) {
            System.out.print(i + ". " + handCard.get(i).getName() + "\t");
        }
        System.out.println();
    }

    private void showWalls() {
        System.out.println(">> You have : " + nbIce + " ice blocks.");
        System.out.println(">> You have : " + nbStone + " stone walls.");
    }

    public void showDiscard() {    //pour montrer la défausse, quelqu'un voudra peut-être l'utiliser un jour
        System.out.println("--  Discard Card : " + discard.size() + " --");
        Iterator<Card> iterator = discard.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "\t");
        }
        System.out.println();
    }

    public void showProgram() {   //pour montrer le programme, quelqu'un voudra peut-être l'utiliser un jour
        System.out.println("--  Program Card : " + program.size() + " --");
        Iterator<Card> iterator = program.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "\t");
        }
        System.out.println();
    }

    private void addToProgram() {
        if (handCard.isEmpty()) {
            System.out.println("You don't have any cards to add to your program");
        } else {
            System.out.println("Which card do you want to add to your program ? (-1 to Stop)");
            while (!handCard.isEmpty()) {
                showHandCard();
                try {
                    int choice = scanner.nextInt();       //il faut controler si l'utilisateur saisie un décimal
                    if (choice > -1 && choice < handCard.size()) {
                        program.add(handCard.remove(choice));
                    } else if (choice == -1) {
                        break;
                    } else {
                        System.out.println("Incorrect index, please try again");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Saisie invalide");
                }
            }
        }
    }

    private void addToDiscard() {
        while (handCard.size() > 0) {
            for (int i = 0; i < handCard.size(); i++) {
                discard.addLast(handCard.remove(0));   //"déplacer" le premier élément de handCard à chaque fois
            }
        }
    }

    public void play() {
        showHandCard();
        showWalls();
        System.out.println("What do you want to do ?\n1. Complete the program\n2. Build a wall\n3. Execute the program.");
        int choiceMin = 1;
        int choiceMax = 3;
        int choice;
        do {
            try {
                choice = scanner.nextInt();
                if (!(choiceMin - 1 < choice && choice < choiceMax + 1)) {
                    System.out.println("Error, please try a correct choice");
                }
            } catch (InputMismatchException e) {
                System.out.println("Saisie invalide");
                choice = -1;
            }
        } while (!(choiceMin - 1 < choice && choice < choiceMax + 1));
        switch (choice) {
            case 1:
                addToProgram();
                break;
            case 2:
                setWall();
                break;
            case 3:
                executeProgram();
                break;
        }
        if (!hasWon) {    //si le joueur gagne, il ne manage pas ses cartes à la fin de son tour
            manageHandCard();
        }
    }

    private void manageHandCard() {
        int choice;
        if (!handCard.isEmpty()) {
            System.out.println("Do you want to discards your hand cards before picking new ones ? (1 : Yes ; 0 : No)");
            do
            {                                //défausser sa main et piocher 5 cartes ou piocher jusqu'à avoir 5 cartes
                try {
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
                } catch (InputMismatchException e) {
                    System.out.println("Saisie invalide");
                    choice = -1;
                }
            } while (!(choice == 0 || choice == 1));
        } else {   //main vide
            pickCardFromDeck();
        }

    }

    private void executeProgram() {
        while (!program.isEmpty() && !hasWon) {
            discard.addLast(program.peekFirst());
            String actualCard = program.removeFirst().getClass().getName();
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
                attackAhead();
            }
        }

    }

    private void attackAhead() {
        int gridLine = 8;
        int gridColumn = 8;
        int laserXPosition = getPositionX();
        int laserYPosition = getPositionY();
        boolean touched = false;
        while (!touched) {
            switch (this.currentDirection) {
                case SOUTH:
                    if (laserYPosition + 1 < gridLine) {
                        if (checkTurtle(laserYPosition + 1, laserXPosition)) {
                            if (positionPlayers.size() > 2) {      //si plus de deux joueurs, la tortue touchée retourne à sa position initiale
                                System.out.println("The turtle goes back to its initial position.");
                                goToInitialPosition(positionPlayers.get(convertPositionToInt(laserYPosition + 1, laserXPosition))); //la tortue touchée va dans sa position initiale
                            } else if (positionPlayers.size() == 2) {    //si deux joueurs, la tortue touchée fait demi-tour
                                turnAround(positionPlayers.get(convertPositionToInt(laserYPosition + 1, laserXPosition)));    //demi-tour
                                System.out.println("Turn around !");
                            }
                            touched = true;
                        } else if (checkJewel(laserYPosition + 1, laserXPosition)) {   //touche un joyau
                            if (positionPlayers.size() > 2) {      //si plus de deux joueurs, la tortue qui attaque retourne à sa position initiale
                                System.out.println("The turtle who attacked goes back to its initial position.");
                                goToInitialPosition(this); //la tortue qui attaque va dans sa position initiale
                            } else if (positionPlayers.size() == 2) {    //si deux joueurs, la tortue qui attaque fait demi-tour
                                turnAround(this);    //demi-tour
                                System.out.println("Turn around !");
                            }
                            touched = true;
                        } else if (checkWall(laserYPosition + 1, laserXPosition)) { //touche un mur
                            if (positionWalls.get(convertPositionToInt(laserYPosition + 1, laserXPosition)).getName().equals("Ice wall")) {
                                System.out.println("The laser touched an ice block!");
                                positionWalls.remove(convertPositionToInt(laserYPosition + 1, laserXPosition)); //on enlève le mur de glace touché
                            } else if (positionWalls.get(convertPositionToInt(laserYPosition + 1, laserXPosition)).getName().equals("Stone wall")) {
                                System.out.println("The laser touched a stone wall!");
                            }
                            touched = true;

                        } else {       //le laser continue son chemin
                            laserYPosition++;
                        }
                    } else {        //le laser "sort du plateau"
                        System.out.println("No target");
                        touched = true;   //sortir de la boucle
                    }
                    break;

                case NORTH:
                    if (laserYPosition - 1 > -1) {
                        if (checkTurtle(laserYPosition - 1, laserXPosition)) {
                            if (positionPlayers.size() > 2) {      //si plus de deux joueurs, la tortue touchée retourne à sa position initiale
                                System.out.println("The turtle goes back to its initial position.");
                                goToInitialPosition(positionPlayers.get(convertPositionToInt(laserYPosition - 1, laserXPosition))); //la tortue touchée va dans sa position initiale
                            } else if (positionPlayers.size() == 2) {    //si deux joueurs, la tortue touchée fait demi-tour
                                turnAround(positionPlayers.get(convertPositionToInt(laserYPosition - 1, laserXPosition)));    //demi-tour
                                System.out.println("Turn around !");
                            }
                            touched = true;
                        } else if (checkJewel(laserYPosition - 1, laserXPosition)) {
                            if (positionPlayers.size() > 2) {      //si plus de deux joueurs, la tortue qui attaque retourne à sa position initiale
                                System.out.println("The turtle who attacked goes back to its initial position.");
                                goToInitialPosition(this); //la tortue qui attaque va dans sa position initiale
                            } else if (positionPlayers.size() == 2) {    //si deux joueurs, la tortue qui attaque fait demi-tour
                                turnAround(this);    //demi-tour
                                System.out.println("Turn around !");
                            }
                            touched = true;
                        } else if (checkWall(laserYPosition - 1, laserXPosition)) { //touche un mur
                            if (positionWalls.get(convertPositionToInt(laserYPosition - 1, laserXPosition)).getName().equals("Ice wall")) {
                                System.out.println("The laser touched an ice block!");
                                positionWalls.remove(convertPositionToInt(laserYPosition - 1, laserXPosition));
                            } else if (positionWalls.get(convertPositionToInt(laserYPosition - 1, laserXPosition)).getName().equals("Stone wall")) {
                                System.out.println("The laser touched a stone wall!");
                            }
                            touched = true;

                        } else {       //le laser continue son chemin
                            laserYPosition--;
                        }
                    } else {        //le laser "sort du plateau"
                        System.out.println("No target");
                        touched = true;   //sortir de la boucle
                    }
                    break;

                case EAST:
                    if (laserXPosition + 1 < gridColumn) {
                        if (checkTurtle(laserYPosition, laserXPosition + 1)) {
                            if (positionPlayers.size() > 2) {      //si plus de deux joueurs, la tortue touchée retourne à sa position initiale
                                System.out.println("The turtle goes back to its initial position.");
                                goToInitialPosition(positionPlayers.get(convertPositionToInt(laserYPosition, laserXPosition + 1))); //la tortue touchée va dans sa position initiale
                            } else if (positionPlayers.size() == 2) {    //si deux joueurs, la tortue touchée fait demi-tour
                                turnAround(positionPlayers.get(convertPositionToInt(laserYPosition, laserXPosition + 1)));    //demi-tour
                                System.out.println("Turn around !");
                            }
                            touched = true;
                        } else if (checkJewel(laserYPosition, laserXPosition + 1)) {
                            if (positionPlayers.size() > 2) {      //si plus de deux joueurs, la tortue qui attaque retourne à sa position initiale
                                System.out.println("The turtle who attacked goes back to its initial position.");
                                goToInitialPosition(this); //la tortue qui attaque va dans sa position initiale
                            } else if (positionPlayers.size() == 2) {    //si deux joueurs, la tortue qui attaque fait demi-tour
                                turnAround(this);    //demi-tour
                                System.out.println("Turn around !");
                            }
                            touched = true;
                        } else if (checkWall(laserYPosition, laserXPosition + 1)) { //touche un mur
                            if (positionWalls.get(convertPositionToInt(laserYPosition, laserXPosition + 1)).getName().equals("Ice wall")) {
                                System.out.println("The laser touched an ice block!");
                                positionWalls.remove(convertPositionToInt(laserYPosition, laserXPosition + 1));
                            } else if (positionWalls.get(convertPositionToInt(laserYPosition, laserXPosition + 1)).getName().equals("Stone wall")) {
                                System.out.println("The laser touched a stone wall!");
                            }
                            touched = true;
                        } else {       //le laser continue son chemin
                            laserXPosition++;
                        }
                    } else {        //le laser "sort du plateau"
                        System.out.println("No target");
                        touched = true;   //sortir de la boucle
                    }
                    break;

                case WEST:
                    if (laserXPosition - 1 > -1) {
                        if (checkTurtle(laserYPosition, laserXPosition - 1)) {
                            if (positionPlayers.size() > 2) {      //si plus de deux joueurs, la tortue touchée retourne à sa position initiale
                                System.out.println("The turtle goes back to its initial position.");
                                goToInitialPosition(positionPlayers.get(convertPositionToInt(laserYPosition, laserXPosition - 1))); //la tortue touchée va dans sa position initiale
                            } else if (positionPlayers.size() == 2) {    //si deux joueurs, la tortue touchée fait demi-tour
                                turnAround(positionPlayers.get(convertPositionToInt(laserYPosition, laserXPosition - 1)));    //demi-tour
                                System.out.println("Turn around !");
                            }
                            touched = true;

                        } else if (checkJewel(laserYPosition, laserXPosition - 1)) {
                            if (positionPlayers.size() > 2) {      //si plus de deux joueurs, la tortue qui attaque retourne à sa position initiale
                                System.out.println("The turtle who attacked goes back to its initial position.");
                                goToInitialPosition(this); //la tortue qui attaque va dans sa position initiale
                            } else if (positionPlayers.size() == 2) {    //si deux joueurs, la tortue qui attaque fait demi-tour
                                turnAround(this);    //demi-tour
                                System.out.println("Turn around !");
                            }
                            touched = true;
                        } else if (checkWall(laserYPosition, laserXPosition - 1)) { //touche un mur
                            if (positionWalls.get(convertPositionToInt(laserYPosition, laserXPosition + 1)).getName().equals("Ice wall")) {
                                System.out.println("The laser touched an ice block!");
                                positionWalls.remove(convertPositionToInt(laserYPosition, laserXPosition + 1));
                            } else if (positionWalls.get(convertPositionToInt(laserYPosition, laserXPosition + 1)).getName().equals("Stone wall")) {
                                System.out.println("The laser touched a stone wall!");
                            }
                            touched = true;
                        } else {       //le laser continue son chemin
                            laserXPosition--;
                        }

                    } else {        //le laser "sort du plateau"
                        System.out.println("No target");
                        touched = true;   //sortir de la boucle
                    }
                    break;
            }
        }

    }

    private void turnAround(Player player) {
        player.currentDirection = player.direction.changeDirClock().changeDirClock();               //faire demi-tour
        player.direction = player.currentDirection;  //mettre à jour la direction pour la prochaine fois
        System.out.println("New direction of the target : " + player.currentDirection);
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
                    } else if (checkWall(getPositionY() + 1, getPositionX())) {
                        System.out.println("There's a wall here, you turn around!");
                        turnAround(this);
                    } else {       //on peut avancer
                        communicateNewPosition(this, true, false, 1);
                    }
                } else {        //la tortue "sort du plateau"
                    if (!this.hasWon) {     //si le joueur n'a pas gagné avant de sortir du plateau (utile vers la fin si son programme le permet d'atteindre le joyau, mais le fait "sortir" après)
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
                    } else if (checkWall(getPositionY(), getPositionX() + 1)) {
                        System.out.println("There's a wall here, you turn around!");
                        turnAround(this);
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
                    } else if (checkWall(getPositionY() - 1, getPositionX())) {
                        System.out.println("There's a wall here, you turn around!");
                        turnAround(this);
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
                    } else if (checkWall(getPositionY(), getPositionX() - 1)) {
                        System.out.println("There's a wall here, you turn around!");
                        turnAround(this);
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
        if (checkWall(player.getPosition()[2], player.getPosition()[3])) {     //s'il y a un mur sur la position initiale du joueur, il sera détruit
            positionWalls.remove(convertPositionToInt(player.getPosition()[2], player.getPosition()[3]));
        }
        positionPlayers.remove(convertPositionToInt(player.getPositionY(), player.getPositionX()));
        player.setPositionY(player.getPosition()[2]);
        player.setPositionX(player.getPosition()[3]);
        player.currentDirection = Direction.SOUTH; //prendre la direction initiale
        player.direction = player.currentDirection;   //enregistrer la direction pour la prochaine fois
        positionPlayers.put(convertPositionToInt(player.getPositionY(), player.getPositionX()), player);
    }

    private boolean checkTurtle(int line, int column) {
        return positionPlayers.containsKey(convertPositionToInt(line, column));
    }

    private boolean checkWall(int line, int column) { //renvoie true si mur
        return positionWalls.containsKey(convertPositionToInt(line, column));
    }

    private boolean checkJewel(int line, int column) {
        return positionJewels.containsKey(convertPositionToInt(line, column));
    }

    private void communicateNewPosition(Player player, boolean Ymove, boolean Xmove, int value) {    //ne permet pas de bouger sur Y et X en même temps
        positionPlayers.remove(convertPositionToInt(player.getPositionY(), player.getPositionX()));
        if (Ymove) {
            player.setPositionY(getPositionY() + value);
        } else if (Xmove) {
            player.setPositionX(getPositionX() + value);
        }
        positionPlayers.put(convertPositionToInt(player.getPositionY(), player.getPositionX()), player);
        checkIfWin(player);
    }

    private void checkIfWin(Player player) {
        if (positionJewels.containsKey(convertPositionToInt(player.getPositionY(), player.getPositionX()))) {
            System.out.println("Congratulations ! " + player.getName() + " is qualified !");
            player.hasWon = true;
            positionPlayers.remove(convertPositionToInt(player.getPositionY(), player.getPositionX())); //pour éviter que les tortues retournent à leur case départ si deux tortues vont sur le même joyau
        }
    }

    //Partie pour vérifier l'encerclement :

    private boolean isSuRrouded(int i, int j, int[][][] tableau) {
        if (tableau[i][j][0] == 2) {//a player is the number 2
            tableau[0][0][2] += 1;
        }
        tableau[i][j][1] = 0;//we set this to avoid calling the function on a cell already test
        if (i < 7 && tableau[i + 1][j][1] == 1 && tableau[i + 1][j][0] != 1) {
            isSuRrouded(i + 1, j, tableau);
        }
        if (j < 7 && tableau[i][j + 1][1] == 1 && tableau[i][j + 1][0] != 1) {
            isSuRrouded(i, j + 1, tableau);
        }
        if (i > 0 && tableau[i - 1][j][1] == 1 && tableau[i - 1][j][0] != 1) {
            isSuRrouded(i - 1, j, tableau);
        }
        if (j > 0 && tableau[i][j - 1][1] == 1 && tableau[i][j - 1][0] != 1) {
            isSuRrouded(i, j - 1, tableau);
        }
        if (tableau[0][0][2] == positionPlayers.size()) {
            return false;
        } else {
            return true;
        }
    }
    //fin de vérifie l'encerclement

    private void setWall() {
        Scanner scan = new Scanner(System.in);
        int gridLine = 8;
        int gridColumn = 8;
        int wallType;
        int x;
        int y;
        int pos = 0;
        boolean valid = true;
        StoneWall stoneWall = new StoneWall();
        IceWall iceWall = new IceWall();
        if (nbIce != 0 && nbStone != 0) {
            do {
                try {
                    System.out.println("Which type of wall do you want to insert ? ");
                    System.out.println("1 : An icewall");
                    System.out.println("2 : A stonewall");
                    wallType = scan.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Saisie invalide");
                    wallType = 0;
                }
            } while (wallType != 1 && wallType != 2);
        } else {
            if (nbStone == 0 && nbIce != 0) {
                wallType = 1;
            } else {
                if (nbStone != 0 && nbIce == 0) {
                    wallType = 2;
                } else {
                    wallType = 0;
                }
            }
        }
        if (wallType != 0) {
            do {
                do {
                    try {
                        System.out.print("In which line do you want to insert this wall ? ");
                        x = scan.nextInt() - 1;//pour que le joueur joue ligne 1 à 8 au lieu de 0 à 7
                    } catch (InputMismatchException e) {
                        System.out.println("Saisie invalide");
                        x = -1;
                    }
                } while ((x < 0) || (x > gridColumn - 1));
                do {
                    try {
                        System.out.print("In which column do you want to insert this wall ? ");
                        y = scan.nextInt() - 1;
                    } catch (InputMismatchException e) {
                        System.out.println("Saisie invalide");
                        y = -1;
                    }
                } while ((y < 0) || (y > gridLine - 1));
                valid = true;
                pos = convertPositionToInt(x, y);
                if (!isAvailable(pos)) {
                    System.out.println("Unavailable cell, please choose another one !");
                } else {
                    int key = positionJewels.firstKey();
                    for (int i = 0; i < positionJewels.size(); i++) {
                        int[][][] tableau = posArrayBool(8, 8);
                        if (wallType == 2) {       //si on pose un mur de pierre
                            tableau[x][y][0] = 1;     //alors on fait comme si le mur est posé pour tester la validité des chemins possibles
                        }
                        if (this.isSuRrouded(key / 10, key % 10, tableau)) {
                            System.out.println("Illegal wall : it won't be possible for one of the turtle to touch the jewel on x = " + (key / 10 + 1) + " and y = " + (key % 10 + 1));
                            valid = false;
                        }
                        try {
                            key = positionJewels.higherKey(key);
                        } catch (NullPointerException e) {
                            i = 100;
                        }
                    }
                }
            } while (!isAvailable(pos) || !valid);
            if (wallType == 1) {
                positionWalls.put(pos, iceWall);
                nbIce--;
            } else if (wallType == 2) {
                positionWalls.put(pos, stoneWall);
                nbStone--;
            } else {
                System.out.println("You don't have anymore walls !");
            }
        }
    }
}

