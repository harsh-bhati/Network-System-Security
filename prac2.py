import math

# Function to compute modular inverse of 'a' under modulo m
def mod_inverse(a, m):
    for i in range(1, m):
        if (a * i) % m == 1:
            return i
    return None

# Function to check if 'a' is coprime with 26
def is_coprime(a, m):
    return math.gcd(a, m) == 1

# Function to perform encryption or decryption
def affine_cipher(text, a, b, mode="encrypt"):
    result_text = ""
    
    # Get modular inverse of 'a' for decryption
    a_inv = mod_inverse(a, 26)
    if mode == "decrypt" and a_inv is None:
        raise ValueError("Key 'a' has no modular inverse, choose a different key")
    
    for char in text:
        if char.isalpha():
            base = ord('A') if char.isupper() else ord('a')
            x = ord(char) - base  # Convert character to number (0-25)
            if mode == "encrypt":
                C = (a * x + b) % 26
            else:  # decrypt
                C = (a_inv * (x - b)) % 26
            result_text += chr(C + base)  # Convert back to character
        else:
            result_text += char  # Keep non-alphabetic characters unchanged

    return result_text

# Function to take valid input for keys
def get_valid_keys():
    while True:
        try:
            a = int(input("Enter value of 'a' (must be coprime with 26): "))
            if not is_coprime(a, 26):  
                print("Invalid input! 'a' must be coprime with 26. Try again.")
                continue  # Restart loop if 'a' is invalid

            b = int(input("Enter value of 'b' (0-25): "))
            if not (0 <= b <= 25):  
                print("Invalid input! 'b' must be between 0 and 25. Try again.")
                continue  # Restart loop if 'b' is invalid

            return a, b  # Return valid keys

        except ValueError:
            print("Invalid input! Enter integers only.")

# Main function
def main():
    while True:
        print("\nAffine Cipher")
        mode = input("Enter mode (encrypt/decrypt/exit): ").strip().lower()

        match mode:
            case 'encrypt' | 'decrypt':
                text = input("Enter the text: ")
                a, b = get_valid_keys()
                try:
                    print(f"Result: {affine_cipher(text, a, b, mode)}")
                except ValueError as e:
                    print(e)
            case 'exit':
                print("Exited")
                break

# Run the main function
if __name__ == "__main__":
    main()
