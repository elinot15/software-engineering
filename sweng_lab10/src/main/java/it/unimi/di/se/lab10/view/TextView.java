package it.unimi.di.se.lab10.view;

import it.unimi.di.se.lab10.controller.AbstractController;
import it.unimi.di.se.lab10.model.Model;
import it.unimi.di.se.lab10.model.ScaleStrategy;

import javax.swing.*;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

public class TextView extends JTextArea implements Observer, TemperatureView {

    private final ScaleStrategy scale;

    public TextView(ScaleStrategy strategy){
        this.scale=strategy;
        this.setText("0.00");
    }

    @Override
    public void addListener(AbstractController controller) {

    }

    @Override
    public double getTempValue() {
        return Double.parseDouble(getText());
    }

    @Override
    public ScaleStrategy getScaleStrategy() {
        return scale;
    }

    @Override
    public void update(Observable observable, Object o) {
        if(observable instanceof Model) {
            double value = ((Model) observable).getTemp(scale);
            setText(String.format(Locale.US, "%.2f", value));
        }
    }
}
