package it.unimi.di.se.lab10.view;

import it.unimi.di.se.lab10.controller.AbstractController;
import it.unimi.di.se.lab10.model.ScaleStrategy;

import java.util.Observer;

public interface TemperatureView {
  void addListener(AbstractController controller);

  double getTempValue();

  ScaleStrategy getScaleStrategy();
}
