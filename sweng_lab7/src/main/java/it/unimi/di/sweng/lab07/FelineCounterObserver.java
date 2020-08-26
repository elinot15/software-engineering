package it.unimi.di.sweng.lab07;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class FelineCounterObserver implements Observer {
    private HashMap <String, Integer> myHashMap = new HashMap<>();

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {
            if (myHashMap.containsKey((String) arg))
                myHashMap.replace((String) arg, myHashMap.get((String) arg) + 1);
            else
                myHashMap.put((String) arg, 1);
        }
    }

    public int getCount(final String name){
        return myHashMap.get(name);
    }

    public void resetCount(){
        for (String myKey: myHashMap.keySet())
            myHashMap.replace(myKey, 0);
    }

}
