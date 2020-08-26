package it.unimi.di.se.lab10.model;

import it.unimi.di.se.lab10.view.TemperatureView;

import java.util.Observable;

public interface Model {
  double getTemp(ScaleStrategy scale);

  void setTemp(double temp, ScaleStrategy scale);


}
