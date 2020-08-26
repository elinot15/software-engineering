package it.unimi.di.sweng.lab09;

import java.util.ArrayList;
import java.util.List;

public class Utente {
    String nome;
    String cognome;
    String username;
    List<String> amici = new ArrayList<String>();

    public Utente(String nome, List amici){
        this.nome=nome;
        this.amici=amici;
        username=nome;
    }

    public Utente(String nome, String cognome, List amici) {
        this.nome = nome;
        this.cognome=cognome;
        this.amici = amici;
        username=nome + cognome;
    }

    public String getUsername(){
        return username;
    }

    public int getNumeroAmici() {
        return amici.size();
    }

    public String getAmico(int i){
        return amici.get(i);
    }

}
