package com.example.pokedex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokedex.pokeapi.Pokemon;

import java.util.ArrayList;

public class ListaPokemonAdapter extends RecyclerView.Adapter<ListaPokemonAdapter.viewHolder> {

    ArrayList<Pokemon> dataset;
    Context context;

    public ListaPokemonAdapter(Context context){
        dataset = new ArrayList<>();
        this.context= context;
    }

    @NonNull
    @Override
    public ListaPokemonAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaPokemonAdapter.viewHolder holder, int position) {
        Pokemon p = dataset.get(position);
        holder.nombrePokemon.setText(p.getName());

        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + p.getNumber() + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoPokemon);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void agregarPokemones(ArrayList<Pokemon> pokemones) {
        dataset.addAll(pokemones);
        notifyDataSetChanged();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView fotoPokemon;
        TextView nombrePokemon;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            fotoPokemon = (ImageView) itemView.findViewById(R.id.fotoPokemon);
            nombrePokemon= (TextView) itemView.findViewById(R.id.nombrePokemon);
        }
    }
}
