import java.util.Scanner;

public class Main {
    public static int getA() {
        Scanner sc = new Scanner(System.in);
        int a;
        while (true) {
            System.out.print("Enter the key a (0-25) (must be coprime with 26): ");
            a = sc.nextInt();
            if (gcd(a, 26) == 1) {
                break;
            } else {
                System.out.println("Invalid input! 'a' must be coprime with 26.");
            }
        }
        return a;
    }

    public static int getB() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the key b (0-25): ");
        int b = sc.nextInt();
        return b;
    }
    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return -1;
    }

    public static String affineCipher(String text, int a, int b, String mode) {
        StringBuilder result = new StringBuilder();
        int shift = mode.equals("encrypt") ? 1 : -1;
        
        for (char ch : text.toCharArray()) {
            if (Character.isAlphabetic(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                int x = ch - base;
                int transformed;
                if (mode.equals("decrypt")) {
                    int a_inv = modInverse(a, 26);
                    transformed = (a_inv * (x - b + 26)) % 26;
                }else{
                     transformed = (a * x + b) % 26;
                }
                result.append((char) (transformed + base));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("*********************** Affine Cipher *************************");
            System.out.print("Enter mode (encrypt/decrypt): ");
            String mode = sc.next().toLowerCase();

            System.out.print("Enter text: ");
            String text = sc.next();
            
            int a = getA();
            int b = getB();

            String encrypted = affineCipher(text, a, b, mode);
            String decrypted = affineCipher(encrypted, a, b, "decrypt");
            System.out.println("Encrypted text: "+encrypted);
            System.out.println("Decryped text: "+decrypted);
        }
    }
}
