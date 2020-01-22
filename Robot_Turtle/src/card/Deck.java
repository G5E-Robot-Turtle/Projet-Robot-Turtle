package card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

public class Deck {
    private Stack<Card> deck = new Stack<>();

    public Stack<Card> getDeck() {
        return deck;
    }

    public Deck() {
        this.deck = shuffle();
    }

    public boolean isEmpty() {
        return deck.isEmpty();
    }

    public Stack<Card> shuffle() {      //créer et mélange le deck
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
                random = (int) (Math.random() * (nbCardMax));   //donne un entier tel que   0 <= entier <nbCardMax
            } while (mixedCard[random] != 0);     //exemple si mixedCard[random] == 1, alors la boucle génère un autre nombre aléatoire

            if (random < nbBlueCard) {
                deck.add(new BlueCard());
            } else if ((nbBlueCard - 1) < random && random < (nbBlueCard + nbYellowCard)) {
                deck.add(new YellowCard());
            } else if ((nbBlueCard + nbYellowCard - 1) < random && random < (nbBlueCard + nbYellowCard + nbPurpleCard)) {
                deck.add(new PurpleCard());
            } else {      //  (nbCardMax-nbLaserCard-1) < random < (nbCardMax)
                deck.add(new LaserCard());
            }
            mixedCard[random] = 1;   //pour pas que l'entier actuel de random soit réétudié (éviter d'étudier deux fois le cas random = 5 par exemple)
            //en effet, le do while regénèrera un autre entier.
        }
        return deck;
    }

    public Stack<Card> shuffle(ArrayDeque<Card> discard) {      // prend la défausse et la mélange
        int[] mixedCard = new int[discard.size()];    //pour savoir si la carte a déjà été utilisé pour le mélange
        ArrayList<Card> discardList = new ArrayList<>();
        while (!discard.isEmpty()) {
            discardList.add(discard.removeFirst());
        }
        int nbCardMax = discardList.size();
        int random;
        if (!deck.isEmpty()) {
            deck.removeAllElements();
        }
        while (deck.size() < nbCardMax) {
            do {
                random = (int) (Math.random() * (nbCardMax));   //donne un entier tel que   0 <= entier <nbCardMax
            } while (mixedCard[random] != 0);     //exemple si mixedCard[random] == 1, alors la boucle génère un autre nombre aléatoire

            deck.add(discardList.get(random));
            mixedCard[random] = 1;   //pour pas que l'entier actuel de random soit réétudié (éviter d'étudier deux fois le cas random = 5 par exemple)
            //en effet, le do while regénèrera un autre entier.
        }
        return deck;
    }


    public void showDeck() {                   //peut être utile pour plus tard, si un jour on veut afficher le deck
        System.out.println("Deck size : " + deck.size());
        for (Card card : deck) {
            System.out.println(card);
        }
    }

    public Card pick() {
        if (!deck.isEmpty()) {
            return this.deck.pop();
        } else {
            System.out.println("Deck is empty");
            return null;
        }
    }
}
