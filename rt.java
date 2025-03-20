import java.util.*;

public class Main {
    public static String encrypt(String pt, int key[]) {
        int row = key.length;
        int col = (int)Math.ceil((double)pt.length() / row);
        char mat[][] = new char[row][col];
        int idx = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (idx < pt.length()) {
                    mat[i][j] = pt.charAt(idx);
                    idx++;
                } else {
                    mat[i][j] = ' ';
                }
            }
        }

        System.out.println("Matrix after encryption (row-wise):");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }

        StringBuilder sb = new StringBuilder();
        for (int i : key) {
            for (int j = 0; j < col; j++) {
                sb.append(mat[i - 1][j]);
            }
        }
        return sb.toString();
    }

    public static String decrypt(String pt, int key[]) {
        int row = key.length;
        int col = pt.length() / row;
        char mat[][] = new char[row][col];

        int[] keyPositions = new int[row];
        for (int i = 0; i < row; i++) {
            keyPositions[i] = key[i] - 1;
        }

        int idx = 0;
        for (int i : keyPositions) {
            for (int j = 0; j < col; j++) {
                mat[i][j] = pt.charAt(idx);
                idx++;
            }
        }

        System.out.println("Matrix after decryption (column-wise):");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                sb.append(mat[i][j]);
            }
        }

        return sb.toString().trim();
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the size of key order: ");
        int n = sc.nextInt();
        System.out.println("Enter key order: ");
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        System.out.println("Enter the text: ");
        String plaintext = sc.next();
        
        String encrypted = encrypt(plaintext, arr);
        System.out.println("Encrypted text: " + encrypted);
        
        String decrypted = decrypt(encrypted, arr);
        System.out.println("Decrypted text: " + decrypted);
    }
}
