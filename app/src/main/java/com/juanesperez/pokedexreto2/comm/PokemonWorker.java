package com.juanesperez.pokedexreto2.comm;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.juanesperez.pokedexreto2.Home;
import com.juanesperez.pokedexreto2.model.Pokemon;

import java.lang.reflect.Type;
import java.util.HashMap;

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
        /*Type type = new TypeToken<HashMap<String, Pokemon>>(){}.getType();
        HashMap<String, Pokemon> pokemons = gson.fromJson(json,type);*/
        Pokemon pokemon = gson.fromJson(json,Pokemon.class);

        Log.e(">>>",pokemon.getSprites().getFront_default());
        ref.sendPokemon(pokemon);
    }
}
