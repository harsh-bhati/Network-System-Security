import numpy as np
from math import gcd

# Function to convert text to numerical values
def text_to_numbers(text):
    return [ord(char) - ord('A') for char in text]

# Function to convert numerical values back to text
def numbers_to_text(numbers):
    return ''.join(chr(num + ord('A')) for num in numbers)

# Function to check if matrix is invertible in mod 26
def is_invertible(matrix, mod=26):
    det = int(round(np.linalg.det(matrix))) % mod
    return gcd(det, mod) == 1

# Function to encrypt the plaintext using the key matrix
def encrypt(plain_text, key_matrix):
    plain_text = plain_text.upper().replace(" ", "X")  # Convert to uppercase and replace spaces
    n = len(key_matrix)
    while len(plain_text) % n != 0:
        plain_text += 'X'  # Padding to match matrix size
    text_numbers = text_to_numbers(plain_text)
    text_matrix = np.array(text_numbers).reshape(-1, n)
    encrypted_matrix = (text_matrix @ key_matrix) % 26
    return numbers_to_text(encrypted_matrix.flatten())

# Function to decrypt the ciphertext using the key matrix
def decrypt(cipher_text, key_matrix):
    cipher_text = cipher_text.upper()
    n = len(key_matrix)
    
    # Compute modular inverse of key matrix
    det = int(round(np.linalg.det(key_matrix)))
    det_inv = pow(det % 26, -1, 26)  # Modular inverse of determinant
    adjugate = np.round(np.linalg.inv(key_matrix) * det).astype(int) % 26
    key_inverse = (det_inv * adjugate) % 26
    
    text_numbers = text_to_numbers(cipher_text)
    text_matrix = np.array(text_numbers).reshape(-1, n)
    decrypted_matrix = (text_matrix @ key_inverse) % 26
    decrypted_text = numbers_to_text(decrypted_matrix.flatten())
    decrypted_text = decrypted_text.rstrip('X')  # Remove padding X
    return decrypted_text.replace("X", " ")  # Convert X back to space

# Take user input for plaintext
plain_text = input("Enter the plaintext: ")

# Define a 3x3 key matrix (must be invertible in mod 26)
key_matrix = np.array([[6, 24, 1], [13, 16, 10], [20, 17, 15]])

if not is_invertible(key_matrix):
    print("Error: Key matrix is not invertible under mod 26. Choose a different key.")
else:
    # Encrypt the plaintext
    cipher_text = encrypt(plain_text, key_matrix)
    print("Encrypted Text:", cipher_text)

    # Decrypt the ciphertext
    decrypted_text = decrypt(cipher_text, key_matrix)
    print("Decrypted Text:", decrypted_text)
