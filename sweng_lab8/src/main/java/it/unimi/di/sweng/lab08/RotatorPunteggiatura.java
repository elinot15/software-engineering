package it.unimi.di.sweng.lab08;

import java.util.ArrayList;

public class RotatorPunteggiatura extends Rotator{
    public RotatorPunteggiatura(String s, boolean b) {
        if(b)
            s= togliPunteggiatura(s);
        super.list= s.split(" ");
        super.id=counterIDs;
        super.counterIDs++;

    }

    public String togliPunteggiatura(String s){
        StringBuilder sb= new StringBuilder();
        for (int i = 0; i <s.length() ; i++) {
            char controllo= s.charAt(i);
            if(Character.isAlphabetic(controllo) || controllo==' ' || controllo=='\n'){
                sb.append(controllo);
            }
        }
        return sb.toString();
    }
}
