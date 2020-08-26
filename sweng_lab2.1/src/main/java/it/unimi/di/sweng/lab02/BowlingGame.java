package it.unimi.di.sweng.lab02;

public class BowlingGame implements Bowling {
    int score=0;
    int[] points=new int[22];
    int rolls=0;

    @Override
    public void roll(int pins) {
        points[rolls]=pins;
        rolls++;
    }

    @Override
    public int score() {
        for (int i = 0; i < 20; i++) {
            if(strikeCondition(i,rolls)){
                updateScore(i+2);
                updateScore(i+3);
            }
            else
                if(i<18 && spareCondition(i))
                    updateScore(i+2);

            updateScore(i);
            updateScore(i+1);
            i++;
        }
        return score;
    }

    private boolean strikeCondition(int tiro, int rolls) {
        if (points[tiro] == 10) {
            for (int i = points.length - 2; i > tiro; i--) {
                points[i + 1] = points[i];
            }
            points[tiro + 1] = 0;
        }
        return points[tiro] == 10;
    }

    private boolean spareCondition(int tiro){
        return points[tiro]+points[tiro+1]==10;
    }

    private int updateScore(int tiro){
        return score+=points[tiro];
    }
}
