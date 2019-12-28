package cell;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public Direction changeDirClock() {
        switch (this.name()){
            case "SOUTH":
                return this.WEST;
            case "WEST":
                return this.NORTH;
            case "NORTH":
                return this.EAST;
            case "EAST":
                return this.SOUTH;
            default:
                throw new IllegalStateException("Unexpected value: " + this.name());
        }
    }

    public Direction changeDirAntiClock() {
        switch (this.name()){
            case "SOUTH":
                return this.EAST;
            case "EAST":
                return this.NORTH;
            case "NORTH":
                return this.WEST;
            case "WEST":
                return this.SOUTH;
            default:
                throw new IllegalStateException("Unexpected value: " + this.name());
        }
    }
}
