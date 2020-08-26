package it.unimi.di.sweng.lab06;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.rules.Timeout;
import static org.assertj.core.api.Assertions.*;
import it.unimi.di.sweng.lab06.Feline;

public class FelineTaleTest {
	
	@Rule
	public Timeout globalTimeout = Timeout.seconds(2);

	@Rule
	public final SystemOutRule output = new SystemOutRule().enableLog();


	private SimpleFelineFactory sfactory = new SimpleFelineFactory();
	private CounterFelineFactory cfactory = new CounterFelineFactory();

	@Test
	public void lionsAndTigersRoar() {
		Feline lion = sfactory.createLion();
		Feline tiger = sfactory.createTiger();
		lion.roar();
		tiger.roar();
		assertThat(output.getLog()).isEqualTo("roar\nroar\n");
	}

    @Test
	public void testDomesticCatPelucheCat(){
		DomesticCat domesticCat= new DomesticCat();
		PelucheCat pelucheCat= new PelucheCat();
		domesticCat.meow();
		pelucheCat.jingle();
		assertThat(output.getLog()).isEqualTo("meow\nsingsong-miao\n");
	}

	@Test
	public void testAdapter(){
		FelineDomesticCat felineDomesticCat = (FelineDomesticCat) sfactory.createDomesticCat();
		FelinePelucheCat felinePelucheCat = (FelinePelucheCat) sfactory.createPelucheCat();
		felineDomesticCat.roar();
		felinePelucheCat.roar();
		assertThat(output.getLog()).isEqualTo("meow\nsingsong-miao\n");

	}

	@Test
	public void testCounter(){
		FelineCounter.resetCount();
		FelineCounter dLion= (FelineCounter) cfactory.createLion();
		FelineCounter dTiger= (FelineCounter) cfactory.createTiger();
		FelineCounter dFelinePelucheCat= new FelineCounter(new FelinePelucheCat(new PelucheCat()));
		assertThat(FelineCounter.getCount()).isEqualTo(0);
		dLion.roar();
		assertThat(FelineCounter.getCount()).isEqualTo(1);
		dTiger.roar();
		dTiger.roar();
		dFelinePelucheCat.roar();
		assertThat(FelineCounter.getCount()).isEqualTo(4);
		FelineCounter.resetCount();
		assertThat(FelineCounter.getCount()).isEqualTo(0);

	}

	@Test
	public void testFactory(){

		Feline lion = sfactory.createLion();
		Feline domesticCat= sfactory.createDomesticCat();
		Feline clion = cfactory.createLion();
		Feline ctiger = cfactory.createTiger();
		lion.roar();
		domesticCat.roar();
        assertThat(output.getLog()).isEqualTo("roar\nmeow\n");
        assertThat(FelineCounter.getCount()).isEqualTo(0);
        clion.roar();
        ctiger.roar();
        assertThat(FelineCounter.getCount()).isEqualTo(2);
        FelineCounter.resetCount();
        assertThat(FelineCounter.getCount()).isEqualTo(0);
	}

	@Test
	public void testComposite(){
		FelineCounter.resetCount();
		Gang branco = new Gang();
		branco.add(sfactory.createLion());
		branco.add(sfactory.createTiger());
		branco.add(sfactory.createDomesticCat());
		branco.add(sfactory.createPelucheCat());
		branco.roar();
		assertThat(output.getLog()).isEqualTo("roar\nroar\nmeow\nsingsong-miao\n");

		branco.add(cfactory.createLion());
		branco.add(cfactory.createDomesticCat());
		branco.add(cfactory.createPelucheCat());
		branco.roar();
		assertThat(output.getLog()).isEqualTo("roar\nroar\nmeow\nsingsong-miao\nroar\nroar\nmeow\nsingsong-miao\nroar\nmeow\nsingsong-miao\n");
	}
}