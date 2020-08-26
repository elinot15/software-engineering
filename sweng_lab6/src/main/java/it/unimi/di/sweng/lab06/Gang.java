package it.unimi.di.sweng.lab06;

import java.util.ArrayList;

public class Gang implements Feline{

    ArrayList<Feline> branco = new ArrayList<Feline>();


    @Override
    public void roar() {
        for (Feline i:branco
             ) {
            i.roar();
        }
    }

    public void add(Feline feline) {
        branco.add(feline);
    }
}
