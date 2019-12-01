package Card;

public class YellowCard implements Card {
    private String name="Carte jaune";
    private String description="Fait tourner la tortue de 90° dans le sens anti-horaire";

    public YellowCard() {
        this.name=name;   //mettre getName() si y'a un bug
        this.description=description;
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
