import math

def encrypt(message, key):
    key_order = sorted(range(len(key)), key=lambda k: key[k])
    num_cols = len(key)
    num_rows = math.ceil(len(message) / num_cols)
    
    # Fill the matrix row-wise
    matrix = [['' for _ in range(num_cols)] for _ in range(num_rows)]
    index = 0
    for i in range(num_rows):
        for j in range(num_cols):
            if index < len(message):
                matrix[i][j] = message[index]
                index += 1
    
    # Read the matrix column-wise using key order
    ciphertext = ''
    for col in key_order:
        for row in range(num_rows):
            if matrix[row][col]:
                ciphertext += matrix[row][col]
    
    return ciphertext

def decrypt(ciphertext, key):
    key_order = sorted(range(len(key)), key=lambda k: key[k])
    num_cols = len(key)
    num_rows = math.ceil(len(ciphertext) / num_cols)
    
    # Create an empty matrix
    matrix = [['' for _ in range(num_cols)] for _ in range(num_rows)]
    
    # Fill the matrix column-wise using key order
    index = 0
    for col in key_order:
        for row in range(num_rows):
            if index < len(ciphertext):
                matrix[row][col] = ciphertext[index]
                index += 1
    
    # Read the matrix row-wise to get the plaintext
    plaintext = ''
    for row in matrix:
        plaintext += ''.join(row)
    
    return plaintext

# User input
message = input("Enter the message: ")
key = list(map(int, input("Enter the key as space-separated numbers: ").split()))

ciphertext = encrypt(message, key)
print("Encrypted:", ciphertext)

decrypted_message = decrypt(ciphertext, key)
print("Decrypted:", decrypted_message)
