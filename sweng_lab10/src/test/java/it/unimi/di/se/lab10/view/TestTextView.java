package it.unimi.di.se.lab10.view;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import it.unimi.di.se.lab10.model.Model;
import it.unimi.di.se.lab10.model.ScaleStrategy;
import org.junit.Test;

import java.util.Observable;

public class TestTextView {

  @Test
  public void getScaleTest() {

    ScaleStrategy scale = mock(ScaleStrategy.class);
    TextView ctView = new TextView(scale);

    assertThat(ctView.getScaleStrategy()).isEqualTo(scale);
  }

  @Test
  public void getTempTest() {

    ScaleStrategy scale = mock(ScaleStrategy.class);
    TemperatureView ctView = new TextView(scale);

    assertThat(ctView.getTempValue()).isEqualTo(0.0, within(0.1));

  }

  @Test
  public void updateTest() {

    ScaleStrategy scale = mock(ScaleStrategy.class);
    abstract class MockObservableModel extends Observable implements Model{};
    MockObservableModel model = mock(MockObservableModel.class);
    TextView ctView = new TextView(scale);

    when(model.getTemp(scale)).thenReturn(123.45);

    ctView.update(model, null);

    verify(model).getTemp(scale);
    assertThat(ctView.getTempValue()).isEqualTo(123.45, within(0.01));
  }
}
