package com.juanesperez.pokedexreto2.comm;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.juanesperez.pokedexreto2.Home;
import com.juanesperez.pokedexreto2.model.PokemonDTO;

public class PokemonWorker extends Thread{

    private Home ref;

    public PokemonWorker(Home ref) {
        this.ref = ref;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void run(){
        HTTPSWebUtilDomi https = new HTTPSWebUtilDomi();
        Gson gson = new Gson();
            String json = https.GETrequest("https://pokeapi.co/api/v2/pokemon/"+ref.getPokeName());
            Log.e(">>>",json);
            PokemonDTO pokemonDTO = gson.fromJson(json, PokemonDTO.class);
            ref.sendPokemon(pokemonDTO);
    }
}
