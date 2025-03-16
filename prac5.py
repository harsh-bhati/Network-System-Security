def power(base, expo, m):
    res = 1
    base = base % m
    while expo > 0:
        if expo & 1:
            res = (res * base) % m
        base = (base * base) % m
        expo = expo // 2
    return res

def is_prime(n):
    if n < 2:
        return False
    for i in range(2, int(n**0.5) + 1):
        if n % i == 0:
            return False
    return True

def modInverse(e, phi):
    for d in range(2, phi):
        if (e * d) % phi == 1:
            return d
    return -1

def gcd(a, b):
    while b != 0:
        a, b = b, a % b
    return a

def generateKeys():
    p = int(input("Enter prime number p: "))
    while not is_prime(p):
        print("Invalid input. p must be a prime number.")
        p = int(input("Enter prime number p again: "))
    
    q = int(input("Enter prime number q: "))
    while not is_prime(q):
        print("Invalid input. q must be a prime number.")
        q = int(input("Enter prime number q again: "))
    
    n = p * q
    phi = (p - 1) * (q - 1)
    
    e = 0
    if gcd(e, phi) != 1:
        for i in range(3, phi, 2):
            if gcd(i, phi) == 1:
                e = i
                break
    
    d = modInverse(e, phi)
    
    return e, d, n

def encrypt(m, e, n):
    return power(m, e, n)

def decrypt(c, d, n):
    return power(c, d, n)

if __name__ == "__main__":
    e, d, n = generateKeys()
    
    print(f"Public Key (e, n): ({e}, {n})")
    print(f"Private Key (d, n): ({d}, {n})")
    
    M = int(input("Enter message (as a number) to encrypt: "))
    C = encrypt(M, e, n)
    print(f"Encrypted Message: {C}")
    
    decrypted = decrypt(C, d, n)
    print(f"Decrypted Message: {decrypted}")
