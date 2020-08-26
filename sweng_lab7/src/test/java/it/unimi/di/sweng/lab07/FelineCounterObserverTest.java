package it.unimi.di.sweng.lab07;

import it.unimi.di.sweng.lecture.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.junit.rules.Timeout;

import java.util.ArrayList;
import java.util.Observer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class FelineCounterObserverTest {

	@Rule
	public Timeout globalTimeout = Timeout.seconds(2); // 2 seconds max per test

	private FelineCounterObserver counter;

	@Before
	public void setUp() {
		counter = new FelineCounterObserver();
	}

	@Rule
	public final SystemErrRule stderr = new SystemErrRule().enableLog();

	@Test
	public void FelineLoggerObserverTest() {
		ObservableFeline oLion = new ObservableFeline(new Lion());
		oLion.addObserver(counter);
        doRoar(oLion, 1);
        assertEquals(1, counter.getCount(oLion.getFelineClass()));
		doRoar(oLion,2);
		assertEquals(3, counter.getCount(oLion.getFelineClass()));

		ObservableFeline oFelineDomesticCat = new ObservableFeline(new FelineDomesticCat());
		oFelineDomesticCat.addObserver(counter);
		doRoar(oFelineDomesticCat, 1);
		assertEquals(1, counter.getCount(oFelineDomesticCat.getFelineClass()));
        doRoar(oFelineDomesticCat, 2);
		assertEquals(3, counter.getCount(oFelineDomesticCat.getFelineClass()));

		counter.resetCount();
		assertEquals(0, counter.getCount(oLion.getFelineClass()));
		assertEquals(0, counter.getCount(oFelineDomesticCat.getFelineClass()));

	}


    @Test
    public void ObservedCounterFelineFactoryTest(){
        ArrayList <Observer> theObserver = new ArrayList<>();
        theObserver = createObserverList(0,4);

        ObservedFelineFactory myFactory = new ObservedFelineFactory(theObserver);

        ObservableFeline myLion = (ObservableFeline) myFactory.createLion();
        ObservableFeline myFelinePelucheCat = (ObservableFeline) myFactory.createPelucheCat();
        ObservableFeline myFelineDomesticCat = (ObservableFeline) myFactory.createDomesticCat();
        ObservableFeline myTiger = (ObservableFeline) myFactory.createTiger();

        doRoar(myTiger,1);
        for(Observer o: theObserver)
                assertEquals(1, ((FelineCounterObserver) o).getCount(myTiger.getFelineClass()));
        doRoar(myTiger,5);
        for(Observer o: theObserver)
                assertEquals(6, ((FelineCounterObserver) o).getCount(myTiger.getFelineClass()));

        doRoar(myFelineDomesticCat,2);
        for(Observer o: theObserver)
                assertEquals(2, ((FelineCounterObserver) o).getCount(myFelineDomesticCat.getFelineClass()));
        doRoar(myFelineDomesticCat,2);
        for(Observer o: theObserver)
                assertEquals(4, ((FelineCounterObserver) o).getCount(myFelineDomesticCat.getFelineClass()));

        for(Observer o: theObserver)
                ((FelineCounterObserver) o).resetCount();
        for(Observer o: theObserver){
                assertEquals(0, ((FelineCounterObserver) o).getCount(myTiger.getFelineClass()));
                assertEquals(0, ((FelineCounterObserver) o).getCount(myFelineDomesticCat.getFelineClass()));
            }
    }

    @Test
	public void ObservedMiscFelineFactoryTest(){
	    ArrayList <Observer> theObserver = new ArrayList<>();
	    theObserver = createObserverList(3,3);

		ObservedFelineFactory myFactory = new ObservedFelineFactory(theObserver);

		ObservableFeline myLion = (ObservableFeline) myFactory.createLion();
		ObservableFeline myFelinePelucheCat = (ObservableFeline) myFactory.createPelucheCat();
		ObservableFeline myFelineDomesticCat = (ObservableFeline) myFactory.createDomesticCat();
		ObservableFeline myTiger = (ObservableFeline) myFactory.createTiger();

		doRoar(myLion,1);
		for(Observer o: theObserver)
		    if (o instanceof FelineCounterObserver)
                assertEquals(1, ((FelineCounterObserver) o).getCount(myLion.getFelineClass()));
		doRoar(myLion,3);
        for(Observer o: theObserver)
            if (o instanceof FelineCounterObserver)
                assertEquals(4, ((FelineCounterObserver) o).getCount(myLion.getFelineClass()));

        doRoar(myFelinePelucheCat,1);
        for(Observer o: theObserver)
            if (o instanceof FelineCounterObserver)
                assertEquals(1, ((FelineCounterObserver) o).getCount(myFelinePelucheCat.getFelineClass()));
        doRoar(myFelinePelucheCat,1);
        for(Observer o: theObserver)
            if (o instanceof FelineCounterObserver)
                assertEquals(2, ((FelineCounterObserver) o).getCount(myFelinePelucheCat.getFelineClass()));

        for(Observer o: theObserver)
            if (o instanceof FelineCounterObserver)
                ((FelineCounterObserver) o).resetCount();
        for(Observer o: theObserver)
            if (o instanceof FelineCounterObserver) {
                assertEquals(0, ((FelineCounterObserver) o).getCount(myLion.getFelineClass()));
                assertEquals(0, ((FelineCounterObserver) o).getCount(myFelinePelucheCat.getFelineClass()));
            }

        doRoar(myFelineDomesticCat,1);
		assertEquals("Lion\nLion\nLion\nLion\nLion\nLion\nLion\nLion\nLion\nLion\nLion\nLion\nFelinePelucheCat\nFelinePelucheCat\nFelinePelucheCat\nFelinePelucheCat\nFelinePelucheCat\nFelinePelucheCat\nFelineDomesticCat\nFelineDomesticCat\nFelineDomesticCat\n", stderr.getLog());

		doRoar(myTiger,2);
		assertEquals("Lion\nLion\nLion\nLion\nLion\nLion\nLion\nLion\nLion\nLion\nLion\nLion\nFelinePelucheCat\nFelinePelucheCat\nFelinePelucheCat\nFelinePelucheCat\nFelinePelucheCat\nFelinePelucheCat\nFelineDomesticCat\nFelineDomesticCat\nFelineDomesticCat\nTiger\nTiger\nTiger\nTiger\nTiger\nTiger\n", stderr.getLog());

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
