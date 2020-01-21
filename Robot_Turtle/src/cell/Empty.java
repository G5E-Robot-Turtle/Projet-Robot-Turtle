package cell;

public class Empty extends Cell {
    private String name = "EmptyZone";

    public String getDescription() {
        return "I'm an empty zone";
    }

    public String getName() {
        return this.name;
    }

    public Empty() {

    }
}
