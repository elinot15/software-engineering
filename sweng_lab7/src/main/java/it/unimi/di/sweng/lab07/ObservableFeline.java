package it.unimi.di.sweng.lab07;

import it.unimi.di.sweng.lecture.Feline;

import java.util.Observable;

public class ObservableFeline extends Observable implements Feline {
    private Feline myFeline = null;

    public ObservableFeline(Feline feline) {
        myFeline = feline;
    }

    public String getFelineClass(){
        return myFeline.getClass().getSimpleName();
    }

    @Override
    public void roar() {
        myFeline.roar();
        setChanged();
        notifyObservers();
        setChanged();
        notifyObservers(getFelineClass());
    }

}