package it.unimi.di.sweng.lab04;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


public class StringCalculatorTest {

  @Rule
  public Timeout globalTimeout = Timeout.seconds(2);

  private Calculator calculator;

  @Before
  public void setUp() throws Exception {
    calculator = new StringCalculator();
  }

  @Test
  public void testSommaInteri() {
    assertThat(calculator.add("")).isEqualTo(0);
    assertThat(calculator.add("1")).isEqualTo(1);
    assertThat(calculator.add("5,7")).isEqualTo(5 + 7);
  }

  @Test
  public void testSommaTantiInteri() {
    assertThat(calculator.add("1,2,3,4")).isEqualTo(1+2+3+4);
    assertThat(calculator.add("2,4,7")).isEqualTo(2+4+7);
    assertThat(calculator.add("15,13,4,8,1")).isEqualTo(15+13+4+8+1);
  }

  @Test
  public void testSeparatoriDifferenti(){
    assertThat(calculator.add("1\n2,3")).isEqualTo(1+2+3);
    assertThat(calculator.add("1,2\n3\n4")).isEqualTo(1+2+3+4);
  }

  @Test
  public void testSceltaSeparatore(){
    assertThat(calculator.add("//!\n1!2!3")).isEqualTo(1+2+3);
    assertThat(calculator.add("//;\n1;2;4")).isEqualTo(1+2+4);
    assertThat(calculator.add("//a\n1a2a4")).isEqualTo(1+2+4);
    assertThat(calculator.add("//£\n1£2£4")).isEqualTo(1+2+4);
  }

  @Test
  public void testNumeriNegativi() throws IllegalArgumentException{
    try{
      calculator.add("-1,2,-3");
      failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
    }catch(IllegalArgumentException e) {
      assertThat(e).hasMessage("Numeri negativi non ammessi: -1, -3");
    }
    try{
      calculator.add("-1,-2,-3");
      failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
    }catch(IllegalArgumentException e) {
      assertThat(e).hasMessage("Numeri negativi non ammessi: -1, -2, -3");
    }
  }

  @Test
  public void testMaxInt() {
    assertThat(calculator.add("1001,2")).isEqualTo(2);
    assertThat(calculator.add("1998,1965")).isEqualTo(0);
    assertThat(calculator.add("6,3,1940")).isEqualTo(6+3);

  }

  @Test
  public void testSepMulticarattere(){
    assertThat(calculator.add("//[!!!]\n1!!!2!!!3")).isEqualTo(1+2+3);
    assertThat(calculator.add("//[abc]\n1abc2abc3")).isEqualTo(1+2+3);
  }

 @Test
  public void testMoltiSeparatori(){
    assertThat(calculator.add("//[!][%]\n1%2!3")).isEqualTo(1+2+3);
    assertThat(calculator.add("//[=][@][#]\n5@6#7=2")).isEqualTo(5+7+6+2);
    assertThat(calculator.add("//[e][a][v][p]\n8a3e9p5v1")).isEqualTo(8+3+9+5+1);
  }
}