package it.unimi.di.sweng.lab08;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class sortIgnoreCase implements SortAlgorithm {
    @Override
    public ArrayList<String> sort(ArrayList<String> list) {
        list.sort(Comparator.comparing(o->(o.substring(o.indexOf(' '))).toLowerCase()));
        return list;

    }
}
