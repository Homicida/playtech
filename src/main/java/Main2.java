/**
 * Created by Sander on 30.04.2016.
 */
public class Main2 {
    public static void main(String args[]){
        int[][] matrix = new int[4][4];
        initMatrix(matrix, 4);
        rotate(matrix, 4);
        printMatrix(matrix, 4);
    }
    public static void rotate(int[][] matrix, int n){
        System.out.println("Input: ");
        printMatrix(matrix, n);
        for(int i = 0; i < n /2; i++){
            System.out.println("Starting rotating outer loop");
            int last = n - 1 - i;
            for(int j = i; j < last; j++){
                int offset = j - i;
                int buffer = matrix[i][j]; // save top

                System.out.println("Starting rotating inner loop");
                System.out.println("i: "+i);
                System.out.println("last: "+last);
                System.out.println("j: "+j);
                System.out.println("buffer: "+buffer);
                System.out.println("offset = j-i: "+offset);

                matrix[i][j] = matrix[last-offset][i]; //left -> top

                matrix[last-offset][i] = matrix[last][last-offset]; //bottom -> left

                matrix[last][last-offset] = matrix[j][last]; // right -> bottom

                matrix[j][last] = buffer; // saved top -> right

                printMatrix(matrix, n);
                System.out.println("Finished rotating inner loop");
            }
            System.out.println("Finished rotating outer loop");
        }
    }

    public static void printMatrix(int[][] matrix, int n){
        System.out.println("");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(" "+matrix[i][j]);
            }
            System.out.println("");
        }
    }
    public static void initMatrix(int[][] matrix, int n){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = (int) (Math.random()*100);
            }
        }
    }
}
