package it.unimi.di.sweng.lecture;

public class CounterFelineFactory extends AbstractFelineFactory {

	@Override
	public Feline createLion() {
		return new FelineCounter(new Lion());
	}

	@Override
	public Feline createTiger() {
		return new FelineCounter(new Tiger());
	}
	
	@Override
	public Feline createDomesticCat() {
		return new FelineCounter(new FelineDomesticCat());
	}

	@Override
	public Feline createPelucheCat() {
		return new FelineCounter(new FelinePelucheCat(new PelucheCat()));
	}
}