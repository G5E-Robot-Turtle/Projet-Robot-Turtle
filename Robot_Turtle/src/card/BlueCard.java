package card;

public class BlueCard implements Card {
    private String name = "Blue Card";
    private String description = "Make the turtle move one square forward.";

    public BlueCard() {

    }

    @Override
    public String getName() {
        return this.name;
    }

}
