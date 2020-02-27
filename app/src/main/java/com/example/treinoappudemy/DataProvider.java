package com.example.treinoappudemy;

public class DataProvider {
    String letra;
    String treino;
    int image;

    public DataProvider(String letra, String treino, int image) {
        this.letra = letra;
        this.treino = treino;
        this.image = image;
    }

    public String getLetra() {
        return letra;
    }

    public String getTreino() {
        return treino;
    }

    public int getImage() {
        return image;
    }
}
