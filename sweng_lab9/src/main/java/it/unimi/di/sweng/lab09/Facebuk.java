package it.unimi.di.sweng.lab09;

import java.io.Reader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Facebuk {

    private Alfabetico alfabetico;
    List<Utente> utenti = new ArrayList<>();

    public Facebuk(Reader reader) {
        Scanner scanner= new Scanner(reader);
        while(scanner.hasNextLine()){
            String nuovoUtente[] = scanner.nextLine().split(" ");
            List<String> nuoviAmici= new ArrayList<String>();
            for (int i = 1; i < nuovoUtente.length; i++) {
                nuoviAmici.add(nuovoUtente[i]);
            }
            Utente utente= new Utente(nuovoUtente[0], nuoviAmici);
            utenti.add(utente);
        }
    }

    public String crossPrint() {
        StringBuilder result= new StringBuilder();

        List<String> coppieAmici = new ArrayList<>();
        creaCoppie(coppieAmici);
        eliminaDupplicati(coppieAmici);

        for (int i = 0; i <coppieAmici.size() ; i++) {
            result.append("("+coppieAmici.get(i)+") [");
            String [] coppia= coppieAmici.get(i).split(" ");

            int indexFirst=1, indexSecond=-1;

            indexFirst = getIndex(indexFirst, coppia, 0);
            indexSecond = getIndex(indexSecond, coppia, 1);

            for (int j = 0; j <utenti.get(indexFirst).getNumeroAmici() ; j++) {
                if(utenti.get(indexSecond).amici.contains((utenti.get(indexFirst).amici.get(j)))){
                    result.append(utenti.get(indexFirst).amici.get(j));
                    int minore;
                    if(indexFirst<indexSecond){
                        minore=indexFirst;
                    } else {
                        minore=indexSecond;
                    }
                    if(j!=utenti.get(minore).getNumeroAmici()-1)
                    result.append(" ");
                }
            }
            result.append("]");
            if(i!=coppieAmici.size())
                result.append("\n");
        }
        return result.toString();
    }

    private int getIndex(int indexFirst, String[] coppia, int i2) {
        for (int j = 0; j < utenti.size(); j++) {
            if (utenti.get(j).getUsername().equals(coppia[i2])) {
                indexFirst = j;
                break;
            }
        }
        return indexFirst;
    }

    private void eliminaDupplicati(List<String> coppieAmici) {
        for (int i = 0; i <coppieAmici.size() ; i++) {
            String coppia[] = coppieAmici.get(i).split(" ");
            for (int j = i; j <coppieAmici.size() ; j++) {
                if(coppieAmici.get(j).contains(coppia[0]) && coppieAmici.get(j).contains(coppia[1])){
                    if(coppieAmici.get(i).compareTo((coppieAmici.get(j)))>0){
                        coppieAmici.remove(j);
                    }
                }
            }
        }
    }

    private void creaCoppie(List<String> coppieAmici) {
        for (int i = 0; i <utenti.size() ; i++) {
            for (int j = 0; j <utenti.get(i).getNumeroAmici(); j++) {
                coppieAmici.add(utenti.get(i).getUsername()+" "+utenti.get(i).getAmico(j));
            }

        }
    }

    public void sortListaAmici(Alfabetico alfabetico) {
        this.alfabetico=alfabetico;
    }

}
