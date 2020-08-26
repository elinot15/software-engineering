package it.unimi.di.se.lab10.model;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import it.unimi.di.se.lab10.view.TemperatureView;
import org.junit.Test;

import java.util.Observer;


public class TestModel {

  abstract class MockTemperatureView implements TemperatureView, Observer {};



  @Test
  public void testModel(){
    ConcreteModel model=new ConcreteModel();
    ScaleStrategy scale = mock(ScaleStrategy.class);
    MockTemperatureView view=mock(MockTemperatureView.class);
    double temp = 15;

    assertThat(model.temperature).isEqualTo((double)-100);

    model.addObserver(view);

    when(scale.valueToCelsius(temp)).thenReturn((double) temp);
    model.setTemp(temp, scale);

    assertThat(model.temperature).isEqualTo((double) temp);

    verify(scale).valueToCelsius(temp);

    verify(view).update(model,null);

  }


  @Test
  public void testStrategy() {
    ConcreteModel model = new ConcreteModel();
    ScaleStrategy scale = new CelsiusConversion();
    MockTemperatureView view = mock(MockTemperatureView.class);
    double temp = 15;

    assertThat(model.temperature).isEqualTo((double) -100);

    model.setTemp(temp, scale);

    assertThat(model.temperature).isEqualTo((double) temp);

    scale=new FahreniheitConversion();
    model.setTemp(temp, scale);

    assertThat(model.getTemp(scale)).isCloseTo(temp, within(0.1));
    assertThat(model.temperature).isCloseTo(-9.444,within(0.1));

    assertThat(scale.valueToCelsius(100)).isCloseTo(37.778, within(0.1));
  }

}
