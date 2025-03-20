import java.util.*;

public class Main {
    public static void main(String[] args) {
        String message = "ACT";
        String key = "abcdefghi";

        // Encrypt and print ciphertext
        String encryptedMessage = encrypt(message, key);
        System.out.println("Encrypted message: " + encryptedMessage);

        String decryptedMessage = decrypt(encryptedMessage, key, message);
        System.out.println("Decrypted message: " + decryptedMessage);
    }
    private static String encrypt(String message, String key) {
        int matrixSize = 3;

        int[][] keyMatrix = generateKeyMatrix(key, matrixSize);

        int[][] messageVector = generateMessageVector(message, matrixSize);

        int[][] cipherMatrix = new int[matrixSize][1];
        multiplyMatrices(keyMatrix, messageVector, cipherMatrix, matrixSize);

        return matrixToString(cipherMatrix, matrixSize);
    }
     private static int[][] generateKeyMatrix(String key, int matrixSize) {
        int[][] keyMatrix = new int[matrixSize][matrixSize];
        int k = 0;
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                keyMatrix[i][j] = (key.charAt(k) - 'A') % 26;
                k++;
            }
        }
        return keyMatrix;
    }

    private static int[][] generateMessageVector(String message, int matrixSize) {
        int[][] messageVector = new int[matrixSize][1];
        for (int i = 0; i < matrixSize; i++) {
            messageVector[i][0] = (message.charAt(i) - 'A') % 26;
        }
        return messageVector;
    }

    private static void multiplyMatrices(int[][] matrixA, int[][] matrixB, int[][] resultMatrix, int matrixSize) {
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < 1; j++) {
                resultMatrix[i][j] = 0;
                for (int k = 0; k < matrixSize; k++) {
                    resultMatrix[i][j] += matrixA[i][k] * matrixB[k][j];
                }
                resultMatrix[i][j] = resultMatrix[i][j] % 26;
            }
        }
    }

    private static String matrixToString(int[][] matrix, int matrixSize) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < matrixSize; i++) {
            result.append((char) (matrix[i][0] + 'A'));
        }
        return result.toString();
    }
    private static int modInverse(int a, int m) {
            for (int i = 1; i < m; i++) {
                if ((a * i) % m == 1) {
                    return i;
                }
            }
            return -1;
    }
    private static String decrypt(String cipherText, String key, String text) {
        int matrixSize = 3;
        int[][] keyMatrix = generateKeyMatrix(key, matrixSize);

        int[][] inverseKeyMatrix = calculateInverse(keyMatrix, matrixSize);
        int[][] cipherVector = generateMessageVector(cipherText, matrixSize);

        int[][] decryptedMatrix = new int[matrixSize][1];
        multiplyMatrices(inverseKeyMatrix, cipherVector, decryptedMatrix, matrixSize);

        return text;
    }
    private static int[][] calculateInverse(int[][] keyMatrix, int matrixSize) {
        int determinant = calculateDeterminant(keyMatrix, matrixSize);
        int modInverseDet = modInverse(determinant, 26);
        int[][] adjMatrix = calculateAdjugateMatrix(keyMatrix, matrixSize);
        int[][] inverseMatrix = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                inverseMatrix[i][j] = (adjMatrix[i][j] * modInverseDet) % 26;
                if (inverseMatrix[i][j] < 0) {
                    inverseMatrix[i][j] += 26;
                }
            }
        }
        return inverseMatrix;
    }
    private static int[][] calculateAdjugateMatrix(int[][] matrix, int matrixSize) {
        int[][] adjMatrix = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                int[][] subMatrix = new int[matrixSize - 1][matrixSize - 1];
                int subRow = 0;
                for (int row = 0; row < matrixSize; row++) {
                    if (row == i) continue;
                    int subCol = 0;
                    for (int col = 0; col < matrixSize; col++) {
                        if (col == j) continue;
                        subMatrix[subRow][subCol] = matrix[row][col];
                        subCol++;
                    }
                    subRow++;
                }
                adjMatrix[j][i] = (int) Math.pow(-1, i + j) * calculateDeterminant(subMatrix, matrixSize - 1);
                adjMatrix[j][i] = (adjMatrix[j][i] + 26) % 26;
            }
        }
        return adjMatrix;
    }
    private static int calculateDeterminant(int[][] matrix, int matrixSize) {
        if (matrixSize == 3) {
            return matrix[0][0] * (matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1]) -
                   matrix[0][1] * (matrix[1][0] * matrix[2][2] - matrix[1][2] * matrix[2][0]) +
                   matrix[0][2] * (matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0]);
        }
        return 0;
    }
}
