package com.juanesperez.pokedexreto2.model;

import java.io.Serializable;

public class SpriteGroup implements Serializable {

    private String front_default;

    public SpriteGroup(){

    }

    public SpriteGroup(String front_default) {
        this.front_default = front_default;
    }

    public String getFront_default() {
        return front_default;
    }

    public void setFront_default(String front_default) {
        this.front_default = front_default;
    }
}
