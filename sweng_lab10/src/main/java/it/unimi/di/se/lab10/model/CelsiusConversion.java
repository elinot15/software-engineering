package it.unimi.di.se.lab10.model;

public class CelsiusConversion implements ScaleStrategy {
    @Override
    public double valueFromCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double valueToCelsius(double temperature) {
        return temperature;
    }
}
