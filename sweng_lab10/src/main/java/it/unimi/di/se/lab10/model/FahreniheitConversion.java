package it.unimi.di.se.lab10.model;

public class FahreniheitConversion implements ScaleStrategy {
    @Override
    public double valueFromCelsius(double temperature) {
        return (temperature*9/5) +32;
    }

    @Override
    public double valueToCelsius(double temperature) {
        return (temperature-32)*5/9;
    }
}
