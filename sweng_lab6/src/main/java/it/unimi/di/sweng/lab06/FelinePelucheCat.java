package it.unimi.di.sweng.lab06;

public class FelinePelucheCat implements Feline{

    private PelucheCat adaptee=null;

    public FelinePelucheCat(PelucheCat pelucheCat){
        adaptee=pelucheCat;
    }

    @Override
    public void roar() {
        adaptee.jingle();
    }
}
