package cell;

public abstract class Block extends Cell {

    public String getDescritpion() {
        return "I'm a block";
    }

    public String getName() {
        return "Block";
    }

    public boolean isDestructible() {
        return false;
    }   //par défaut on va dire qu'un block est indestructible
}
