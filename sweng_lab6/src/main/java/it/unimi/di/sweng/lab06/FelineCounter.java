package it.unimi.di.sweng.lab06;

public class FelineCounter implements Feline {

    public static int counter=0;
    private Feline component=null;

    public FelineCounter(Feline feline){
        component=feline;
    }

    public static int getCount() {
        return counter;
    }

    public static void resetCount() {
        counter=0;
    }

    @Override
    public void roar() {
        component.roar();
        counter++;
    }
}
