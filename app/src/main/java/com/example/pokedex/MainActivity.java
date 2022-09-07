package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.pokedex.pokeapi.PokeApiService;
import com.example.pokedex.pokeapi.Pokemon;
import com.example.pokedex.pokeapi.PokemonRespuesta;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit;
    RecyclerView recyclerView;
    ListaPokemonAdapter listaPokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listaPokemonAdapter = new ListaPokemonAdapter(this);
        recyclerView.setAdapter(listaPokemonAdapter);

        GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obtenerDatos();

    }
    private void obtenerDatos() {
        PokeApiService service = retrofit.create(PokeApiService.class);
        Call<PokemonRespuesta> pokemonRespuestaCall = service.getPokemons();

        pokemonRespuestaCall.enqueue(new Callback<PokemonRespuesta>() {


            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
                if (response.isSuccessful()){
                    PokemonRespuesta respuesta = response.body();
                    ArrayList<Pokemon> pokemones = respuesta.getResults();

                    listaPokemonAdapter.agregarPokemones(pokemones);


                } else {
                    Log.e("POKEDEX", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
                Log.e("POKEDEX", t.getMessage());
            }
        });
    }

}