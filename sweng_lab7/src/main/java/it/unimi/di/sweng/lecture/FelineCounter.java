package it.unimi.di.sweng.lecture;

public class FelineCounter implements Feline {

	private static int counter = 0;
	private final Feline feline;

	public FelineCounter(Feline feline) {
		this.feline = feline;
	}

	@Override
	public void roar() {
		feline.roar();
		counter++;
	}

	public static int getCount() {
		return counter;
	}

	public static void resetCount() {
		counter = 0;
	}
}