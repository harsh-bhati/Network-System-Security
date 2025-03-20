import java.util.*;
class Main{
    public static int power(int base, int exp, int mod){
        int res = 1;
        while(exp>0){
            if((exp&1) != 0)
                res = res * base %mod;
            base  *= base %mod;
            exp>>=1;
        }
        return res;
    }
    public static boolean isprime(int num){
        if(num<2) return false;
        for(int i = 2; i*i<=num; i++){
            if(num%i==0) return false;
        }
        return true;
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int p = sc.nextInt();
        while(!isprime(p)){
            System.out.println("Not prime, Enter prime no. again : ");
            p = sc.nextInt();
        }
        int g = sc.nextInt();
        System.out.println("Alice's Private Key: ");
        int a = sc.nextInt();
        System.out.println("Bob's Private Key: ");
        int b = sc.nextInt();
        
        int pa = power(g, a, p);
        int pb = power(g, b, p);
        
        int ka = power(pb, a, p);
        int kb = power(pa, b, p);
        
        System.out.println("Alice's secret key: "+ ka);
        System.out.println("Bob's secret key: "+ kb);
        
    }
}
//p = 7, a = 3, ak = 5, bk = 2