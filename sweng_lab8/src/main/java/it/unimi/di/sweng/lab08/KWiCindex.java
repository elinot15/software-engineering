package it.unimi.di.sweng.lab08;

import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class KWiCindex {
    private SortAlgorithm sortAlgorithm;
    private ArrayList<String> righe= new ArrayList<String>();
    private Output output;

    public KWiCindex(Reader reader) {
        Scanner input= new Scanner(reader);
        while(input.hasNextLine()){
            Rotator rotator= new Rotator(input.nextLine());
            while (rotator.hasNext()) {
                righe.add(rotator.next());
            }
        }

    }

    public KWiCindex(Reader reader, boolean b) {
        Scanner input= new Scanner(reader);
        while(input.hasNextLine()){
            RotatorPunteggiatura punteggiatura= new RotatorPunteggiatura(input.nextLine(), b);
            while (punteggiatura.hasNext()) {
                righe.add(punteggiatura.next());

            }
        }
    }

    public KWiCindex(Reader reader, String[] array) {
        Scanner input= new Scanner(reader);
        while(input.hasNextLine()){
            RotatorStopWord stopWord= new RotatorStopWord(input.nextLine(), array);
            while (stopWord.hasNext()) {
                righe.add(stopWord.next());

            }
        }
    }

    public void setSortAlgorithm(SortAlgorithm sortAlgorithm){
        this.sortAlgorithm= sortAlgorithm;
   }

    public void setOutput(Output output) {
        this.output= output;
    }


    public String getSortedList() {
        return output.getOutput(sortAlgorithm.sort(righe));

    }


}
