package it.unimi.di.sweng.lab06;

public class SimpleFelineFactory extends AbstractFelineFactory {


    @Override
    public Feline createLion() {
        return new Lion();
    }

    @Override
    public Feline createTiger() {
        return new Tiger();
    }

    @Override
    public Feline createDomesticCat() {
        return new FelineDomesticCat();
    }

    @Override
    public Feline createPelucheCat() {
        return new FelinePelucheCat(new PelucheCat());
    }
}
