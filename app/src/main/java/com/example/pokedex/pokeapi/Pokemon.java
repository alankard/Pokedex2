package com.example.pokedex.pokeapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pokemon {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("url")
    @Expose
    private String url;

    private int number;

    public int getNumber() {
        String[] partes = url.split("/");
        String ultCad = partes[partes.length - 1];
        int resultado = Integer.parseInt(ultCad);
        return resultado;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return "It's " + getName() + "!";
    }

}

