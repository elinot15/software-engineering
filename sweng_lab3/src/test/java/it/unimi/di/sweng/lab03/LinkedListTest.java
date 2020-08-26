package it.unimi.di.sweng.lab03;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class LinkedListTest {

	@Rule
    public Timeout globalTimeout = Timeout.seconds(2);

	private IntegerList list = new IntegerList();
	private IntegerList list2 = new IntegerList();
	private IntegerList list3 = new IntegerList();


	@Test
	public void costruttoreVuoto() {

		assertThat(list.toString()).isEqualTo("[]");
	}

	@Test
	public void tailInsert(){

		list.addLast(1);
		assertThat(list.toString()).isEqualTo("[1]");

		list.addLast(3);
		assertThat(list.toString()).isEqualTo("[1 3]");
	}

	@Test
	public void costruttoreStringa(){
		list.newFromString("");
		assertThat(list.toString()).isEqualTo("[]");

		list2.newFromString("1");
		assertThat(list2.toString()).isEqualTo("[1]");

		list3.newFromString("3");
		assertThat(list3.toString()).isEqualTo("[1 2 3]");
	}

	@Test
	public void robustezzaCostruttore(){
		try{
			list.newFromString("1 2 aaa");
			failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
		}catch(IllegalArgumentException e){
			assertThat(e).hasMessage("Not supported input format");
		}
	}


	@Test
	public void aggiungiTesta(){
		list.addFirst(1);
		assertThat(list.toString()).isEqualTo("[1]");
		list.addFirst(3);
		assertThat(list.toString()).isEqualTo("[3 1]");
	}

	@Test
	public void rimozioneTesta(){
		list.addFirst(2);
		list.addFirst(1);
		assertThat(list.removeFirst()).isEqualTo(true);
		assertThat(list.toString()).isEqualTo("[2]");
		assertThat(list.removeFirst()).isEqualTo(true);
		assertThat(list.toString()).isEqualTo("[]");
		assertThat(list.removeFirst()).isEqualTo(false);
	}

	@Test
	public void rimozioneCoda(){
		list.addFirst(10);
		list.addFirst(7);
		assertThat(list.removeLast()).isEqualTo(true);
		assertThat(list.toString()).isEqualTo("[7]");
		assertThat(list.removeLast()).isEqualTo(true);
		assertThat(list.toString()).isEqualTo("[]");
		assertThat(list.removeLast()).isEqualTo(false);
	}

	@Test
	public void rimozioneOccorrenza(){
		list.addFirst(5);
		list.addFirst(3);
		list.addFirst(4);
		list.addFirst(3);
		list.addFirst(2);
		list.addFirst(1);
		assertThat(list.remove(2)).isEqualTo(true);
		assertThat(list.toString()).isEqualTo("[1 3 4 3 5]");
		assertThat(list.remove(3)).isEqualTo(true);
		assertThat(list.toString()).isEqualTo("[1 4 3 5]");
		assertThat(list.remove(6)).isEqualTo(false);
		assertThat(list.remove(5)).isEqualTo(true);
		assertThat(list.toString()).isEqualTo("[1 4 3]");
		assertThat(list.remove(1)).isEqualTo(true);
		assertThat(list.toString()).isEqualTo("[4 3]");
	}

	@Test
	public void rimozioneTutteOccorrenze(){
		list.addFirst(5);
		list.addFirst(3);
		list.addFirst(4);
		list.addFirst(3);
		list.addFirst(2);
		list.addFirst(1);
		assertThat(list.removeAll(3)).isEqualTo(true);
		assertThat(list.toString()).isEqualTo("[1 2 4 5]");
		assertThat(list.removeAll(6)).isEqualTo(false);
		assertThat(list.removeAll(5)).isEqualTo(true);
		assertThat(list.toString()).isEqualTo("[1 2 4]");
		assertThat(list.removeAll(1)).isEqualTo(true);
		assertThat(list.toString()).isEqualTo("[2 4]");
	}

	@Test
	public void valoreMedio(){
		list.addFirst(1);
		list.addFirst(2);
		assertThat(list.mean()).isEqualTo(1.5);
		list2.addFirst(160);
		list2.addFirst(591);
		list2.addFirst(114);
		list2.addFirst(229);
		list2.addFirst(230);
		list2.addFirst(270);
		list2.addFirst(128);
		list2.addFirst(1657);
		list2.addFirst(624);
		list2.addFirst(1503);
		assertThat(list2.mean()).isEqualTo(550.6);
		assertThat(list3.mean()).isEqualTo(0);
	}

	@Test
	public void deviazioneStandard(){
		assertThat(list.stdDev()).isEqualTo(0);
		list.addFirst(1);
		assertThat(list.stdDev()).isEqualTo(0);
		list2.addFirst(160);
		list2.addFirst(591);
		list2.addFirst(114);
		list2.addFirst(229);
		list2.addFirst(230);
		list2.addFirst(270);
		list2.addFirst(128);
		list2.addFirst(1657);
		list2.addFirst(624);
		list2.addFirst(1503);
		assertThat(list2.stdDev()).isEqualTo(572.026);
	}

	@Test
	public void scansione(){
		list.addFirst(3);
		list.addFirst(2);
		list.addFirst(1);
		assertThat(list.next()).isEqualTo(1);
		assertThat(list.next()).isEqualTo(2);
		assertThat(list.next()).isEqualTo(3);
		assertThat(list.next()).isEqualTo(3);
		assertThat(list.prev()).isEqualTo(2);
		assertThat(list.prev()).isEqualTo(1);
		assertThat(list.prev()).isEqualTo(1);
	}

	@Test
	public void inputFile() throws java.io.IOException{
		list.addFromFile();
		assertThat(list.toString()).isEqualTo("[7 10 8 6 7 4 10]");
	}
}
