package sudoku.DecipherSudoku;

import sudoku.Algoritm;
import sudoku.Decipher;

public class Main {
    public static void main(String[] args) {

         //Screan sc = new Screan();

        Algoritm a = new Algoritm(61);
        a.outsNew();
        Decipher d = new Decipher();
        d.setArr(a.getSudoku());
        d.decipherAssumption();
        d.outsNew();
        System.out.println("P: " + d.perevirka());
        System.out.println("Number: "+d.getNumberDecipher());
    }
}