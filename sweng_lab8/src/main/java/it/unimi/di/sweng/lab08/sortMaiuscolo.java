package it.unimi.di.sweng.lab08;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.lang.Object;

public class sortMaiuscolo implements SortAlgorithm {
    @Override
    public ArrayList<String> sort(ArrayList<String> list) {
        list.sort(Comparator.comparing(o->o.substring(o.indexOf(' '))));
        return list;
    }
}
