package it.unimi.di.sweng.lab08;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class sortMinuscolo implements SortAlgorithm {
    @Override
    public ArrayList<String> sort(ArrayList<String> list) {
        ArrayList<String> minuscolo= new ArrayList<String >();
        ArrayList<String> maiuscolo= new ArrayList<String>();
        for (int i = 0; i <list.size(); i++) {
            String s= list.get(i).substring((list.get(i)).indexOf(' ')+1);
            if(Character.isUpperCase(s.charAt(0))){
                maiuscolo.add(list.get(i));
            }
            else {
                minuscolo.add(list.get(i));
            }
        }
        maiuscolo.sort(Comparator.comparing(o->o.substring(o.indexOf(' '))));
        minuscolo.sort(Comparator.comparing(o->o.substring(o.indexOf(' '))));
        list.removeAll(list);
        list.addAll(minuscolo);
        list.addAll(maiuscolo);
        return list;
    }
}
