package cell;

public class Empty extends Cell{
    private String name="Empty zone";

    public String getDescritpion() {
        return "I'm a block";
    }

    public String getName() {
        return this.name;
    }

    public Empty() {
    }
}
