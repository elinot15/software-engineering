package it.unimi.di.se.lab10.model;

public interface ScaleStrategy {
  double valueFromCelsius(double temperature);

  double valueToCelsius(double temperature);
}
