package it.unimi.di.sweng.lab08;

import java.util.ArrayList;

public class OutputNormale implements Output{
    @Override
    public String getOutput(ArrayList<String> righe) {
        return output(righe);
    }

    public String output(ArrayList<String> list){
        StringBuilder sb= new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if(i!=list.size()-1)
                sb.append("\n");
        }
        return sb.toString();
    }
}
