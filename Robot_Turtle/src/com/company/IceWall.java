package com.company;

public class IceWall extends Block {
    private String description = "Je bloque le chemin, mais je suis destructible par le laser";
    private String name = "Mur de glace";

    public String getDescription() {
        return description;
    }

    public IceWall() {
    }

    public String getName() {
        return name;
    }

    public boolean isDestructible() {
        return true;
    }
}
