package card;


import java.util.Stack;

public class Deck {
    private Stack<Card> deck;

    public Deck() {
        this.deck = shuffle();
    }


    public Stack<Card> shuffle() {
        int[] mixedCard = new int[37];    //pour savoir si la carte a déjà été utilisé pour le mélange
        int random;
        int nbBlueCard = 18;
        int nbYellowCard = 8;
        int nbPurpleCard = 8;
        int nbLaserCard = 3;
        int nbCardMax = nbBlueCard + nbLaserCard + nbPurpleCard + nbYellowCard;
        if (!deck.isEmpty()) {
            deck.removeAllElements();
        }  //pas de else, sinon il vide et c'est tout
        while (deck.size() < 37) {
            do {
                random = (int) (Math.random() * (nbCardMax + 1));   //donne un entier tel que   0 <= entier <nbCardMax+1
            } while (mixedCard[random] != 0);     //exemple si mixedCard[random] == 1, alors la boucle regénère un nombre aléatoire

            if (random < nbBlueCard) {
                deck.add(new BlueCard());
            } else if ((nbBlueCard - 1) < random && random < (nbBlueCard + nbYellowCard)) {
                deck.add(new YellowCard());
            } else if ((nbBlueCard + nbYellowCard - 1) < random && random < (nbBlueCard + nbYellowCard + nbPurpleCard)) {
                deck.add(new PurpleCard());
            } else {      //  (nbCardMax-nbLaserCard-1) < random < (nbCardMax)
                deck.add(new LaserCard());
            }
            mixedCard[random] = 1;   //pour pas que le cas actuel de random se répète (éviter deux fois random = 5 par exemple)
        }

        return deck;
    }
}
