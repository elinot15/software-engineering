package it.unimi.di.sweng.lab02;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class BowlingTest {

	@Rule
    public Timeout globalTimeout = Timeout.seconds(2);

	private Bowling game;

	@Before
	public void setUp(){
		game = new BowlingGame();
	}
	
	@Test
	public void gutterGame() {
		rollMany(20, 0);
		assertThat(game.score()).isEqualTo(0);
	}

	private void rollMany(int tiri, int punteggio) {
		for (int i = 0; i < tiri; i++)
			game.roll(punteggio);
	}

	@Test
	public void allOnesGame() {
		rollMany(20, 1);
		assertThat(game.score()).isEqualTo(20);
	}
	
	@Test
	public void oneSpareGame() {
		game.roll(5);
		game.roll(5);
		game.roll(3);
		rollMany(17, 0);
		assertThat(game.score()).isEqualTo(16);
	}
	
    @Test
	public void notSpareGame() {
		 // Il test deve simulare una partita in cui vengono colpiti 5 birilli due volte di seguito,
		 // ma non all'interno del medesimo frame.
		 // Questa condizione non deve essere riconosciuta come spare.
		 // Es., roll(1), roll(5), roll(5), ecc., non è spare.
		game.roll(1);
		game.roll(5);
		game.roll(5);
		game.roll(2);
		rollMany(16,0);
		assertThat(game.score()).isEqualTo(13);
	}

	@Test
	public void oneStrikeGame() {
		 // Il test deve simulare una partita in cui avviene uno strike.
		 // Es., lo score di: roll(10), roll(3), roll(4), roll(0), ..., roll(0), è 24.
		game.roll(10);
		game.roll(3);
		game.roll(4);
		rollMany(17,0);
		assertThat(game.score()).isEqualTo(24);

	}
	
	@Test
	public void notStrikeGame() {
		 // Il test deve simulare una partita in cui vengono colpiti 10 birilli con il secondo tiro di un frame,
		 // Questa condizione deve essere riconosciuta come spare e non come strike.
		 // Es., lo score di: roll(0), roll(10), roll(3), roll(0), ..., roll(0), è 16.
		game.roll(0);
		game.roll(10);
		game.roll(3);
		rollMany(17,0);
		assertThat(game.score()).isEqualTo(16);
	}
	
	@Test
	public void lastFrameStrikeGame() {
		 // Il test deve simulare una partita in cui avviene uno strike nell'ultimo frame.
		 // In questo caso il giocatore completa il frame ed ha diritto ad un tiro aggiuntivo.
		 // Es., lo score di: roll(0), ..., roll(0), roll(10), roll(3), roll(2), è 15.
		rollMany(18,0);
		game.roll(10);
		game.roll(3);
		game.roll(2);
		assertThat(game.score()).isEqualTo(15);
	}
	
	@Test
	public void perfectGame() { 
		 // Il test deve simulare una partita perfetta in cui avvengono 12 strike di seguito.
		 // Es., lo score di: roll(10), ..., roll(10), è 300.
		rollMany(12, 10);
		assertThat(game.score()).isEqualTo(300);
	}
	

	
}
