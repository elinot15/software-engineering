package it.unimi.di.sweng.lecture;

public class FelineDomesticCat extends DomesticCat implements Feline {

	@Override
	public void roar() {
		meow();
	}

}
