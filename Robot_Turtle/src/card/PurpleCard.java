package card;

public class PurpleCard implements Card {
    private String name = "Purple Card";
    private String description = "Rotate the turtle 90° clockwise";

    public PurpleCard() {

    }

    @Override
    public String getName() {
        return this.name;
    }

}
