package example;

import static org.assertj.core.api.Assertions.*;

import java.awt.Dimension;

import static java.awt.event.KeyEvent.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.*;
import org.junit.*;


public class IntegrationTestExample {

  private FrameFixture window;

  @BeforeClass
  public static void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }

  @Before
  public void setUp() {
    // creazione finestra principale
    JFrame frame = GuiActionRunner.execute(() -> new JFrame());
    JPanel panel = GuiActionRunner.execute(() -> new JPanel());

    // creazione oggetti dell'interfaccia
    JLabel label = GuiActionRunner.execute(() -> new JLabel("        "));
    MyTextField text = GuiActionRunner.execute(() -> new MyTextField(label));

    // nell'MVC il componente listener sarà il Controller
    text.addListener(text);

    // aggiunta oggetti grafici alla finestra principale
    panel.add(label);
    panel.add(text);

    frame.add(panel);
    frame.pack();
    frame.setPreferredSize(new Dimension(300, 80));

    window = new FrameFixture(frame);
    window.show();
  }

  @Test
  public void test() {

    JTextComponentFixture textBox = window.textBox();
    JLabelFixture label = window.label();

    textBox.deleteText().enterText("pippo");
    textBox.pressAndReleaseKeys(VK_ENTER);

    assertThat(label.text()).isEqualTo("pippo");

    textBox.deleteText().enterText("pluto");

    // il listener non viene chiamato fino alla pressione di ENTER (quindi è ancora pippo)
    assertThat(label.text()).isNotEqualTo("pluto");
    assertThat(label.text()).isEqualTo("pippo");

    textBox.pressAndReleaseKeys(VK_ENTER);

    // dopo la pressione di ENTER diventa pluto
    assertThat(label.text()).isEqualTo("pluto");
  }

  @After
  public void tearDown() {
    window.cleanUp();
  }
}
