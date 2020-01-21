package card;

public class YellowCard implements Card {
    private String name = "Yellow Card";
    private String description = "Rotate the turtle 90° anticlockwise.";

    public YellowCard() {

    }

    @Override
    public String getName() {
        return this.name;
    }

}
