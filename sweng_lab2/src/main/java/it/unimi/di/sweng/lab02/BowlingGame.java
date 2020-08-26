package it.unimi.di.sweng.lab02;
import java.util.ArrayList;
import java.lang.Integer;

public class BowlingGame implements Bowling {

    private int currentRoll=0;
    private ArrayList<Integer> rolls = new ArrayList<Integer>();
    //private int[] rolls= new int[20];

    public void roll(int pins) {
        //rolls[currentRoll++]= pins;
        rolls.add(pins);
    }


    public int score() {
        int scores=0;
        int NUM_FRAMES = 10;
        int currentRoll=0;
        boolean isstrike= false;
        for (int frame = 0; frame < NUM_FRAMES; frame++) {
            if(rolls.get(currentRoll)==10){
                scores+= rolls.get(currentRoll+1)+rolls.get(currentRoll+2);
                isstrike=true;
            }

            if (isstrike==false && currentRoll < 19 && isSpare(currentRoll)){
                scores += rolls.get(currentRoll + 2);
        }
            scores += rolls.get(currentRoll) + rolls.get(currentRoll + 1);
            if(isstrike && frame==9){
                scores-=rolls.get(currentRoll+1);
            }
            currentRoll+=2;

        }
        return scores;
    }

    private boolean isSpare(int i) {
        return 10==rolls.get(i)+rolls.get(i+1);
    }
}
