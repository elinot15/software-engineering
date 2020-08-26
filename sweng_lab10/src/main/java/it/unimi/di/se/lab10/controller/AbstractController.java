package it.unimi.di.se.lab10.controller;


import java.awt.event.ActionListener;
import javax.swing.event.ChangeListener;

import it.unimi.di.se.lab10.model.Model;
import it.unimi.di.se.lab10.view.TemperatureView;

public abstract class AbstractController implements ActionListener, ChangeListener {

  protected Model model;
  protected TemperatureView view;

  public abstract void updateModel();

}
