package card;

public class YellowCard implements Card {
    private String name = "Yellow Card";
    private String description = "Rotate the turtle 90° anticlockwise.";

    public YellowCard() {
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
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
