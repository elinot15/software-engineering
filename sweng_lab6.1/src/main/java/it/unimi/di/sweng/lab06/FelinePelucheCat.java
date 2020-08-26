package it.unimi.di.sweng.lab06;

public class FelinePelucheCat implements Feline{

    private PelucheCat adapter= null;
    public FelinePelucheCat(PelucheCat pelucheCat) {
        adapter=pelucheCat;
    }


    @Override
    public void roar() {
        adapter.jingle();
    }
}
