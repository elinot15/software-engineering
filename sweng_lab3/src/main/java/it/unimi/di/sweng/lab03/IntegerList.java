package it.unimi.di.sweng.lab03;

import java.io.InputStreamReader;
import java.io.Reader;

import static java.lang.Math.*;

public class IntegerList {

    private Element head=null;
    private Element tail=null;
    private Element puntatore = null;
    private boolean isEmpty() {
        return head == null;
    }

    private boolean isOnlyOne() {
        return head.next==null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("[");
        if(!isEmpty()){
            result.append(head.value);
            for (Element cont = head.next; cont!=null; cont=cont.next) {
                result.append (" ");
                result.append(cont.value);
            }
        }
        result.append("]");

        return result.toString();
    }

    public void addLast(int value){
        Element add= new Element();

        add.value= value;
        add.next= null;

        if(isEmpty())
            tail=head=add;

        else{
            tail.next=add;
            tail=add;
        }
    }

    public void newFromString(String toValue) {
        if(!toValue.isEmpty()) {
            for (int i = 0; i < toValue.length(); i++) {
                if (Character.isAlphabetic(toValue.charAt(i)))
                    throw new IllegalArgumentException("Not supported input format");
            }
            for (int actual = 1; actual <= Integer.parseInt(toValue); actual++)
                addLast(actual);
        }
    }

    public void addFirst(int value) {
        Element add= new Element();
        add.value=value;
        add.next=head;

        head=add;
    }


    public boolean removeFirst() {
        if (isEmpty())
            return false;
        if (isOnlyOne()) {
            head = tail = null;
            return true;
        }
        else {
            head = head.next;
        }
        return true;

    }

    public boolean removeLast() {
        if (isEmpty())
            return false;

        else {
            if (isOnlyOne()) {
                head = tail = null;
            }

            else {
                Element cur = head;
                while (cur != null) {
                    if ((cur.next).next == null) {
                        cur.next = null;
                        tail = cur;
                    }
                    cur = cur.next;
                }
            }

            return true;
        }
    }

    public boolean remove(int value) {
        Element cur, prev;

        if(isEmpty())
            return false;

        else if(isOnlyOne()){
            if(head.value==value) {
                head = tail = null;
                return true;
            }
            else
                return false;
        }

        else{
            for ( cur = head, prev = null ; cur != null ; prev =cur, cur = cur.next) {
                if (cur.value == value) {
                    if (cur == head)
                        head = head.next;
                    else
                        prev.next = cur.next;

                    return true;
                }
            }
            return false;
        }
    }

    public boolean removeAll(int value) {
        Element cur, prev;
        boolean ris = false;

        if(isEmpty())
            ris = false;

        else if(isOnlyOne()){
            if(head.value==value) {
                head = tail = null;
                ris = true;
            }
            else
                ris = false;
        }

        else{
            for ( cur = head, prev = null ; cur != null ; prev =cur, cur = cur.next){
                if(cur.value==value){
                    if (cur == head)
                        head = head.next;
                    else
                        prev.next=cur.next;

                    ris= true;
                }

            }
        }

        return ris;
    }

    public double mean() {
        Element cur=head;
        double somma=0;
        double ris=0;
        double var=0;
        while(cur!=null){
            somma+=cur.value;
            var++;
            cur=cur.next;
        }
        if(var!=0)
            ris= somma/var;
        return ris;
    }

    public double stdDev() {
        Element cur = head;
        if (isEmpty())
            return 0;

        if (isOnlyOne()) {
            return 0;
        } else {
            double medio = mean();
            double somma = 0;
            double cont = 0;
            double ris = 0;
            while (cur != null) {
                somma += pow((cur.value - medio), 2);
                cont += 1;
                cur = cur.next;
            }
            ris = sqrt(somma / (cont - 1));
            ris = floor(ris*1000)/1000;
            return ris;
        }
    }

    public int next(){
        if (puntatore == null) {
            puntatore = head;
            return puntatore.value;
        }
        else {
            if (puntatore.next == null)
                return puntatore.value;
            else {
                puntatore = puntatore.next;
                return puntatore.value;
            }
        }

    }

    public int prev(){
        if (puntatore == null) {
            puntatore = head;
            return puntatore.value;
        }
        else {
            Element curr = head, prec = null;
            while (curr != puntatore) {
                prec = curr;
                curr = curr.next;
            }
            if (prec == null)
                return puntatore.value;
            else {
                puntatore = prec;
                return puntatore.value;
            }
        }
    }

    public void addFromFile() throws java.io.IOException {
        Reader resourceReader = new InputStreamReader(getClass().getResourceAsStream("/input.txt"));
        int intFromFile = 0;
        char charFromInt = ' ';

        while (intFromFile != -1) {
            StringBuilder num = new StringBuilder();

            intFromFile = resourceReader.read();
            charFromInt = (char) intFromFile;

            while (charFromInt != ' ' && intFromFile!= -1){
                if (Character.isDigit(charFromInt))
                    num.append(charFromInt);

                intFromFile = resourceReader.read();
                charFromInt = (char) intFromFile;
            }

            addLast(Integer.parseInt(num.toString()));
        }
    }

    private class Element {
        int value;
        Element next;
    }
}
