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

def diffie_hellman():
    P = int(input("Enter prime number P: "))
    while not is_prime(P):
        print("Invalid input. P must be a prime number.")
        P = int(input("Enter prime number P again: "))
    
    G = int(input("Enter primitive root G of P: "))
    a = int(input("Enter private key a for Alice: "))
    b = int(input("Enter private key b for Bob: "))
    
    x = power(G, a, P)  # Alice's public key
    y = power(G, b, P)  # Bob's public key
    
    ka = power(y, a, P)  # Secret key for Alice
    kb = power(x, b, P)  # Secret key for Bob

    print("\n")
    print("**********************")
    
    print(f"Secret key for Alice: {ka}")
    print(f"Secret key for Bob: {kb}")

if __name__ == "__main__":
    diffie_hellman()
