package com.juanesperez.pokedexreto2.lists.ViewModel;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juanesperez.pokedexreto2.R;

public class PokemonViewModel extends RecyclerView.ViewHolder {

    private Button actionRow;
    private ImageView imageRow;
    private TextView nameRow;

    public PokemonViewModel(@NonNull View itemView) {
        super(itemView);
        actionRow = itemView.findViewById(R.id.actionRow);
        imageRow = itemView.findViewById(R.id.imageRow);
        nameRow = itemView.findViewById(R.id.nameRow);
    }

    public Button getActionRow() {
        return actionRow;
    }

    public void setActionRow(Button actionRow) {
        this.actionRow = actionRow;
    }

    public ImageView getImageRow() {
        return imageRow;
    }

    public void setImageRow(ImageView imageRow) {
        this.imageRow = imageRow;
    }

    public TextView getNameRow() {
        return nameRow;
    }

    public void setNameRow(TextView nameRow) {
        this.nameRow = nameRow;
    }
}
