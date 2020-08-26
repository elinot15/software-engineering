package it.unimi.di.sweng.lecture;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.rules.Timeout;

import it.unimi.di.sweng.lecture.AbstractFelineFactory;
import it.unimi.di.sweng.lecture.CounterFelineFactory;
import it.unimi.di.sweng.lecture.Gang;
import it.unimi.di.sweng.lecture.DomesticCat;
import it.unimi.di.sweng.lecture.Lion;
import it.unimi.di.sweng.lecture.FelineCounter;
import it.unimi.di.sweng.lecture.Feline;
import it.unimi.di.sweng.lecture.Tiger;
import it.unimi.di.sweng.lecture.PelucheCat;
import it.unimi.di.sweng.lecture.SimpleFelineFactory;

public class FelineTaleTest {

	@Rule
	public Timeout globalTimeout = Timeout.seconds(2); // 2 seconds max per test
	
	@Rule
	public final SystemOutRule output = new SystemOutRule().enableLog();

	@Before
	public void resetCounter() {
		FelineCounter.resetCount();
	}

	@Test
	public void felinesRoar() {
		Feline lion = new Lion();
		Feline tiger = new Tiger();
		lion.roar();
		tiger.roar();
		assertEquals(output.getLog(), "roar\nroar\n");
	}

	@Test
	public void otherAnimals() throws Exception {
		DomesticCat cat = new DomesticCat();
		PelucheCat peluche = new PelucheCat();
		cat.meow();
		peluche.jingle();
		assertEquals(output.getLog(), "meow\nsingsong-miao\n");
	}
	
	@Test
	public void felineAdapter() throws Exception {
		Feline adaptedCat = new FelineDomesticCat();
		Feline adaptedPeluche = new FelinePelucheCat(new PelucheCat());
		adaptedCat.roar();
		adaptedPeluche.roar();
		assertEquals(output.getLog(), "meow\nsingsong-miao\n");
	}

	private Gang setupAMixedGang(AbstractFelineFactory factory) {
		Gang gang = new Gang();
		gang.add(factory.createLion());
		gang.add(factory.createTiger());
		gang.add(factory.createDomesticCat());
		gang.add(factory.createPelucheCat());
		gang.add(setupNumberedGang(factory, 2));
		return gang;
	}

	@Test
	public void aGangRoars() throws Exception {
		AbstractFelineFactory factory = new SimpleFelineFactory();
		Gang gang = setupAMixedGang(factory);
		gang.roar();
		assertEquals(output.getLog(), "roar\nroar\nmeow\nsingsong-miao\nroar\nroar\n");
	}

	@Test
	public void countRoars() throws Exception {
		AbstractFelineFactory factory = new CounterFelineFactory();
		Gang gang = setupAMixedGang(factory);
		gang.roar();
		assertEquals(6, FelineCounter.getCount());
	}

	private Gang setupNumberedGang(AbstractFelineFactory fact, int n) {
		Gang gang = new Gang();
		for (int i = 0; i < n; i++)
			gang.add(fact.createTiger());
		return gang;
	}

	@Test
	public void aGangIsFeline() throws Exception {
		AbstractFelineFactory factory = new CounterFelineFactory();
		Gang subGang = setupNumberedGang(factory, 10);
		Gang gang = setupNumberedGang(factory, 5);
		gang.add(subGang);
		gang.roar();
		assertEquals(15, FelineCounter.getCount());
	}

}