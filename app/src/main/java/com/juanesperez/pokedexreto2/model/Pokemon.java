package com.juanesperez.pokedexreto2.model;

import java.io.Serializable;

public class Pokemon implements Serializable {

    private String id;
    private String name;
    private SpriteGroup sprites;
    private String type;
    private long date;
    private int life;
    private int speed;
    private int defense;
    private int attack;

    public Pokemon(){

    }

    public Pokemon(String id, String name, SpriteGroup sprites, String type, long date, int life, int attack, int defense, int speed) {
        this.id = id;
        this.name = name;
        this.sprites = sprites;
        this.type = type;
        this.date = date;
        this.life = life;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
