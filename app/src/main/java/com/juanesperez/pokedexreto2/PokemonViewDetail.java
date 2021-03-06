package com.juanesperez.pokedexreto2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.juanesperez.pokedexreto2.model.Pokemon;
import com.juanesperez.pokedexreto2.model.PokemonDTO;

public class PokemonViewDetail extends AppCompatActivity {

    private TextView pokedefense_tv, pokeattack_tv, pokespeed_tv, pokelife_tv, pokename_tv, poketype_tv;
    private ImageView pokeimage;
    private Button close_btn, liberate_btn;
    private Pokemon pokemon;
    private FirebaseFirestore db;
    private String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_view_detail);
        pokedefense_tv = findViewById(R.id.pokedefense_tv);
        pokeattack_tv = findViewById(R.id.pokeattack_tv);
        pokespeed_tv = findViewById(R.id.pokespeed_tv);
        pokelife_tv = findViewById(R.id.pokelife_tv);
        pokename_tv = findViewById(R.id.pokename_tv);
        poketype_tv = findViewById(R.id.poketype_tv);
        pokeimage = findViewById(R.id.pokeimage);
        close_btn = findViewById(R.id.close_btn);
        liberate_btn = findViewById(R.id.liberate_btn);
        close_btn.setOnClickListener(this::closeActivity);
        liberate_btn.setOnClickListener(this::liberatePoke);
        Intent i = getIntent();
        pokemon = (Pokemon) i.getSerializableExtra("pokemon");
        username = i.getStringExtra("username");
        db = FirebaseFirestore.getInstance();
        setVars();
    }

    private void liberatePoke(View view) {
        db.collection("reto2").document(username).collection("pokemons").document(pokemon.getId()).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(">>>", "DocumentSnapshot successfully deleted!");
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Log.w(">>>", "Error deleting document", e);
                    }
                });

    }

    private void closeActivity(View view) {
        finish();
    }

    public void setVars(){
        runOnUiThread(
                ()->{
                    pokelife_tv.setText(Integer.toString(pokemon.getLife()));
                    pokespeed_tv.setText(Integer.toString(pokemon.getSpeed()));
                    pokedefense_tv.setText(Integer.toString(pokemon.getDefense()));
                    pokeattack_tv.setText(Integer.toString(pokemon.getAttack()));
                    poketype_tv.setText(pokemon.getType());
                    pokename_tv.setText(pokemon.getName());
                    Glide.with(pokeimage).load(pokemon.getSprites().getFront_default()).into(pokeimage);
                }
        );
    }

}