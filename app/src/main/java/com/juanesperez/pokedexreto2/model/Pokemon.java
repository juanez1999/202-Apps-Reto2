package com.juanesperez.pokedexreto2.model;

import java.io.Serializable;

public class Pokemon implements Serializable {

    private String name;
    private String type;
    private String defense;
    private String attack;
    private String speed;
    private String life;

    public Pokemon() {

    }

    public Pokemon(String name, String type, String defense, String attack, String speed, String life) {
        this.name = name;
        this.type = type;
        this.defense = defense;
        this.attack = attack;
        this.speed = speed;
        this.life = life;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefense() {
        return defense;
    }

    public void setDefense(String defense) {
        this.defense = defense;
    }

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }
}
