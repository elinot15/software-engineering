package it.unimi.di.sweng.lab08;

import java.util.ArrayList;

public class OutputSpezzato implements Output{
    @Override
    public String getOutput(ArrayList<String> righe) {
        String colonna1;
        String colonna2;
        ArrayList<String> head= new ArrayList<String>();
        ArrayList<String> tail= new ArrayList<String>();
        ArrayList<String> num= new ArrayList<String>();
        for (int i = 0; i <righe.size() ; i++) {
            String numero= righe.get(i).substring(0, 2);
            int treSpazi= righe.get(i).indexOf("   ");
            if(treSpazi!=-1) {
                colonna1= righe.get(i).substring(treSpazi+3, righe.get(i).length());
                colonna2 = righe.get(i).substring(2, treSpazi);
            }
            else {
                colonna1= "";
                colonna2=righe.get(i).substring(2);
            }
            num.add(numero);
            head.add(colonna1);
            tail.add("   "+colonna2);
        }
        int max=0;
        max = getMax(head, max);
        StringBuilder sb= new StringBuilder();
        int spa;
        createOutput(head, tail, num, max, sb);
        return sb.toString();
    }

    private void createOutput(ArrayList<String> head, ArrayList<String> tail, ArrayList<String> num, int max, StringBuilder sb) {
        int spa;
        for (int i = 0; i <head.size(); i++) {
            spa=max-(head.get(i).length());
            sb.append(num.get(i));
                for (int j = 0; j <=spa; j++) {
                    sb.append(" ");
                }
            sb.append(head.get(i)+tail.get(i));

            if(i!=head.size()-1)
                sb.append("\n");
        }
    }

    private int getMax(ArrayList<String> head, int max) {
        for (int i = 0; i <head.size() ; i++) {
            if(((head.get(i)).length())>max){
                max=head.get(i).length();
            }
        }
        return max;
    }
}
