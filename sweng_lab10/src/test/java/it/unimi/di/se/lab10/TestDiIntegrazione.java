package it.unimi.di.se.lab10;

import it.unimi.di.se.lab10.model.CelsiusConversion;
import it.unimi.di.se.lab10.model.ConcreteModel;
import it.unimi.di.se.lab10.model.Model;
import it.unimi.di.se.lab10.view.TextView;
import it.unimi.di.se.lab10.view.TemperatureView;
import org.junit.Test;

import java.util.Observer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class TestDiIntegrazione {

    @Test
    public void testDiIntegrazione(){

        Model myModel = new ConcreteModel();
        CelsiusConversion strategy = new CelsiusConversion();
        TemperatureView view = new TextView(strategy);
        double temp = 25;

        ((ConcreteModel) myModel).addObserver((Observer) view);

        myModel.setTemp(temp, strategy);

        assertThat(view.getTempValue()).isEqualTo(temp, within(0.1));
        assertThat(myModel.getTemp(strategy)).isEqualTo(temp, within(0.1));
    }
}
