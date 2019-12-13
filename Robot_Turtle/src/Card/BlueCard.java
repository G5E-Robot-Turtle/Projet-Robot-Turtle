package Card;

public class BlueCard implements Card {
    private String name = "Carte Bleu";
    private String description = "Fait avancer la tortue d'une case";

    public BlueCard() {
    }

    public BlueCard(String name, String description) {
        this.name = name;   //mettre getName() si y'a un bug
        this.description = description;
    }

    @Override
    public void playCard() {
//        super.playCard();           //cette ligne a été écrite toute seule   ,
        // PS:  super() c'est pour faire appel aux variables, méthodes de la classe mère dans nos constructeurs
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
