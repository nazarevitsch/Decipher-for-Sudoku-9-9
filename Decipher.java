package sudoku.DecipherSudoku;

import java.util.Arrays;
import java.util.Scanner;

public class Decipher {

    private int[][] arr = new int[9][9];
    private int numberDecipher;

    public void decipherFull(){
        decipherAssumption();
    }

    public void setArr(int[][] arr) {
        this.arr = arr;
    }

    public void setArrManual(){
        Scanner a = new Scanner(System.in);
        for(int i = 0; i < 9; i++){
            for(int l = 0; l < 9; l++){
                System.out.print("I: "+(i + 1)+", L: "+(l + 1)+", Enter: ");
                arr[i][l] = a.nextInt();
            }
        }
    }

    public void setElement(int i, int l, int data){
        arr[i][l] = data;
    }

    public int getElement(int i, int l){
        return arr[i][l];
    }

    public int getNumberDecipher() {
        return numberDecipher;
    }

    public void decipherAssumption() {
        boolean important;
        do {
            important = false;
            numberDecipher++;
            boolean EnterInCompare = false;
            int I = 0;
            int L = 0;
            int NumberAssumption = 10;
            int[] Index = new int[9];
            for (int i = 0; i < 9; i++) {
                for (int l = 0; l < 9; l++) {
                    if (arr[i][l] == 0) {
                        boolean[] assumption = new boolean[9];
                        for (int j = 0; j < 9; j++) {
                            assumption[j] = false;
                        }
//                        int IindexLower = (i / 3) * 3;
//                        int LindexLower = (l / 3) * 3;
//                        int IindexHight = ((i / 3) * 3) + 3;
//                        int LindexHight = ((l / 3) * 3) + 3;
                        int s = -1;
                        for (int k = ((i / 3) * 3); k < ((i / 3) * 3) + 3; k++) {
                            for (int t = ((l / 3) * 3); t < ((l / 3) * 3) + 3; t++) {
                                if (arr[k][t] != 0) assumption[arr[k][t] - 1] = true;
                                s++;
                                if (arr[i][s] != 0) assumption[arr[i][s] - 1] = true;
                                if (arr[s][l] != 0) assumption[arr[s][l] - 1] = true;
                            }
                        }
                        int numAss = 0;
                        int[] index = new int[9];
                        for (int j = 0; j < 9; j++) {
                            if (!assumption[j]) {
                                numAss++;
                                index[numAss - 1] = j + 1;
                            }
                        }
                        if (numAss < NumberAssumption && numAss != 1) {
                            NumberAssumption = numAss;
                            I = i;
                            L = l;
                            for (int z = 0; z < NumberAssumption; z++) {
                                Index[z] = index[z];
                            }
                        }
                        if (numAss == 1) {
                            EnterInCompare = true;
                            arr[i][l] = index[0];
                            important = true;
                        }
                    }
                }
            }
            int[][] checkPoint = new int[9][9];
            if (NumberAssumption >= 2 && NumberAssumption <= 9 && !EnterInCompare) {
                for (int i = 0; i < NumberAssumption; i++) {
                    if (i == 0) {
                                checkPoint = Arrays.copyOf(arr, arr.length);
                    }
                    if (i != 0) {
                                arr = Arrays.copyOf(checkPoint, checkPoint.length);
                    }
                    arr[I][L] = (Index[i]);
                    decipherAssumption();
                   if(perevirka()) break;
                }
                important = true;
            }
        } while (important);
    }

    public boolean perevirka(){
        int[] data = new int[9];
        for(int i = 0; i < 9; i++){
            for(int l = 0; l < 9; l++){
                try {
                    data[arr[i][l] - 1]++;
                } catch (Exception e){
                    return false;
                }
            }
        }
        for(int i = 0; i < 9; i++){
            if(data[i] != 9)
                return false;
        }
        return true;
    }

    public void outsNew(){
        System.out.println("Your new deciphered Sudoku:");
        for(int i = 0; i < arr.length; i++){
            for(int l = 0; l < arr[i].length; l++){
                System.out.printf("%3d",arr[i][l]);
            }
            System.out.println("");
        }
    }
}
