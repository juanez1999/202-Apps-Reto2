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

import com.google.firebase.firestore.FirebaseFirestore;
import com.juanesperez.pokedexreto2.lists.Adapter.PokemonAdapter;
import com.juanesperez.pokedexreto2.model.Pokemon;

import java.util.HashMap;

public class Home extends AppCompatActivity {

    private Button catchBtn;
    private EditText pokemonNameET;
    private EditText searchPokemonET;
    private Button searchBtn;
    private RecyclerView pokemonList;
    private PokemonAdapter adapter;
    private FirebaseFirestore db;
    private String username;

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

        pokemonList.setAdapter(adapter);
        pokemonList.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        pokemonList.setLayoutManager(manager);
        catchBtn.setOnClickListener(this::catchPokemon);
        db = FirebaseFirestore.getInstance();
        Intent i = getIntent();
        username = i.getStringExtra("myUser");
        Log.e(">>>",username);
    }

    private void catchPokemon(View v) {
        String pokeName = pokemonNameET.getText().toString();
        HashMap<String,String> poke = new HashMap<>();
        poke.put("pokeName",pokeName);
        db.collection("reto2").document(username).collection("pokemons").add(poke);
        Pokemon pokesito = new Pokemon(pokeName,"normal","10","10","10","10");
        adapter.addPokemon(pokesito);
    }




}