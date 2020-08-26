package it.unimi.di.sweng.lab09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Alfabetico implements sortListaAmici{

    @Override
    public List<String> sort(ArrayList<String> list) {
        Collections.sort(list);
        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i));
        }
        return  list;
    }
}
