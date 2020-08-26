package example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;


public class MyTextField extends JTextField implements ActionListener {

  private JLabel label;

  public MyTextField(JLabel label) {
    super(10);
    this.label = label;
  }

  public void addListener(ActionListener listener) {
    addActionListener(listener);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    label.setText(getText());
  }
}
