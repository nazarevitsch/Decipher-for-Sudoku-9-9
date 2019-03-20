package sudoku.DecipherSudoku;

public class Algoritm {

    private int zahl;
    private int[] zifr = new int[9];
    private int[][] matrix = new int[9][9];
    private int[][] sudoku = new int[9][9];

    public Algoritm(int zahl){
        this.zahl = zahl;
        zifr();
        zapov();
        perekudka();
        decipherSudoku();
    }

    public int getElementOld(int i, int l){
        return matrix[i][l];
    }

    public int getElementNew(int i, int l){
        return sudoku[i][l];
    }

    public void creatNew(){
        zapov();
        perekudka();
        decipherSudoku();
    }

    private void zifr(){
        for(int i = 0; i < zifr.length; i++){
            zifr[i] = (i + 1);
        }
    }

    private void zapov(){
        for(int i = 0; i < matrix.length; i++){
            for(int l = 0; l < matrix[i].length; l++){
                if((l+i) >= 9 ){
                    matrix[i][l] = zifr[(i+l)-9];
                }
                if((l+i) < 9 ) {
                    matrix[i][l] = zifr[l + i];
                }
            }
        }
        pererobka1(2, 4);
        pererobka1(3, 7);
        pererobka1(6,8);
    }

    private void perekudka() {
        for (int l = 0; l < 10; l++) {
            for (int i = 0; i < 3; i++) {
                int rand1 = (int) ((Math.random() * 2) + 1);
                int rand2 = (int) ((Math.random() * 2) + 1);
                pererobka1((rand1 + (i * 3)), (rand2 + (i * 3)));
                pererobka2((rand1 + (i * 3)), (rand2 + (i * 3)));
                for(int j = 0; j < 3 ;j++){
                    pererobka1((1 + j), (4 + j));
                }
                for(int j = 0; j < 3; j++){
                    pererobka2((1 + j), (4 + j));
                }
            }
        }
    }

    private void pererobka1(int one, int two) {
        for (int l = 0; l < matrix.length; l++) {
            int k = matrix[one - 1][l];
            matrix[one - 1][l] = matrix[two - 1][l];
            matrix[two - 1][l] = k;
        }
    }

    private void pererobka2(int one, int two) {
        for (int l = 0; l < matrix.length; l++) {
            int k = matrix[l][one - 1];
            matrix[l][one - 1] = matrix[l][two - 1];
            matrix[l][two - 1] = k;
        }
    }

    private void decipherSudoku() {
        int n = zahl;

        for (int i = 0; i < matrix.length; i++) {
            for (int l = 0; l < matrix[i].length; l++) {
                sudoku[i][l] = matrix[i][l];
            }
        }
        while (n > 0) {
            for (int i = 0; i < matrix.length; i++) {
                for (int l = 0; l < matrix[i].length; l++) {
                    if (sudoku[i][l] != 0) {
                        int rand = (int) (Math.random() * 5);
                        if (rand == 0) {
                            sudoku[i][l] = 0;
                            n--;
                        }
                    }
                    if (n == 0) return;
                }
            }
        }
    }

    public void outsOld(){
        System.out.println("Your old Sudoku:");
        for(int i = 0; i < matrix.length; i++){
            for(int l = 0; l < matrix[i].length; l++){
                System.out.printf("%3d",matrix[i][l]);
            }
            System.out.println("");
        }
    }

    public void outsNew(){
        System.out.println("Your ciphered Sudoku:");
        for(int i = 0; i < sudoku.length; i++){
            for(int l = 0; l < sudoku[i].length; l++){
                System.out.printf("%3d",sudoku[i][l]);
            }
            System.out.println("");
        }
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int[][] getSudoku() {
        return sudoku;
    }
}
