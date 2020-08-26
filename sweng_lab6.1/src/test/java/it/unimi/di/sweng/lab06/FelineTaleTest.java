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
		Feline lion = new Lion();
		Feline tiger = new Tiger();
		lion.roar();
		tiger.roar();
		assertThat(output.getLog()).isEqualTo("roar\nroar\n");
	}

	@Test
	public void PelucheAndDomesticCat() {
		DomesticCat domesticCat = new DomesticCat ();
		PelucheCat pelucheCat = new PelucheCat();
		domesticCat.meow();
		pelucheCat.jingle();
		assertThat(output.getLog()).isEqualTo("meow\nsingsong-miao\n");
	}

	@Test
	public void FelinePelucheAndDomesticCat() {
		FelineDomesticCat felineDomesticCat = new FelineDomesticCat ();
		FelinePelucheCat felinePelucheCat = new FelinePelucheCat(new PelucheCat());
		felineDomesticCat.roar();
		felinePelucheCat.roar();
		assertThat(output.getLog()).isEqualTo("meow\nsingsong-miao\n");
	}

	@Test
	public void felineCouter(){
		FelineCounter felineCounter = new FelineCounter(new Lion());
		felineCounter.roar();
		felineCounter.roar();
		assertThat(felineCounter.getCounter()).isEqualTo(2);
		felineCounter.resetCount();
		assertThat(felineCounter.getCounter()).isEqualTo(0);
	}

	@Test
	public void felineFactory(){
		Feline lion = sfactory.createLion();
		Feline domesticCat= sfactory.createDomesticCat();
		Feline clion = cfactory.createLion();
		Feline ctiger = cfactory.createTiger();
		lion.roar();
		domesticCat.roar();
		assertThat(output.getLog()).isEqualTo("roar\nmeow\n");
		assertThat(FelineCounter.getCounter()).isEqualTo(0);
		clion.roar();
		ctiger.roar();
		assertThat(FelineCounter.getCounter()).isEqualTo(2);
		FelineCounter.resetCount();
		assertThat(FelineCounter.getCounter()).isEqualTo(0);
	}
}