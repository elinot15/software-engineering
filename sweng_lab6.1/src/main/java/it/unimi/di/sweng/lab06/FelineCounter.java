package it.unimi.di.sweng.lab06;

public class FelineCounter implements Feline{
    public static int counter=0;
    private Feline decorator=null;
    public FelineCounter(Feline feline) {
     decorator= feline;
    }

    @Override
    public void roar() {
        decorator.roar();
        counter++;
    }

    public static int getCounter() {
        return counter;
    }

    public static void resetCount() {
        counter=0;
    }
}
