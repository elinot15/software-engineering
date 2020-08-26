package it.unimi.di.sweng.lab04;

import static java.lang.Integer.*;

public class StringCalculator implements Calculator{

    public static final char ACAPO = '\n';
    public static final char COMMA = ',';
    public static final int SIMPLESEP = 0;
    public static final int MULTISEP = 1;
    public static final String OPEN_QUADRA = "[";
    public static final String CLOSE_QUADRA = "]";
    public static final String EMPTY_STRING = "";
    public static final String DOUBLE_SLASH = "//";

    private boolean isNotEmpty(String expression) {
        return !expression.equals(EMPTY_STRING);
    }

    private String extractSep(String expression, int condition)
    {
        switch(condition) {
            case SIMPLESEP:
                return expression.substring(2, 3);
            case MULTISEP:
                return expression.substring(expression.indexOf(OPEN_QUADRA) + 1, expression.indexOf(CLOSE_QUADRA));
            default:
                return null;
        }
    }

    private boolean isSeparatoreDiverso(String expression) {
        return expression.substring(0,2).equals(DOUBLE_SLASH) && expression.charAt(3)==ACAPO;
    }

    private boolean isNegative(String s, char c) {
        return s.charAt(0) == c;
    }

    private boolean isMultiChar(String expression) {
        return expression.substring(0,3).equals(DOUBLE_SLASH.concat(OPEN_QUADRA)) && expression.substring(expression.indexOf(CLOSE_QUADRA), expression.indexOf(CLOSE_QUADRA)+2).equals(CLOSE_QUADRA.concat(Character.toString(ACAPO)));
    }

    private void writeNegatives(StringBuilder sb, String[] number, int i) {
        sb.append(" "+number[i]);
        if(i!=(number.length-1))
            sb.append(COMMA);
    }

    private boolean isNotHigh(String s) {
        return parseInt(s)<=1000;
    }

    private int getContaQuadre(String expression) {
        int contaQuadre = 0;
        for (int i = 0; i<expression.length(); i++) {
            if (expression.charAt(i) == '[')
                contaQuadre++;
        }
        return contaQuadre;
    }

    private void ExtractAllSep(int contaQuadre, String primaParte, String[] multiSep) {
        int i=primaParte.indexOf('[');
        int f=primaParte.indexOf(']');
        for (int j = 0; j < contaQuadre; j++) {
            multiSep[j]=primaParte.substring(i+1,f);
            i=primaParte.indexOf('[', f);
            f=primaParte.indexOf(']', i+1);
        }
    }

    @Override
    public int add(String expression) throws IllegalArgumentException{
        int result = 0;
        String sep=",";
        if(isNotEmpty(expression)){
            if(expression.length()==1)
                result= parseInt(expression);
            else {
                int contaQuadre = getContaQuadre(expression);
                if(isSeparatoreDiverso(expression) && contaQuadre==0){
                    sep= extractSep(expression, SIMPLESEP);
                    expression= expression.substring(4);
                }

                else {
                    if(contaQuadre>0){
                        String primaParte=expression.substring(0, expression.indexOf(ACAPO)+1);
                        expression=expression.substring(expression.indexOf(ACAPO)+1);
                        String [] multiSep = new String[contaQuadre];
                        ExtractAllSep(contaQuadre, primaParte, multiSep);

                        for (int j = 0; j < contaQuadre; j++) {
                            expression=expression.replaceAll(multiSep[j], ",");
                        }
                    }
                }

                StringBuilder sb = new StringBuilder("Numeri negativi non ammessi:");
                boolean check=false;

                expression=expression.replace(ACAPO, COMMA);
                String[] number = expression.split(sep);

                for (int i = 0; i < number.length; i++) {
                    if(isNegative(number[i], '-')){
                        check=true;
                        writeNegatives(sb, number, i);
                    }
                    else if(isNotHigh(number[i]))
                        result += parseInt(number[i]);
                }

                if (check)
                    throw new IllegalArgumentException(sb.toString());
            }
        }
        return result;
    }

}
