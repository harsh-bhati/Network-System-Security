import java.util.Scanner;

public class Main {

    public static int getKey() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Key (0-25): ");
        int key = sc.nextInt();
        while (key < 0 || key > 25) {
           System.out.println("Invalid input! Please enter a number between 0 and 25.");
           key = sc.nextInt();
        }
        return key;
    }
    public static String caesarCipher(String text, int key, String mode) {
        StringBuilder result = new StringBuilder();
        int shift = mode.equals("encrypt") ? key : -key;

        for (char ch : text.toCharArray()) {
            if (Character.isAlphabetic(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                result.append((char) ((ch - base + shift + 26) % 26 + base));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("************************ Caesar Cipher ***************************");
            System.out.print("Enter mode (encrypt/decrypt): ");
            String mode = sc.next().toLowerCase();
            System.out.print("Enter text: ");
            String text = sc.next();
            int key = getKey();
            String encrypted = caesarCipher(text, key, mode);
            String decrypted = caesarCipher(encrypted, key, "decrypt");
            System.out.println("Encrypted text: "+encrypted);
            System.out.println("Decryped text: "+decrypted);
        }
    }
}
