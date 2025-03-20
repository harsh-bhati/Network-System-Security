import java.util.*;
public class Main{
    public static long power(long base, long expo, long mod){
        long res = 1;
        while(expo>0){
            if((expo&1) != 0){
                res = res*base%mod;
            }
            base = base * base %mod;
            expo = expo/2;
        }
        return res;
    }
    public static long modinverse(long e, long phi){
        for(long i = 2; i<phi; i++){
            if((e*i)%phi == 1){
                return i;
            }
        }
        return -1;
    }
    public static long gcd(long a, long b){
        if(b == 0) return a;
        return gcd(b, a%b);
    }
    public static long generateKey(long phi){
        for(long i = 2; i<phi; i++){
            if(gcd(phi, i) == 1){
                return i;
            }
        }
        return -1;
    }
    public static boolean isprime(long num){
        if(num<2) return false;
        for(long i = 2; i*i<=num; i++){
            if(num%i == 0){
                return false;
            }
        }
        return true;
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter value of p: ");
        long p = sc.nextInt();
        while(!isprime(p)){
            System.out.println("Enter p again : ");
            p = sc.nextInt();
        }
        
        System.out.println("Enter value of q: ");
        long q = sc.nextInt();
        while(!isprime(q)){
            System.out.println("Enter q again : ");
            q = sc.nextInt();
        }
        
        long n = p*q;
        long phi = (p-1)*(q-1);
        
        long e = generateKey(phi);
        long d = modinverse(e, phi);
        
        
        System.out.println("Enter the message: ");
        long m = sc.nextInt();
         System.out.println("Public Key (e, n):"+e+","+n);
                  System.out.println("Public Key (d, n):"+d+","+n);

        long c = power(m, e, n);//encrypt (m, e, n)
        long decrypt = power(c, d, n);//decrypt(c, d, n)
        
        System.out.println("Encrypted message: "+c);
        System.out.println("Decrypted message: "+decrypt);
    }
}