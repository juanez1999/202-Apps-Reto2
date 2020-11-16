package com.juanesperez.pokedexreto2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.juanesperez.pokedexreto2.comm.HTTPSWebUtilDomi;
import com.juanesperez.pokedexreto2.comm.PokemonWorker;
import com.juanesperez.pokedexreto2.lists.Adapter.PokemonAdapter;
import com.juanesperez.pokedexreto2.model.Pokemon;
import com.juanesperez.pokedexreto2.model.Stat;

import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Home extends AppCompatActivity implements PokemonAdapter.OnPokemonClickListener{

    private Button catchBtn;
    private EditText pokemonNameET;
    private EditText searchPokemonET;
    private Button searchBtn;
    private RecyclerView pokemonList;
    private PokemonAdapter adapter;
    private FirebaseFirestore db;
    private String username;
    private String pokeName;
    private PokemonWorker worker;
    private HTTPSWebUtilDomi https;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        catchBtn = findViewById(R.id.btn_catch);
        pokemonNameET = findViewById(R.id.et_pokemonName);
        searchPokemonET = findViewById(R.id.et_search);
        searchBtn = findViewById(R.id.btn_search);
        pokemonList = findViewById(R.id.pokemonList);

        adapter = new PokemonAdapter();
        adapter.setListener(this);
        https = new HTTPSWebUtilDomi();

        pokemonList.setAdapter(adapter);
        pokemonList.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        pokemonList.setLayoutManager(manager);
        catchBtn.setOnClickListener(this::catchPokemon);
        db = FirebaseFirestore.getInstance();
        Intent i = getIntent();
        username = i.getStringExtra("myUser");
        Log.e(">>>",username);
        getPokemon();
    }

    private void catchPokemon(View v) {
        pokeName = pokemonNameET.getText().toString();
        worker = new PokemonWorker(this);
        worker.start();
    }

    public void sendPokemon(Pokemon pokemon){
        Map<String, Object> poke = new HashMap<>();
        poke.put("name", pokemon.getName());
        poke.put("sprites", pokemon.getSprites());
        poke.put("life",pokemon.getStats()[0].getBase_stat());
        poke.put("attack",pokemon.getStats()[1].getBase_stat());
        poke.put("defense",pokemon.getStats()[2].getBase_stat());
        poke.put("speed",pokemon.getStats()[5].getBase_stat());

        db.collection("reto2").document(username).collection("pokemons").add(poke);
        runOnUiThread(
                ()->{
                    getPokemon();
                }
        );
    }

    public void getPokemon(){
        adapter.clear();
        db.collection("reto2").document(username).collection("pokemons").get().addOnCompleteListener(
                task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.e(">>>", document.getId() + " => " + document.getData());
                            Pokemon pokemon = document.toObject(Pokemon.class);
                            adapter.addPokemon(pokemon);
                        }
                    }
                }
        );
        /*Thread thread = new Thread(
                ()->{
                    String json = https.GETrequest("https://firestore.googleapis.com/v1/projects/semana12firebase-af4d3/databases/(default)/documents/reto2/"+username+"/pokemons");
                    Log.e(">>>", json);
                    Pokemon pokemon = gson.fromJson(json,Pokemon.class);
                    runOnUiThread(
                            ()->{
                                adapter.addPokemon(pokemon);
                            }
                    );
                }
        );
        thread.start();*/
    }

    public String getPokeName() {
        return pokeName;
    }

    public void setPokeName(String pokeName) {
        this.pokeName = pokeName;
    }

    @Override
    public void onUserClick(Pokemon pokemon) {
        Intent j= new Intent(this,PokemonViewDetail.class);
        j.putExtra("pokemon", pokemon);
        startActivity(j);
    }

   /* @Override
        public void onFreePokemon(Pokemon pokemon) {
        adapter.deletePokemon(pokemon);
    }*/
}