package it.unimi.di.se.lab10.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ConcreteModel extends Observable implements Model {

    protected double temperature=-100;

    @Override
    public double getTemp(ScaleStrategy scale) {
        return scale.valueFromCelsius(temperature);
    }

    @Override
    public void setTemp(double temp, ScaleStrategy scale) {
        double celsius = scale.valueToCelsius(temp);
        if(Double.compare(temperature,celsius)!=0) {
            temperature=celsius;
            setChanged();
            notifyObservers();
        }
    }
}
