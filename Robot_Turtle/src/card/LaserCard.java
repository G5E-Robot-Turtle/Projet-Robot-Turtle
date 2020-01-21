package card;

public class LaserCard implements Card {
    private String name = "Laser Card";
    private String description = "Attack the first obstacle on the way, be careful if the obstacle is a Jewel, the laser is reflected.";

    public LaserCard() {    //constructeur par défaut

    }
    
    @Override
    public String getName() {
        return this.name;
    }

}
