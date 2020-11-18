package com.juanesperez.pokedexreto2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.juanesperez.pokedexreto2.comm.HTTPSWebUtilDomi;
import com.juanesperez.pokedexreto2.comm.PokemonWorker;
import com.juanesperez.pokedexreto2.lists.Adapter.PokemonAdapter;
import com.juanesperez.pokedexreto2.model.Pokemon;
import com.juanesperez.pokedexreto2.model.PokemonDTO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    private  ListenerRegistration listener;

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

    public void sendPokemon(PokemonDTO pokemonDTO){
        Date now = new Date();
        long timestamp = now.getTime();
        Pokemon pokemon = new Pokemon(UUID.randomUUID().toString(),
                pokemonDTO.getName(),
                pokemonDTO.getSprites(),
                pokemonDTO.getTypes()[0].getType().getName(),
                timestamp,
                pokemonDTO.getStats()[0].getBase_stat(),
                pokemonDTO.getStats()[1].getBase_stat(),
                pokemonDTO.getStats()[2].getBase_stat(),
                pokemonDTO.getStats()[5].getBase_stat());

        db.collection("reto2").document(username).collection("pokemons").document(pokemon.getId()).set(pokemon);
    }

    public void getPokemon(){
         listener = db.collection("reto2").document(username).collection("pokemons").addSnapshotListener(
                (value, error) -> {
                    adapter.clear();
                    for (DocumentSnapshot document : value.getDocuments()) {
                        Log.e(">>>", document.getId() + " => " + document.getData());
                        Pokemon pokemon = document.toObject(Pokemon.class);
                        adapter.addPokemon(pokemon);
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

    @Override
    protected void onDestroy() {
        listener.remove();
        super.onDestroy();
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