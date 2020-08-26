package it.unimi.di.sweng.lecture;

import java.util.ArrayList;
import java.util.List;

public class Gang implements Feline {
	
	private List<Feline> felines = new ArrayList<Feline>();

	@Override
	public void roar() {
		for (Feline feline: felines)
			feline.roar();
	}

	public void add(Feline feline) {
		felines.add(feline);
	}

}