import java.util.*;
import static java.lang.Math.*;

public class Zellen {
    public static boolean[][] Genesis (int N, int alive) { // default: false!
        boolean[][] A = new boolean[N][N];
        int count = 0;
        while ( count < alive ) {
            int rand_i = (int)(random()*N);
            int rand_j = (int)(random()*N);
            if ( A[rand_i][rand_j] == false ) {
                A[rand_i][rand_j] = true;
                count++;
            } 
        }    
        return A;
    }
    public static int AnzNachbarn (boolean[][] A, int[] koord) { // koord in mathmatical logic
        int Anz = 0;
        for ( int i = koord[0]-2; i <= koord[0]; i++) {
            for ( int j = koord[1]-2; j <= koord[1]; j++) {
                if ( i >= 0 && i <= A.length-1 && j >= 0 && j <= A[0].length-1 && A[i][j] && (koord[0] != i+1 || koord[1] != j+1)) {
                    Anz++;
                }
            }
        }
        return Anz;
    }
    public static boolean[][] NeueGeneration (boolean[][] A) {
        boolean[][] B = new boolean[A.length][A[0].length];
        for (int i = 0; i <= B.length-1; i++) {
            for (int j = 0; j <= B[0].length-1; j++) {
                int[] step = {i+1,j+1}; // in mathmatical logic, too
                int AnzNachb = AnzNachbarn(A, step);
                if ( AnzNachb == 3 ) {
                    B[i][j] = true;
                }
                else if ( AnzNachb == 2 ) {
                    B[i][j] = A[i][j];
                }
                else {
                    B[i][j] = false;
                }
            }
        }
        return B;
    }
    public static void matrixAusgeben (boolean[][] A) {
        for (int i = 0; i <= A.length-1; i++) {
            for (int j = 0; j <= A[0].length-1; j++) {
                if ( j < A[0].length-1) {
                    if ( A[i][j] ) {
                        System.out.print("*");
                    }
                    else {
                        System.out.print(" ");
                    }
                }
                else {
                    if ( A[i][j] ) {
                        System.out.println("*");
                    }
                    else {
                        System.out.println(" ");
                    }
                }
            }
        }
    }
    public static boolean compare (boolean[][] A, boolean[][] B) {
        boolean equal = true;
        for ( int i = 0; i <= A.length-1; i++ ) {
            for ( int j = 0; j <= A[0].length-1; j++ ) {
                if ( A[i][j] != B[i][j] ) {
                    equal = false;
                    return equal;
                }
            }
        }
        return equal;
    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter desired dimension N = ");
        int N = sc.nextInt();
        System.out.print("Please enter desired number of cells alive : ");
        int alive = sc.nextInt();
        boolean[][] A = Genesis(N,alive);
        System.out.println("----------\n First colony to develope:\n----------"); // DEAD-SPIN!
        matrixAusgeben(A);
        System.out.println("----------\n Developement:");
        while ( !compare(A,NeueGeneration(A)) ) {
            System.out.println("----------");
            A = NeueGeneration(A);
            matrixAusgeben(A);
        }
        System.out.println("----------\n Last colony after developement:\n----------");
        matrixAusgeben(A);        
    }
}