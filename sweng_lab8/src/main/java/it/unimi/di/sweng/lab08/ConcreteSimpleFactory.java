package it.unimi.di.sweng.lab08;

public class ConcreteSimpleFactory implements AbstractRotatorFactory{
    @Override
    public Rotator createRotator(String input) {
        return new Rotator(input);
    }
}
