package it.unimi.di.sweng.lab07;

import java.util.Observable;
import java.util.Observer;

public class FelineLoggerObserver implements Observer{

    @Override
    public void update(Observable o, Object arg) {
        if (arg == null)
            System.err.println(((ObservableFeline) o).getFelineClass());
    }
}
