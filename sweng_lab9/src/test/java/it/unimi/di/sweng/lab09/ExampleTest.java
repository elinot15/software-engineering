package it.unimi.di.sweng.lab09;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.Reader;
import java.io.StringReader;

import static org.assertj.core.api.Assertions.*;

public class ExampleTest {

	@Rule
	public Timeout globalTimeout = Timeout.seconds(2);

	@Test
	public void creaUtentiTest() {
		Reader reader= new StringReader("Mario Roberta Luca Filippo\n" +
				"Roberta Mario Luca Filippo Anna\n" +
				"Luca Mario Roberta Filippo Anna\n" +
				"Filippo Mario Roberta Luca Anna\n" +
				"Anna Roberta Luca Filippo");
		Facebuk facebuk= new Facebuk(reader);
		assertThat(facebuk.crossPrint()).isEqualTo("(Filippo Anna) [Roberta Luca]\n" +
				"(Luca Anna) [Roberta Filippo]\n" +
				"(Luca Filippo) [Mario Roberta Anna]\n" +
				"(Mario Filippo) [Roberta Luca]\n" +
				"(Mario Luca) [Roberta Filippo]\n" +
				"(Mario Roberta) [Luca Filippo]\n" +
				"(Roberta Anna) [Luca Filippo]\n" +
				"(Roberta Filippo) [Mario Luca Anna]\n" +
				"(Roberta Luca) [Mario Filippo Anna]");
	}
}
