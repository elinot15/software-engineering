package example;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    JLabel label = new JLabel("         ");
    MyTextField text = new MyTextField(label);

    panel.add(label);
    panel.add(text);

    text.addListener(text);

    frame.add(panel);
    frame.pack();
    frame.setVisible(true);

  }

}
