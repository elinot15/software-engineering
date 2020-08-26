package it.unimi.di.sweng.lab08;

public class ConcreteFactoryPunteggiatura implements AbstractRotatorFactory{

    @Override
    public Rotator createRotator(String input) {
        return new RotatorPunteggiatura(input,true);
    }
}
