package it.unimi.di.sweng.lecture;

public class FelinePelucheCat implements Feline {

	private final PelucheCat cat;

	public FelinePelucheCat(PelucheCat cat) {
		this.cat = cat;
	}

	@Override
	public void roar() {
		cat.jingle();
	}
}