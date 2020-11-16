package com.juanesperez.pokedexreto2.lists.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.juanesperez.pokedexreto2.R;
import com.juanesperez.pokedexreto2.lists.ViewModel.PokemonViewModel;
import com.juanesperez.pokedexreto2.model.Pokemon;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewModel> {

    private ArrayList<Pokemon> pokemons;

    public PokemonAdapter() {
        pokemons = new ArrayList<>();
    }

    public void addPokemon(Pokemon pokemon){
        pokemons.add(pokemon);
        notifyDataSetChanged();
    }

    public void deletePokemon(Pokemon pokemon){
        pokemons.remove(pokemon);
        notifyDataSetChanged();
    }

    public void clear(){
        pokemons.clear();
        notifyDataSetChanged();
    }

    @Override
    public PokemonViewModel onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.pokemonrow,parent,false);
        PokemonViewModel pokemonViewModel = new PokemonViewModel(view);
        return pokemonViewModel;
    }

    @Override
    public void onBindViewHolder(PokemonViewModel holder, int position) {
        holder.getNameRow().setText(pokemons.get(position).getName());
        Glide.with(holder.getImageRow()).load(pokemons.get(position).getSprites().getFront_default()).into(holder.getImageRow());
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }
}
