package example;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import org.junit.Test;

public class TestExample {

  @Test
  public void test() {
    //faccio finta di non avere una JLabel reale
    JLabel label = mock(JLabel.class);

    MyTextField text = new MyTextField(label);

    text.setText("pippo");
    text.actionPerformed(mock(ActionEvent.class));

    verify(label).setText("pippo");

  }

}
