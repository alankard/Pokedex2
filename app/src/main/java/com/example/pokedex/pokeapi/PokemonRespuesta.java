package com.example.pokedex.pokeapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PokemonRespuesta {
        @SerializedName("results")
        @Expose
        private ArrayList<Pokemon> results;

        public ArrayList getResults() {
            return results;
        }

        public void setResults(ArrayList<Pokemon> results) {
            this.results = results;
        }


}
