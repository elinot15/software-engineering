package it.unimi.di.sweng.lab07;

import it.unimi.di.sweng.lecture.*;

import java.util.ArrayList;
import java.util.Observer;

public class ObservedFelineFactory extends AbstractFelineFactory {
    private ArrayList<Observer> theObserver = new ArrayList<Observer>();

    public ObservedFelineFactory(ArrayList<Observer> theObserver) {
        this.theObserver = theObserver;
    }

    @Override
    public Feline createLion() {
        ObservableFeline theLion = new ObservableFeline(new Lion());
        for(Observer o: theObserver)
            theLion.addObserver(o);

        return theLion;
    }

    @Override
    public Feline createTiger() {
        ObservableFeline theTiger = new ObservableFeline(new Tiger());
        for(Observer o: theObserver)
            theTiger.addObserver(o);

        return theTiger;
    }

    @Override
    public Feline createDomesticCat() {
        ObservableFeline theDomesticCat = new ObservableFeline(new FelineDomesticCat());
        for(Observer o: theObserver)
            theDomesticCat.addObserver(o);

        return theDomesticCat;
    }

    @Override
    public Feline createPelucheCat() {
        ObservableFeline thePelucheCat = new ObservableFeline(new FelinePelucheCat(new PelucheCat()));
        for(Observer o: theObserver)
            thePelucheCat.addObserver(o);

        return thePelucheCat;
    }
}
