package it.unimi.di.sweng.lab08;

import org.assertj.core.api.FactoryBasedNavigableIterableAssert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;


public class ExampleTest {

	@Rule
	public Timeout globalTimeout = Timeout.seconds(2);


	@Before
	public  void setup() {
		Rotator.resetCounter();
	}

	@Test
	public void letturaInputTest() {

		Reader reader= new StringReader(
				"Maramao perche sei morto\n" +
				"Pan e vin non ti mancava");
		KWiCindex index= new KWiCindex(reader);
		index.setOutput(new OutputNormale());
		index.setSortAlgorithm(new sortMaiuscolo());
		assertThat(index.getSortedList()).isEqualTo("0: Maramao perche sei morto\n" +
				"1: Pan e vin non ti mancava\n" +
				"1: e vin non ti mancava   Pan\n" +
				"1: mancava   Pan e vin non ti\n" +
				"0: morto   Maramao perche sei\n" +
				"1: non ti mancava   Pan e vin\n" +
				"0: perche sei morto   Maramao\n" +
				"0: sei morto   Maramao perche\n" +
				"1: ti mancava   Pan e vin non\n" +
				"1: vin non ti mancava   Pan e");
	}

	@Test
	public void ordineMaiuscoleTest() {
		Reader reader = new StringReader(
				"Maramao perche Sei morto\n" +
						"Pan e Vin non ti Mancava");
		KWiCindex index = new KWiCindex(reader);
		index.setSortAlgorithm(new sortMaiuscolo());
		index.setOutput(new OutputNormale());
		assertThat(index.getSortedList()).isEqualTo("1: Mancava   Pan e Vin non ti\n" +
				"0: Maramao perche Sei morto\n" +
				"1: Pan e Vin non ti Mancava\n" +
				"0: Sei morto   Maramao perche\n" +
				"1: Vin non ti Mancava   Pan e\n" +
				"1: e Vin non ti Mancava   Pan\n" +
				"0: morto   Maramao perche Sei\n" +
				"1: non ti Mancava   Pan e Vin\n" +
				"0: perche Sei morto   Maramao\n" +
				"1: ti Mancava   Pan e Vin non"
		);
	}

	@Test
	public void ordineMinuscoloTest() {
		Reader reader1 = new StringReader(
				"Maramao perche Sei morto\n" +
						"Pan e Vin non ti Mancava");
		KWiCindex index1 = new KWiCindex(reader1);
		index1.setOutput(new OutputNormale());
		index1.setSortAlgorithm(new sortMinuscolo());
		assertThat(index1.getSortedList()).isEqualTo("1: e Vin non ti Mancava   Pan\n" +
				"0: morto   Maramao perche Sei\n" +
				"1: non ti Mancava   Pan e Vin\n" +
				"0: perche Sei morto   Maramao\n" +
				"1: ti Mancava   Pan e Vin non\n" +
				"1: Mancava   Pan e Vin non ti\n" +
				"0: Maramao perche Sei morto\n" +
				"1: Pan e Vin non ti Mancava\n" +
				"0: Sei morto   Maramao perche\n" +
				"1: Vin non ti Mancava   Pan e"
		);
	}

		@Test
		public void ordineIgnoreCaseTest() {
			Reader reader1 = new StringReader(
					"Maramao perche Sei morto\n" +
							"Pan e Vin non ti Mancava");
			KWiCindex index2 = new KWiCindex(reader1);
			index2.setOutput(new OutputNormale());
			index2.setSortAlgorithm(new sortIgnoreCase());
			assertThat(index2.getSortedList()).isEqualTo("1: e Vin non ti Mancava   Pan\n" +
					"1: Mancava   Pan e Vin non ti\n" +
					"0: Maramao perche Sei morto\n" +
					"0: morto   Maramao perche Sei\n" +
					"1: non ti Mancava   Pan e Vin\n" +
					"1: Pan e Vin non ti Mancava\n" +
					"0: perche Sei morto   Maramao\n" +
					"0: Sei morto   Maramao perche\n" +
					"1: ti Mancava   Pan e Vin non\n" +
					"1: Vin non ti Mancava   Pan e"
			);
		}

	  	@Test
	    public void controlloPunteggiatura() {
			Reader reader = new StringReader(
					"Maramao, perche Sei; morto\n" +
							"Pan e Vin: non ti Mancava!");
			KWiCindex index = new KWiCindex(reader, true);
			index.setOutput(new OutputNormale());
			index.setSortAlgorithm(new sortMaiuscolo());
			assertThat(index.getSortedList()).isEqualTo("1: Mancava   Pan e Vin non ti\n" +
					"0: Maramao perche Sei morto\n" +
					"1: Pan e Vin non ti Mancava\n" +
					"0: Sei morto   Maramao perche\n" +
					"1: Vin non ti Mancava   Pan e\n" +
					"1: e Vin non ti Mancava   Pan\n" +
					"0: morto   Maramao perche Sei\n" +
					"1: non ti Mancava   Pan e Vin\n" +
					"0: perche Sei morto   Maramao\n" +
					"1: ti Mancava   Pan e Vin non"
			);
		}

	@Test
	public void stopWordTest(){
		Reader reader = new StringReader(
				"Maramao perche Sei morto\n" +
						"Pan e Vin non ti Mancava");
		String array[]= {"e", "ti", "non"};
		KWiCindex index = new KWiCindex(reader, array);
		index.setOutput(new OutputNormale());
		index.setSortAlgorithm(new sortMaiuscolo());
		assertThat(index.getSortedList()).isEqualTo("1: Mancava   Pan Vin\n" +
				"0: Maramao perche Sei morto\n" +
				"1: Pan Vin Mancava\n" +
				"0: Sei morto   Maramao perche\n" +
				"1: Vin Mancava   Pan\n" +
				"0: morto   Maramao perche Sei\n" +
				"0: perche Sei morto   Maramao"
		);
	}

	@Test
	public void outputTest(){
		Reader reader = new StringReader(
				"Maramao perche sei morto\n" +
						"Pan e vin non ti mancava");
		KWiCindex index = new KWiCindex(reader);
		index.setOutput(new OutputSpezzato());
		index.setSortAlgorithm(new sortMaiuscolo());
		assertThat(index.getSortedList()).isEqualTo(
				"0:                       Maramao perche sei morto\n"+
				"1:                       Pan e vin non ti mancava\n"+
				"1:                Pan    e vin non ti mancava\n"+
				"1:   Pan e vin non ti    mancava\n"+
				"0: Maramao perche sei    morto\n"+
				"1:          Pan e vin    non ti mancava\n"+
				"0:            Maramao    perche sei morto\n"+
				"0:     Maramao perche    sei morto\n"+
				"1:      Pan e vin non    ti mancava\n"+
				"1:              Pan e    vin non ti mancava"
		);
	}

	}
