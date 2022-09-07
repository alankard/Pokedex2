package com.example.pokedex.pokeapi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeApiService {
        @GET("pokemon/?limit=50")
        Call<PokemonRespuesta> getPokemons();

}
