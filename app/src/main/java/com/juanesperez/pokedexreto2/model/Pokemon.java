package com.juanesperez.pokedexreto2.model;


public class Pokemon {

    private String name;
    private SpriteGroup sprites;
    private Stat[] stats;
    private int life;

    public Pokemon() {

    }

    public Pokemon(String name,SpriteGroup sprites, Stat[] stats, int life) {
        this.name = name;
        this.sprites = sprites;
        this.stats = stats;
        this.life = life;

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

    public Stat[] getStats() {
        return stats;
    }

    public void setStats(Stat[] stats) {
        this.stats = stats;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
