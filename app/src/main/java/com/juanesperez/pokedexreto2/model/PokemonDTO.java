package com.juanesperez.pokedexreto2.model;


import java.io.Serializable;

public class PokemonDTO implements Serializable {

    private String name;
    private SpriteGroup sprites;
    private Stat[] stats;
    private TypeContainer[] types;
    private int life;
    private int speed;
    private int defense;
    private int attack;

    public PokemonDTO() {

    }

    public PokemonDTO(String name, SpriteGroup sprites, Stat[] stats, TypeContainer[] types, int life, int speed, int defense, int attack) {
        this.name = name;
        this.sprites = sprites;
        this.stats = stats;
        this.types = types;
        this.life = life;
        this.speed = speed;
        this.defense = defense;
        this.attack = attack;
    }

    public TypeContainer[] getTypes() {
        return types;
    }

    public void setTypes(TypeContainer[] types) {
        this.types = types;
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
