package com.company;

public class StoneWall {
    private String description="Je bloque le chemin, rien ne peut me détruire";
    private String name="Mur de pierre";

    public String getDescription() {
        return description;
    }

    public StoneWall() {
    }

    public String getName(){
        return name;
    }
    public boolean isDestructible(){
        return false;
    }
}
