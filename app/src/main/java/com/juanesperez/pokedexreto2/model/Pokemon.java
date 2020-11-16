package com.juanesperez.pokedexreto2.model;


public class Pokemon {

    private String name;
    private SpriteGroup sprites;
    /*private String defense;
    private String attack;
    private String speed;
    private String life;*/

    public Pokemon() {

    }

    public Pokemon(String name,SpriteGroup sprites) {
        this.name = name;
        this.sprites = sprites;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SpriteGroup getSprites() {
        return sprites;
    }

    public void setSprites(SpriteGroup sprites) {
        this.sprites = sprites;
    }
}
