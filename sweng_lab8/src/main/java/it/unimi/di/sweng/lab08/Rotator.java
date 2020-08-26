package it.unimi.di.sweng.lab08;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Rotator implements Iterator<String> {

    protected String[] list;
    protected int pos=0;
    protected static int counterIDs=0;
    protected int id;

    public Rotator(){
        list=new String[0];
    }

    public Rotator(String s) {
        list= s.split(" ");
        id=counterIDs;
        counterIDs++;
    }

    public static void resetCounter() {
        counterIDs=0;
    }

    @Override
    public boolean hasNext() { return pos<list.length; }

    @Override
    public String next() {
        if(!hasNext())
            throw new NoSuchElementException("Non ci sono più elementi");
        StringBuilder sb= new StringBuilder(id+": "+concatenaStringhe(pos, list.length));
        if(pos>0)
          sb.append("   ");

        sb.append(concatenaStringhe(0, pos));

        pos++;
        return sb.toString();
    }

    private String concatenaStringhe(int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++) {
            if (i != start)
                sb.append(" ");
            sb.append(list[i]);
        }
        return sb.toString();
    }
}
