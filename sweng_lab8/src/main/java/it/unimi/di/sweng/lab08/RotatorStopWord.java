package it.unimi.di.sweng.lab08;

import java.util.ArrayList;
import java.util.Arrays;

public class RotatorStopWord extends Rotator{

    public RotatorStopWord(String nextLine, String[] array) {
        String inizio[]=  nextLine.split(" ");
        ArrayList<String> words= new ArrayList<String>(Arrays.asList(inizio));
        for (int i = 0; i <array.length ; i++) {
            for (int j = 0; j <words.size(); j++) {
             if(array[i].equals(words.get(j))){
                 words.remove(j);
             }
            }
        }
        super.list= words.toArray(new String[0]);
        super.id=counterIDs;
        super.counterIDs++;
    }
}
