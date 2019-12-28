package card;

public class LaserCard implements Card {
    private String name = "Laser Card";
    private String description = "Attack the first obstacle on the way, be careful if the obstacle is a Jewel, the laser is reflected.";

    public LaserCard() {    //constructeur par défaut
        this.name = name;   //mettre getName() si y'a un bug
        this.description = description;   //mettre getDescrition() si y'a un bug
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
