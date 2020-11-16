package com.juanesperez.pokedexreto2.model;

import java.io.Serializable;
import java.lang.reflect.Array;

public class Stat implements Serializable {

    private int base_stat;

    public Stat(int base_stat) {
        this.base_stat = base_stat;
    }

    public Stat(){

    }

    public int getBase_stat() {
        return base_stat;
    }

    public void setBase_stat(int base_stat) {
        this.base_stat = base_stat;
    }
}
