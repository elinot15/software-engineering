package it.unimi.di.sweng.lab07;

import it.unimi.di.sweng.lecture.FelineDomesticCat;
import it.unimi.di.sweng.lecture.FelinePelucheCat;
import it.unimi.di.sweng.lecture.Lion;
import it.unimi.di.sweng.lecture.PelucheCat;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.junit.rules.Timeout;

import java.util.ArrayList;
import java.util.Observer;

import static org.junit.Assert.assertEquals;

public class FelineLoggerObserverTest {

	@Rule
	public Timeout globalTimeout = Timeout.seconds(2); // 2 seconds max per test

	@Rule
	public final SystemErrRule stderr = new SystemErrRule().enableLog();

	private FelineLoggerObserver logger;

	@Before
	public void setUp() {
		logger = new FelineLoggerObserver();
	}

	@Test
	public void FelineLoggerObserverTest() {
		ObservableFeline oLion = new ObservableFeline(new Lion());
		oLion.addObserver(logger);
		doRoar(oLion,2);
		assertEquals("Lion\nLion\n", stderr.getLog());

		ObservableFeline oFelineDomesticCat = new ObservableFeline (new FelineDomesticCat());
		assertEquals(oFelineDomesticCat.getClass().getSimpleName(), "ObservableFeline");
		oFelineDomesticCat.addObserver(logger);
		doRoar(oFelineDomesticCat, 1);
		assertEquals("Lion\nLion\nFelineDomesticCat\n", stderr.getLog());

		ObservableFeline oFelinePelucheCat = new ObservableFeline (new FelinePelucheCat(new PelucheCat()));
		assertEquals(oFelinePelucheCat.getClass().getSimpleName(), "ObservableFeline");
		oFelinePelucheCat.addObserver(logger);
		doRoar(oFelinePelucheCat,1);
		assertEquals("Lion\nLion\nFelineDomesticCat\nFelinePelucheCat\n", stderr.getLog());
	}

	@Test
	public void ObservedLoggerFelineFactoryTest(){
		ArrayList<Observer> theObserver = new ArrayList<>();
		theObserver = createObserverList(4,0);

		ObservedFelineFactory myFactory = new ObservedFelineFactory(theObserver);

		ObservableFeline myLion = (ObservableFeline) myFactory.createLion();
		ObservableFeline myFelinePelucheCat = (ObservableFeline) myFactory.createPelucheCat();
		ObservableFeline myFelineDomesticCat = (ObservableFeline) myFactory.createDomesticCat();
		ObservableFeline myTiger = (ObservableFeline) myFactory.createTiger();

		doRoar(myLion,2);
		assertEquals("Lion\nLion\nLion\nLion\nLion\nLion\nLion\nLion\n", stderr.getLog());

		doRoar(myFelinePelucheCat,1);
		assertEquals("Lion\nLion\nLion\nLion\nLion\nLion\nLion\nLion\nFelinePelucheCat\nFelinePelucheCat\nFelinePelucheCat\nFelinePelucheCat\n", stderr.getLog());
	}

	private void doRoar(ObservableFeline whichFeline, int howMuchRoar) {
		for (int i = 0; i < howMuchRoar; i++)
			whichFeline.roar();
	}

	private ArrayList<Observer> createObserverList(int howManyLogger, int howManyCounter){
		ArrayList<Observer> myList = new ArrayList<Observer>();

		for (int i = 0; i < howManyLogger; i++)
			myList.add(new FelineLoggerObserver());

		for (int i = 0; i < howManyCounter; i++)
			myList.add(new FelineCounterObserver());

		return myList;
	}
}

